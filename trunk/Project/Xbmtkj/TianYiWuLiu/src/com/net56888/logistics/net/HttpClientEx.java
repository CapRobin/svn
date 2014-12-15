package com.net56888.logistics.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.net56888.logistics.App;

public class HttpClientEx {

    private HttpClient client;
    private HttpContext context;
    private CookieStore cookieStore;
    private static HttpClientEx instance = new HttpClientEx();
    
    private HttpClientEx() {
        try {
            KeyStore trustStore;
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            
            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            ConnManagerParams.setMaxTotalConnections(params, 200);
            ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRoute() {
                @Override
                public int getMaxForRoute(HttpRoute route) {
                    return 20;
                }
            });
            ConnManagerParams.setTimeout(params, 30);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(
                    new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(
                    new Scheme("https", sf, 443));

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
            client = new DefaultHttpClient(cm, params);

            cookieStore = new PersistentCookieStore();
            context = new BasicHttpContext();
            context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            
        } catch (Exception e) {
            client = new DefaultHttpClient();
        }
    }
    
    public static HttpClientEx getInstance() {
        return instance;
    }

    
    public HttpClient getClient() {
        return client;
    }
    
    public HttpContext getContext() {
        return context;
    }
    
    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void shutdown() {
        client.getConnectionManager().shutdown();
    }

    private class PersistentCookieStore implements CookieStore {
        private static final String COOKIE_PREFS = "CookiePrefsFile";
        private static final String COOKIE_NAME_STORE = "names";
        private static final String COOKIE_NAME_PREFIX = "cookie_";

        private final ConcurrentHashMap<String, Cookie> cookies;
        private final SharedPreferences cookiePrefs;

        /**
         * Construct a persistent cookie store.
         */
        public PersistentCookieStore() {
            Context context = App.getAppContext();
            cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
            cookies = new ConcurrentHashMap<String, Cookie>();

            // Load any previously stored cookies into the store
            String storedCookieNames = cookiePrefs.getString(COOKIE_NAME_STORE, null);
            if(storedCookieNames != null) {
                String[] cookieNames = TextUtils.split(storedCookieNames, ",");
                for(String name : cookieNames) {
                    String encodedCookie = cookiePrefs.getString(COOKIE_NAME_PREFIX + name, null);
                    if(encodedCookie != null) {
                        Cookie decodedCookie = decodeCookie(encodedCookie);
                        if(decodedCookie != null) {
                            cookies.put(name, decodedCookie);
                        }
                    }
                }

                // Clear out expired cookies
                clearExpired(new Date());
            }
        }

        @Override
        public void addCookie(Cookie cookie) {
            String name = cookie.getName();

            // Save cookie into local store, or remove if expired
            if(!cookie.isExpired(new Date())) {
                cookies.put(name, cookie);
            } else {
                cookies.remove(name);
            }

            // Save cookie into persistent store
            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
            prefsWriter.putString(COOKIE_NAME_STORE, TextUtils.join(",", cookies.keySet()));
            prefsWriter.putString(COOKIE_NAME_PREFIX + name, encodeCookie(new SerializableCookie(cookie)));
            prefsWriter.commit();
        }

        @Override
        public void clear() {
            // Clear cookies from local store
            cookies.clear();

            // Clear cookies from persistent store
            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
            for(String name : cookies.keySet()) {
                prefsWriter.remove(COOKIE_NAME_PREFIX + name);
            }
            prefsWriter.remove(COOKIE_NAME_STORE);
            prefsWriter.commit();
        }

        @Override
        public boolean clearExpired(Date date) {
            boolean clearedAny = false;
            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();

            for(ConcurrentHashMap.Entry<String, Cookie> entry : cookies.entrySet()) {
                String name = entry.getKey();
                Cookie cookie = entry.getValue();
                if(cookie.isExpired(date)) {
                    // Clear cookies from local store
                    cookies.remove(name);

                    // Clear cookies from persistent store
                    prefsWriter.remove(COOKIE_NAME_PREFIX + name);

                    // We've cleared at least one
                    clearedAny = true;
                }
            }

            // Update names in persistent store
            if(clearedAny) {
                prefsWriter.putString(COOKIE_NAME_STORE, TextUtils.join(",", cookies.keySet()));
            }
            prefsWriter.commit();

            return clearedAny;
        }

        @Override
        public List<Cookie> getCookies() {
            return new ArrayList<Cookie>(cookies.values());
        }


        //
        // Cookie serialization/deserialization
        //

        protected String encodeCookie(SerializableCookie cookie) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(os);
                outputStream.writeObject(cookie);
            } catch (Exception e) {
                return null;
            }

            return byteArrayToHexString(os.toByteArray());
        }

        protected Cookie decodeCookie(String cookieStr) {
            byte[] bytes = hexStringToByteArray(cookieStr);
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            Cookie cookie = null;
            try {
               ObjectInputStream ois = new ObjectInputStream(is);
               cookie = ((SerializableCookie)ois.readObject()).getCookie();
            } catch (Exception e) {
               e.printStackTrace();
            }

            return cookie;
        }

        // Using some super basic byte array <-> hex conversions so we don't have
        // to rely on any large Base64 libraries. Can be overridden if you like!
        protected String byteArrayToHexString(byte[] b) {
            StringBuffer sb = new StringBuffer(b.length * 2);
            for (byte element : b) {
                int v = element & 0xff;
                if(v < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(v));
            }
            return sb.toString().toUpperCase();
        }

        protected byte[] hexStringToByteArray(String s) {
            int len = s.length();
            byte[] data = new byte[len / 2];
            for(int i=0; i<len; i+=2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
            }
            return data;
        }
    }
    
    public class SerializableCookie implements Serializable {
        private static final long serialVersionUID = 6374381828722046732L;

        private transient final Cookie cookie;
        private transient BasicClientCookie clientCookie;

        public SerializableCookie(Cookie cookie) {
            this.cookie = cookie;
        }

        public Cookie getCookie() {
            Cookie bestCookie = cookie;
            if(clientCookie != null) {
                bestCookie = clientCookie;
            }
            return bestCookie;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(cookie.getName());
            out.writeObject(cookie.getValue());
            out.writeObject(cookie.getComment());
            out.writeObject(cookie.getDomain());
            out.writeObject(cookie.getExpiryDate());
            out.writeObject(cookie.getPath());
            out.writeInt(cookie.getVersion());
            out.writeBoolean(cookie.isSecure());
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            String name = (String)in.readObject();
            String value = (String)in.readObject();
            clientCookie = new BasicClientCookie(name, value);
            clientCookie.setComment((String)in.readObject());
            clientCookie.setDomain((String)in.readObject());
            clientCookie.setExpiryDate((Date)in.readObject());
            clientCookie.setPath((String)in.readObject());
            clientCookie.setVersion(in.readInt());
            clientCookie.setSecure(in.readBoolean());
        }
    }
    
    public class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }
}
