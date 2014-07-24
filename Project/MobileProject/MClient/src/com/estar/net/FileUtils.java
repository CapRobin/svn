package com.estar.net;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import android.os.Environment;  

public class FileUtils {

	private String SDPATH=null;  
    public String getSDPATH()  
    {  
       return SDPATH; 
    }  
    public FileUtils()  
    {  
       //��õ�ǰ�ⲿ�洢�豸SD����Ŀ¼  
        SDPATH=Environment.getExternalStorageDirectory()+"/";  
    }  
    //�����ļ�  
    public File createFile(String fileName) throws IOException  
    {  
        File file=new File(SDPATH+fileName);  
        file.createNewFile();  
        return file;  
    }  
    //����Ŀ¼ 
    public File createDir(String fileName) throws IOException  
   {  
        File dir=new File(SDPATH+fileName);      
        dir.mkdir();  
        return dir;  
    }  
    //�ж��ļ��Ƿ����  
    public boolean isExist(String fileName)  
   {  
        File file=new File(SDPATH+fileName);  
       return file.exists();  
    }  
    //ɾ���ļ�
    public boolean delFile(String fileName)
    {
    	File file=new File(SDPATH+fileName);
    	
    	return file.delete();
    }
    //ȡ���ļ�ȫ��·��
    public String getFilePath(String fileName)
    {
    	return (SDPATH+fileName);
    }
   public File writeToSDPATHFromInput(String path,String fileName,InputStream inputstream)  
    {  
        File file=null;  
       OutputStream outputstream=null;  
        try  
       {  
            createDir(path);  
            file=createFile(path+fileName);  
            outputstream=new FileOutputStream(file);  
           /* byte buffer[]=new byte[1024]; 
           //���������е����������뵽buffer�л��棬Ȼ���������д���ļ��� 
           while((inputstream.read(buffer))!=-1)  
            {  
                outputstream.write(buffer);  
            } */
            byte buffer[] = new byte[128];
	         // ���������е����������뵽buffer�л��棬Ȼ���������д���ļ���
	         do {
		         int length = (inputstream.read(buffer));
		         if (length != -1) {
		        	 outputstream.write(buffer, 0, length);
		         } else {
		        	 break;
		         }
	         } while (true);

        }  
        catch(Exception e) 
        {  
           e.printStackTrace(); 
        }  
       finally  
        {  
          try {  
                outputstream.close();  
            } catch (IOException e) {  
               // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
       return file;  
   }  
}