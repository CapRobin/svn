
package com.steellogistics.gps;

import java.util.List;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.steellogistics.global.Constant;

public class GPSManager {

    private final static String TAG = "GPSManager";

    private LocationManager _locationManager;

    private MultipLocationListener mGPSLocationListener;

    private MultipLocationListener mNetWorkLocationListener;

    private MultipLocationListener mPassiveLocationListener;

    private static GPSManager shareInstant;

    private boolean isRegister;

    private GPSLocationListener _bmListener = null;

    private Coordinate currentNetCoordinate = new Coordinate(0, 0);

    private Coordinate currentGPSCoordinate = new Coordinate(0, 0);

    private Coordinate currentPassiveCoordinate = new Coordinate(0, 0);

    public static GPSManager getGPSManagerInstance() {
        if (shareInstant == null) {
            shareInstant = new GPSManager();
        }
        return shareInstant;
    }

    private GPSManager() {

    }

    private synchronized void registerGPS(Context ctx) {
        Logger.i(TAG, "register start.......");
        initLocationChangeListen(ctx);
        findCurrentLocation();
        isRegister = true;
    }

    public synchronized void registerGPS(Context ctx,
            GPSLocationListener bingMapLocationListener) {
        setBingMapLocationListener(bingMapLocationListener);
        registerGPS(ctx);
    }

    public boolean isRegister() {
        if (isRegister) {
            Logger.i(TAG, "is register");
        } else {
            Logger.i(TAG, "is not register");
        }
        return isRegister;
    }

    public synchronized void unRegisterGPS() {
        if (_locationManager != null) {
            Logger.i(TAG, "unRegister start.......");
            removeLocationListener(_locationManager);
            mGPSLocationListener = null;
            mNetWorkLocationListener = null;
            mPassiveLocationListener = null;
            _locationManager = null;
            isRegister = false;
        }
    }

    /**
     * @Description: get gps status,if we can get last know location then it
     *               will return Constants.GPSUCCESSFUL,this function used to
     *               check gps with gps cache
     * @return
     * @return int
     */
    public int getGPSStatus() {
        if (null != _locationManager) {
            boolean isGPSenable = _locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isAGPSenable = _locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            // if gps and agps not open
            if (!isGPSenable && !isAGPSenable) {
                Logger.i(TAG, "gps off");
                return Constant.GPSOFF;
            } else if (getCoordinate().isInvalid()) {
                Logger.i(TAG, "gps failed include last know location is invalid");
                return Constant.GPSFAILED;
            } else {
                Logger.i(TAG, "gps successful");
                return Constant.GPSUCCESSFUL;
            }
        }
        Logger.i(TAG, "gps failed");
        return Constant.GPSFAILED;
    }

