package com.stx.xhb.dmgameapp.utils;

import android.os.Handler;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by xhb on 2016/1/5.
 * 网络工具类
 */
public class HttpUtils {

    public static final int TIMEOUT_MILLIS = 10000;
    //运送的Handler
    private static final Handler M_HANDLER = new Handler();

    /**
     * 输入流转字节数组方法
     *
     * @param is 输入流
     */
    private static byte[] isToByteArray(InputStream is) {
        byte[] ret = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024 * 4];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                //读取流
                bos.write(buff, 0, len);
                bos.flush();
            }
            //获取到值
            ret = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            closeStream(is, bos);
        }
        return ret;
    }


    //关闭流
    private static void closeStream(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 评论提交
     *
     * @param params
     * @param encode
     * @return
     */
    public static void submitPostData(Map<String, String> params, String encode, final OnFetchResponseListener listener) {
        final byte[] data = getRequestData(params, encode).toString().getBytes();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL netUrl = new URL(API.COMMENT_COMMIT_URL);
                    HttpURLConnection connection = (HttpURLConnection) netUrl.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(TIMEOUT_MILLIS);
                    connection.setDoInput(true); //打开输入流，以便从服务器获取数据
                    connection.setDoOutput(true); //打开输出流，以便向服务器提交数据
                    connection.setUseCaches(false);//使用Post方式不能使用缓存
                    //设置请求体的类型是文本类型
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    //设置请求体的长度
                    connection.setRequestProperty("Content-Length", String.valueOf(data.length));
                    //获得输出流，向服务器写入数据
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(data);
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();

                        final String response = ResponseResult(is);
                        //该方法在主线程执行
                        M_HANDLER.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.OnFechResponse(response);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 封装请求体信息
     *
     * @param params 请求体内容
     * @param encode 编码格式
     * @return
     */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer(); //存储封装好的请求体信息
        /*try {*/
        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringBuffer.append(entry.getKey())
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue()))
                    .append("&");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"/*
       /* } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return stringBuffer;
    }

    /**
     * 处理服务器响应结果
     *
     * @param is 输入流
     * @return
     */
    public static String ResponseResult(InputStream is) {
        String result = null;
        ByteArrayOutputStream bos = null;
        int len = 0;
        byte[] buffer = new byte[1024];
        try {
            bos = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            closeStream(is, bos);
        }
        result = new String(bos.toByteArray());
        return result;
    }

    //服务器响应处理回调接口
    public interface OnFetchResponseListener {
        void OnFechResponse(String reponse);
    }
}
