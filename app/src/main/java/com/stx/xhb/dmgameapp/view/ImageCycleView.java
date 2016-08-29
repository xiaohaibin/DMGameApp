package com.stx.xhb.dmgameapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stx.xhb.dmgameapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * <Pre>
 * 自定义图片自动轮播控件，自定轮播指示器样式，支持点击，无限轮播，网络下载图片</br>
 * 可是使用XUtil的BitmapUtils也可是使用smart-image-view加载图片，支持轮播文字切换</br>
 * 此插件是基于viewpager实现的,需要导入android-support-v4.jar</br></br>
 * <p/>
 * 如果使用网络图片记得加权限。</br>
 * uses-permission android:name="android.permission.INTERNET"
 * uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
 * <p/>
 * 主要功能:</br></br>
 * 1.支持设置文字提示</br>
 * 2.支持修改轮播指示器的样式及位置（修改view_cycle_image.xml样式,不能修改id）</br>
 * 3.支持修改文字提样式及位置（修改view_cycle_image.xml样式,不能修改id）</br>
 * 4.支持设置是否开启自动轮播</br>
 * 5.支持运行中启动和停止自动轮播</br>
 * 6.支持网络加载图片，资源图片id，sd卡图片</br>
 * 7.设置支持XUtil的BitmapUtils也可是使用smart-image-view加载图片</br>
 * 8.支持点击事件</br>
 * 9.默认是第一张</br></br>
 */
public class ImageCycleView extends FrameLayout {

    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 图片轮播视图
     */
    private ImageCycleViewPager mViewPager;
    /**
     * 数据集合
     * Map<String,String> map=new HashMap<String, String>();
     * map.put("","");
     */
    private List<ImageInfo> data = new ArrayList<ImageInfo>();
    /**
     * 加载图片回调函数
     */
    private LoadImageCallBack mLoadImageCallBack;

    /**
     * 图片轮播指示器容器
     */
    private LinearLayout mIndicationGroup;
    /**
     * 轮播的总数
     */
    private int mCount = 0;
    /**
     * 未获得焦点指示器资源
     */
    private Bitmap unFocusIndicationStyle;
    /**
     * 获得焦点指示器资源
     */
    private Bitmap focusIndicationStyle;
    /**
     * 指示器间距相对于自身的百分比,默认间距为指示器高度的1/2
     */
    private float indication_self_margin_percent = 0.5f;
    /**
     * 单击事件监听器
     */
    private OnPageClickListener mOnPageClickListener;
    /**
     * 图片文本提示
     */
    private TextView mText;
    /**
     * 图片轮播是自动滚动状态  true 自动滚动，false 图片不能自动滚动只能手动左右滑动
     */
    private boolean isAutoCycle = true;
    /**
     * 自动轮播时间间隔默认3秒
     */
    private long mCycleDelayed = 3000;
    /**
     * 实现自动轮播
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(0, mCycleDelayed);
            }
            return false;
        }
    });

    public ImageCycleView(Context context) {
        super(context);
        init(context);
    }

    public ImageCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化基础信息
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        unFocusIndicationStyle = drawCircle(50, Color.GRAY);
        focusIndicationStyle = drawCircle(50, Color.WHITE);
        initView();
    }

    /**
     * 初始化view控件
     */
    private void initView() {
        View.inflate(mContext, R.layout.view_image_cycle, this);
        FrameLayout fl_image_cycle = (FrameLayout) findViewById(R.id.fl_image_cycle);
        mViewPager = new ImageCycleViewPager(mContext);
        mViewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        fl_image_cycle.addView(mViewPager);
        mViewPager.setOnPageChangeListener(new ImageCyclePageChangeListener());
        mIndicationGroup = (LinearLayout) findViewById(R.id.ll_indication_group);
        mText = (TextView) findViewById(R.id.tv_text);
    }

    /**
     * 设置轮播指示器样式，如果你对默认的样式不满意可以自己设置
     *
     * @param indicationStyle         资源类型,color,image,shape
     * @param unFocus                 未获得焦点指示器资源id  图片或shape或color值
     * @param focus                   获得焦点指示器资源id 图片或shape或color值
     * @param indication_self_percent 自身高度的百分比 >=0f
     */
    public void setIndicationStyle(IndicationStyle indicationStyle, int unFocus, int focus, float indication_self_percent) {
        if (indicationStyle == IndicationStyle.COLOR) {
            unFocusIndicationStyle = drawCircle(50, unFocus);
            focusIndicationStyle = drawCircle(50, focus);
        } else if (indicationStyle == IndicationStyle.IMAGE) {
            unFocusIndicationStyle = BitmapFactory.decodeResource(mContext.getResources(), unFocus);
            focusIndicationStyle = BitmapFactory.decodeResource(mContext.getResources(), focus);
        }
        indication_self_margin_percent = indication_self_percent;
        initIndication();
    }

