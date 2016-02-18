package com.stx.xhb.dmgameapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xhb on 2016/1/9.
 * 下载图片的工具类
 */
public class DownImage {

    public static final int TIMEOUT_MILLIS = 10000;
    /**
     * 网络下载方法
     * @param url
     * @return
     */
    public static byte[] downLoad(String url){

        byte [] ret =null;
       HttpURLConnection connection=null;
        try {
            URL netUrl=new URL(url);
            connection = (HttpURLConnection) netUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(TIMEOUT_MILLIS);
            connection.setReadTimeout(TIMEOUT_MILLIS);
            connection.connect();
            if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream is = connection.getInputStream();
                //输入流转换为字节流
                ret=isToByteArray(is);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.disconnect();
            }
        }
          return ret;
    }

    /**
     * 输入流转换为字节数组
     * @param is
     * @return
     */
    private static byte[] isToByteArray(InputStream is) {
          byte[] ret=null;
        ByteArrayOutputStream bos=null;
        try {
            bos=new ByteArrayOutputStream();
            byte[] buff=new byte[1024*4];
            int len=0;
            while ((len=is.read(buff))!=-1){
                 bos.write(buff,0,len);
                bos.flush();
            }
            ret=bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                //关闭流
               closeStream(is,bos);
        }
        return ret;
    }

    /**
     * 关闭流
     * @param closeables
     */
    private  static void closeStream(Closeable...closeables){

        if (closeables==null){
            return;
        }
        for (Closeable c:closeables){
            if (c!=null){
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