    public final Coordinate getCoordinate() {
        if (null != _locationManager) {
            if (checkGPSStatusWithoutCache() == Constant.GPSUCCESSFUL) {
                boolean isGPSenable = _locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);
                boolean isAGPSenable = _locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                boolean isPassiveEnable = _locationManager
                        .isProviderEnabled(LocationManager.PASSIVE_PROVIDER);

                if (isGPSenable && !currentGPSCoordinate.isInvalid()) {
                    Logger.i(TAG, "currentGPSCoordinate:" + currentGPSCoordinate);
                    return currentGPSCoordinate;
                } else if (isAGPSenable && !currentNetCoordinate.isInvalid()) {
                    Logger.i(TAG, "currentNetCoordinate:" + currentNetCoordinate);
                    return currentNetCoordinate;
                } else if (isPassiveEnable && !currentPassiveCoordinate.isInvalid()) {
                    Logger.i(TAG, "currentPassiveCoordinate:" + currentPassiveCoordinate);
                    return currentPassiveCoordinate;
                }
            } else if (checkGPSStatusWithoutCache() == Constant.GPSFAILED) {
                Coordinate bestLastKnowCoordinate = getLastBestCoordinate();
                if (null != bestLastKnowCoordinate) {
                    Logger.i(TAG, "bestLastKnowCoordinate:" + bestLastKnowCoordinate);
                    return bestLastKnowCoordinate;
                }
            }
        }
        return new Coordinate(0, 0);
    }

    /**
     * @Description: check gps status,it is used for getCoordinate function,if
     *               check gps status is failed then getCoordinate function
     *               return last know location
     * @return
     * @return int
     */
    private int checkGPSStatusWithoutCache() {
        if (null != _locationManager) {
            boolean isGPSenable = _locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isAGPSenable = _locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            // if gps and agps not open
            if (!isGPSenable && !isAGPSenable) {
                Logger.i(TAG, "checkGPSStatusWithoutCache off");
                return Constant.GPSOFF;
            } else if (currentNetCoordinate.isInvalid() && currentGPSCoordinate.isInvalid()
                    && currentPassiveCoordinate.isInvalid()) {
                Logger.i(TAG, "checkGPSStatusWithoutCache failed");
                return Constant.GPSFAILED;
            } else if ((isGPSenable && !currentGPSCoordinate.isInvalid())
                    || (isAGPSenable && !currentNetCoordinate.isInvalid())) {
                Logger.i(TAG, "checkGPSStatusWithoutCache successful");
                return Constant.GPSUCCESSFUL;
            }
        }
        Logger.i(TAG, "checkGPSStatusWithoutCache return default failed");
        return Constant.GPSFAILED;
    }

    /**
     * @Description: get the last know coordinate from these providers,if these
     *               providers are disable,then the value of null will be
     *               returned
     * @return Coordinate
     */
    public Coordinate getLastBestCoordinate() {
        if (null != _locationManager) {
            Logger.e(TAG, "--------------Get Last Best Coordinate Start--------------------");
            Coordinate lastBestCoordinate = null;
            Location lastBestLocation = null;
            List<String> matchingProviders = _locationManager.getAllProviders();
            int providerNumber = matchingProviders.size();
            for (int i = 0; i < providerNumber; i++) {
                String provider = matchingProviders.get(i);
                Location location = _locationManager.getLastKnownLocation(provider);
                if (location == null) {
                    Logger.e(TAG, "provider:" + provider + "/location=null");
                } else {
                    Logger.e(TAG,
                            "provider:" + provider + "/location=latitude:" + location.getLatitude()
                                    + "--longtitude:" + location.getLongitude());
                }
                if (null != location) {
                    if (i == 0) {
                        lastBestLocation = location;
                    } else {
                        if (null != lastBestLocation) {
                            if (lastBestLocation.getTime() < location.getTime()) {
                                lastBestLocation = location;
                            }
                        } else {
                            lastBestLocation = location;
                        }
                    }
                }
            }
            Logger.e(TAG, "--------------Get Last Best Coordinate End--------------------");
            if (null != lastBestLocation) {
                lastBestCoordinate = new Coordinate(lastBestLocation.getLatitude(),
                        lastBestLocation.getLongitude());
                return lastBestCoordinate;
            }
        } else {
            Logger.e(TAG, "getLastBestCoordinate location Manager is null");
        }
        return null;
    }

    private void setBingMapLocationListener(GPSLocationListener listener) {
        _bmListener = listener;
    }

    /**
     * init current location
     */
    private void initLocationChangeListen(Context ctx) {

        // location manager init
        if (_locationManager == null) {
            _locationManager = (LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE);
        }

        // location listener init
        initLocationListener();
    }

    /**
     * get location via GPS or AGPS
     */
    private void findCurrentLocation() {

        boolean isGPSenable = _locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isAGPSenable = _locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isPassiveGPS = _locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);

        // if gps and agps not open
        if (!isGPSenable && !isAGPSenable) {
            return;
        }
        // _locationManager.removeUpdates(_locationListener);
        removeLocationListener(_locationManager);

        // gps is open
        if (isGPSenable) {
            // Criteria gpsCriteria = getCriteria(LocationManager.GPS_PROVIDER);
            _locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    Constant.GPSTimeDelta, Constant.GPSDistanceDelta, mGPSLocationListener);
        }
        // agps is open
        if (isAGPSenable) {
            // Criteria agpsCriteria =
            // getCriteria(LocationManager.NETWORK_PROVIDER);
            _locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    Constant.GPSTimeDelta, Constant.GPSDistanceDelta, mNetWorkLocationListener);
        }
        if (isPassiveGPS) {
            // Criteria agpsCriteria =
            // getCriteria(LocationManager.PASSIVE_PROVIDER);
            _locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,
                    Constant.GPSTimeDelta, Constant.GPSDistanceDelta, mPassiveLocationListener);
        }

    }

    /**
     * get Criteria
     * 
     * @param provider Criteria type
     * @return Criteria object
     */
    private Criteria getCriteria(String provider) {
        Criteria criteria = new Criteria();
        if (LocationManager.GPS_PROVIDER.equals(provider)) {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_HIGH);
        } else {
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        }
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        return criteria;
    }

    private void initLocationListener() {
        if (null == mGPSLocationListener) {
            mGPSLocationListener = new MultipLocationListener();
        }

        if (null == mNetWorkLocationListener) {
            mNetWorkLocationListener = new MultipLocationListener();
        }

        if (null == mPassiveLocationListener) {
            mPassiveLocationListener = new MultipLocationListener();
        }
    }

    private void removeLocationListener(LocationManager locationManager) {
        if (null != locationManager) {
            if (null != mGPSLocationListener) {
                locationManager.removeUpdates(mGPSLocationListener);
            }

            if (null != mNetWorkLocationListener) {
                locationManager.removeUpdates(mNetWorkLocationListener);
            }

            if (null != mPassiveLocationListener) {
                locationManager.removeUpdates(mPassiveLocationListener);
            }
        }
    }

    class MultipLocationListener implements LocationListener {

        /**
         * (non-Javadoc) Description:
         * 
         * @param location
         * @see android.location.LocationListener#onLocationChanged(android.location.Location)
         */
        @Override
        public void onLocationChanged(Location location) {
            String provide_Name = location.getProvider();
            if (_bmListener == null) {
                Logger.i(TAG, "listener is null");
            }
            Logger.i(TAG, "provide_Name:" + provide_Name + " onLocationChanged");
            if (provide_Name.equals(LocationManager.NETWORK_PROVIDER)) {
                currentNetCoordinate = new Coordinate(location.getLatitude(),
                        location.getLongitude());
                if (_bmListener != null) {
                    _bmListener.onLocationChanged(location);
                }
            } else if (provide_Name.equals(LocationManager.GPS_PROVIDER)) {
                currentGPSCoordinate = new Coordinate(location.getLatitude(),
                        location.getLongitude());
                if (_bmListener != null) {
                    _bmListener.onLocationChanged(location);
                }
            } else if (provide_Name.equals(LocationManager.PASSIVE_PROVIDER)) {
                currentPassiveCoordinate = new Coordinate(location.getLatitude(),
                        location.getLongitude());
                if (_bmListener != null) {
                    _bmListener.onLocationChanged(location);
                }
            }
        }

        /**
         * (non-Javadoc) Description:
         * 
         * @param provider
         * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
         */
        @Override
        public void onProviderDisabled(String provider) {
            Logger.i(TAG, "provide_Name:" + provider + " disenabled");
        }

        /**
         * (non-Javadoc) Description:
         * 
         * @param provider
         * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
         */
        @Override
        public void onProviderEnabled(String provider) {
            Logger.i(TAG, "provide_Name:" + provider + " enabled");

        }

        /**
         * (non-Javadoc) Description:
         * 
         * @param provider
         * @param status
         * @param extras
         * @see android.location.LocationListener#onStatusChanged(java.lang.String,
         *      int, android.os.Bundle)
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Logger.i(TAG, "provide_Name:" + provider + " status changed");
        }

    }
}
