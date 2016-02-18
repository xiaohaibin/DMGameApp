package com.stx.xhb.dmgameapp.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;

import com.stx.xhb.dmgameapp.utils.DownImage;

/**
 * Created by xhb on 2016/1/9.
 * 图片的处理中心
 * 图片加载器
 * 当用户需要展示图片的时候：
 * 1.从内存缓存中获取图片
 * 若存在，直接返回给用户
 * <p/>
 * 2.从磁盘缓存中获取图片
 * 如果存在，直接返回给用户，并且添加到内存缓存里面去
 * 3.从网络获取数据
 * 下载成功之后，存储到磁盘缓存中去，存储到内存缓存中去
 */
public class ImageLoader {
    //一个对象，类加载的时候，就实例化了
    private static ImageLoader imageLoader = new ImageLoader();
    //这里采取单例的模式，因为这个类，只需要存在一个对象就ok了
    //LruCache,DiskCache
    private ILruCache iLruCache;
    private IDiskCache iDiskCache;

    private ImageLoader() {
        iLruCache = new LruCacheImpl();
        iDiskCache = new DiskCacheImpl();
    }

    //获取到这个单例对象
    public static ImageLoader getInstance() {
        return imageLoader;
    }

    //优化方法
    //这个方法，只需要传递ImageView和图片的地址，就可以实现展示
    public void disPlay(final ImageView iv, String url) {

        getBitmap(url, new OnBitmapFetchListener() {
            @Override
            public void onFetch(Bitmap bitmap) {
                //主线程
                iv.setImageBitmap(bitmap);
            }
        });

    }

    /**
     * 在listview里面展示，可以设置默认图片，自动设置tag
     *
     * @param iv
     * @param url
     * @param defaultImage
     */
    public void display(final ImageView iv, final String url, int defaultImage) {
        //设置Tag
        iv.setTag(url);
        //设置默认图片
        iv.setImageResource(defaultImage);
        //设置图片
        getBitmap(url, new OnBitmapFetchListener() {
            @Override
            public void onFetch(Bitmap bitmap) {
                if (url.equals(iv.getTag())) {
                    //判断tag是否相同，如果不同，说明listview已经经过滑动，
                    // 布局重用了，这时候，就不需要显示以前那张图片了，
                    //相同，说明没有滑动，或者是刚好滑动回来，依然是以前的tag的时候
                    iv.setImageBitmap(bitmap);
                }
            }
        });
    }


    //通过遍历二级缓存里面的缓存，获取到想到的数据
    public void getBitmap(String url, OnBitmapFetchListener listener) {
        if (listener == null || TextUtils.isEmpty(url)) {
            return;
        }
        Bitmap ret = null;
        //1.从内存缓存获取
        ret = iLruCache.get(url);
        if (ret != null) {
            listener.onFetch(ret);
            return;
        }
        //2.从磁盘缓存中获取
        //3.从网络中获取
        MyLosdTask myLosdTask = new MyLosdTask(listener);
        myLosdTask.execute(url);

    }

    //加载数据的异步任务
    class MyLosdTask extends AsyncTask<String, Void, byte[]> {
        private OnBitmapFetchListener listener;
        private String url;
        //判断是否是从磁盘中获取的
        private boolean isFromDisk;

        public MyLosdTask(OnBitmapFetchListener listener) {
            this.listener = listener;
        }

        @Override
        protected byte[] doInBackground(String... params) {
            byte[] ret = null;
            //2.从磁盘缓存中获取
            url = params[0];
            ret = iDiskCache.get(url);
            if (ret != null) {
                isFromDisk = true;
                return ret;
            }
            //3.从网络中获取
            ret = DownImage.downLoad(url);

            return ret;
        }


        //这个方法是执行在主线程的，所以，在这个方法里面进行回调
        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            //将内容存储到二级缓存中
            //判断数据是否为空
            if (bytes == null) {
                return;
            }
            //如果不是从磁盘中获取的，就保存到磁盘缓存中
            if (!isFromDisk) {
                iDiskCache.put(url, bytes);
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            iLruCache.put(url, bitmap);
            //回调数据，这里是主线程，可以直接用
            listener.onFetch(bitmap);
        }
    }
}