    /**
     * 设置是否自动无限轮播
     *
     * @param delayed 自动轮播时间间隔
     */
    public void setCycleDelayed(long delayed) {
        mCycleDelayed = delayed;
    }

    /**
     * 设置是否自动无限轮播
     *
     * @param state
     */
    public void setAutoCycle(Boolean state) {
        isAutoCycle = state;
    }

    /**
     * 加载显示的数据  网络图片资源及标题
     *
     * @param list     数据
     * @param callBack 如何加载图片及显示的回调方法 not null
     */
    public void loadData(List<ImageInfo> list, LoadImageCallBack callBack) {
        data = list;
        mCount = list.size();
        initIndication();
        if (callBack == null) {
            new IllegalArgumentException("LoadImageCallBack 回调函数不能为空！");
        }
        mLoadImageCallBack = callBack;
        mViewPager.setAdapter(new ImageCycleAdapter());
        //最大值中间 的第一个
        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % mCount));
    }

    /**
     * 设置点击事件监听回调函数
     *
     * @param listener
     */
    public void setOnPageClickListener(OnPageClickListener listener) {
        mOnPageClickListener = listener;
    }

    /**
     * 初始化指标器
     */
    private void initIndication() {
        mIndicationGroup.removeAllViews();
        for (int i = 0; i < mCount; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicationGroup.getLayoutParams().height, LinearLayout.LayoutParams.MATCH_PARENT);
            params.leftMargin = (int) (mIndicationGroup.getLayoutParams().height * indication_self_margin_percent);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setImageBitmap(focusIndicationStyle);
            } else {
                imageView.setImageBitmap(unFocusIndicationStyle);
            }
            mIndicationGroup.addView(imageView);
        }
    }

    private Bitmap drawCircle(int radius, int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);// 设置颜色
        Bitmap bitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        return bitmap;
    }

    /**
     * 开始图片轮播
     */
    private void startImageCycle() {
        handler.sendEmptyMessageDelayed(0, mCycleDelayed);
    }

    /**
     * 暂停图片轮播
     */
    private void stopImageCycle() {
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 触摸停止计时器，抬起启动计时器
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isAutoCycle) {
                // 开始图片滚动
                startImageCycle();
            }
        } else {
            if (isAutoCycle) {
                // 停止图片滚动
                stopImageCycle();
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 停止图片滚动
        stopImageCycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isAutoCycle) {
            startImageCycle();
        }
    }


    public enum IndicationStyle {
        COLOR, IMAGE
    }

    /**
     * 轮播控件的监听事件
     */
    public interface OnPageClickListener {
        /**
         * 单击图片事件
         *
         * @param imageView 被点击的View对象
         * @param imageInfo 数据信息
         */
        void onClick(View imageView, ImageInfo imageInfo);
    }

    /**
     * 加载图片并显示回调接口
     */
    public interface LoadImageCallBack {
        /**
         * 自己如何设置加载图片
         *
         * @param imageInfo 数据信息
         */
        ImageView loadAndDisplay(ImageInfo imageInfo);
    }

    public static class ImageInfo {
        public Object image;
        public String text = "";
        public Object value;

        public ImageInfo(Object image, String text, Object value) {
            this.image = image;
            this.text = text;
            this.value = value;
        }
    }

    /**
     * 轮播图片监听
     */
    private final class ImageCyclePageChangeListener implements OnPageChangeListener {

        //上次指示器指示的位置,开始为默认位置0
        private int preIndex = 0;

        @Override
        public void onPageSelected(int index) {
            index = index % mCount;
            //更新文本信息
            String text = data.get(index).text;
            mText.setText(TextUtils.isEmpty(text) ? "" : text);
            //恢复默认没有获得焦点指示器样式
            ((ImageView) (mIndicationGroup.getChildAt(preIndex))).setImageBitmap(unFocusIndicationStyle);
            // 设置当前显示图片的指示器样式
            ((ImageView) (mIndicationGroup.getChildAt(index))).setImageBitmap(focusIndicationStyle);
            preIndex = index;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }
    }

    /**
     * 图片轮播适配器
     */
    private class ImageCycleAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            final ImageInfo imageInfo = data.get(position % mCount);

            ImageView imageView = mLoadImageCallBack.loadAndDisplay(imageInfo);
            if (imageView!=null) {
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                // 设置图片点击监听
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnPageClickListener != null) {
                            mOnPageClickListener.onClick(v, imageInfo);
                        }
                    }
                });

                container.addView(imageView);
            }
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    /**
     * 自定义ViewPager主要用于事件处理
     */
    public class ImageCycleViewPager extends ViewPager {

        public ImageCycleViewPager(Context context) {
            super(context);
        }

        public ImageCycleViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        /**
         * 事件拦截
         */
        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return super.onInterceptTouchEvent(ev);
        }

        /**
         * 事件分发
         */
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.dispatchTouchEvent(ev);
        }

        /**
         * 事件处理
         */
        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            return super.onTouchEvent(ev);
        }


    }

}
