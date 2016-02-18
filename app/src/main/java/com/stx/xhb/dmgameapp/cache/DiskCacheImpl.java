package com.stx.xhb.dmgameapp.cache;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xhb on 2016/1/9.
 * IDiskC ache的实现类
 * 用于将数据保存在磁盘，以及将文件内容获取出来
 */
public class DiskCacheImpl implements IDiskCache {
    //需要保存或者获取到的文件夹
    private static final String FOLDER = "stx" + File.separator + "cache";
    //定义一个文件夹
    private File folder;

    public DiskCacheImpl() {
        if (!isMounted()) {
            throw new IllegalStateException("磁盘挂载异常");
        }
        //获取到sd根路径的绝对路径
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + folder;
        //实例化一个文件
        folder = new File(absolutePath + File.separator + FOLDER);
        if (!folder.exists() || folder.isFile()) {
            folder.mkdirs();
        }
    }

    /**
     * 将文件保存到里面去
     *
     * @param url  地址
     * @param data 数据
     */
    @Override
    public void put(String url, byte[] data) {
        //如果它们中有一个为空，就不保存了
        if (TextUtils.isEmpty(url) || data == null) {
            return;
        }
        //获取到文件对象
        String fileName = getFileName(url);
        File file = new File(folder, fileName);
        //保存到文件
        saveToFile(file, data);
    }

    /**
     * 保存到文件里面去
     *
     * @param file
     * @param data
     */
    private void saveToFile(File file, byte[] data) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            closeStream(fos, bos);
        }
    }

    /**
     * 判断是否挂载SD卡
     *
     * @return
     */
    private boolean isMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取文件
     *
     * @param url 地址
     * @return
     */
    @Override
    public byte[] get(String url) {
        byte[] ret = null;
        //如果地址为空，则直接返回null
        if (TextUtils.isEmpty(url)) {
            return ret;
        }
        //获取到这个文件的对象
        String fileName = getFileName(url);
        File file = new File(folder, fileName);
        //获取到文件里的内容
        ret = getDataFromFile(file);
        return ret;
    }

    /**
     * 获取到文件里面的内容
     *
     * @param file
     * @return
     */
    private byte[] getDataFromFile(File file) {
        byte[] ret = null;
        //如果不存在，或者不是一个文件，直接返回
        if (!file.exists() && file.isFile()) {
            return ret;
        }
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[512];
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
            ret = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fis, bos);
        }
        return ret;
    }

    /**
     * 关闭流
     *
     * @param closeables
     */
    private void closeStream(Closeable... closeables) {
        if (closeables != null) {
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
     * 获取到图片的文件名字
     *
     * @return 文件名
     * 例如：http：//www.baidu.com/pretty.jpg
     * pretty.jpg
     */
    private String getFileName(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        //获取到url 也是一种uri，所以可以通过这个方法，获取到最后一块的文件名
        String lastPathSegment = Uri.parse(url).getLastPathSegment();
        //如果图片没有最后的文件名则把url地址作为文件名
        if (lastPathSegment == null) {
            return url;
        }
        return lastPathSegment;
    }
}
