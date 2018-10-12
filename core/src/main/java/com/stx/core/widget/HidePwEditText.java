package com.stx.core.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.stx.core.R;


/**
 * Created by xhb on 2016/8/17.
 * 自定View实现点击图标 具有隐藏/显示密码功能的EditText
 * 可自定义设置隐藏/显示的图标
 */
@SuppressLint("AppCompatCustomView")
public class HidePwEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    private int hideImage;
    private int showImage;
    /**
     * 输入框右侧图标
     */
    private Drawable hideDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;
    /**
     * 输入框内容是否隐藏
     */
    private boolean isHide = true;

    private onCallBackListener listener;

    public HidePwEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HidePwEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public HidePwEditText(Context context) {
        super(context);
    }

    public void init(AttributeSet attrs) {
        hideDrawable = getCompoundDrawables()[2];
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HidePwEditText);
            hideImage = typedArray.getResourceId(R.styleable.HidePwEditText_hideDrawable, R.drawable.eye_close);
            showImage = typedArray.getResourceId(R.styleable.HidePwEditText_showDrawable, R.drawable.eye_open);
            typedArray.recycle();
        } else {
            hideImage = R.drawable.eye_close;
            showImage = R.drawable.eye_open;
        }
        //默认设置隐藏图片
        setHideDrawable(hideImage);
        //默认设置隐藏图标
        setHideDrawableVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    /**
     * 设置右侧隐藏/显示图标
     *
     * @param resId
     */
    private void setHideDrawable(@DrawableRes int resId) {
        hideDrawable = getResources().getDrawable(resId);
        if (hideDrawable != null) {
            hideDrawable.setBounds(0, 0, hideDrawable.getIntrinsicWidth(), hideDrawable.getIntrinsicHeight());
        }
    }

    private void setHideDrawableVisible(boolean isVisible) {
        Drawable right = isVisible ? hideDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setHideDrawableVisible(getText().length() > 0);
        } else {
            setHideDrawableVisible(false);
        }
        if (listener != null) {
            listener.onFocusChange(hasFocus);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                    && (event.getX() < ((getWidth() - getPaddingRight())));
            if (touchable) {
                if (isHide) {
                    setHideDrawable(hideImage);
                    this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isHide = false;
                } else {
                    setHideDrawable(showImage);
                    this.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isHide = true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (hasFoucs) {
            setHideDrawableVisible(text.length() > 0);
            setSelection(text.length());
        }
        if (listener != null) {
            listener.onTextChange(text);
        }
    }

    public void setListener(onCallBackListener listener) {
        this.listener = listener;
    }

    /**
     * 接口回调，方便在输入框的事件监听中处理其他事件
     */
    public interface onCallBackListener {
        void onTextChange(CharSequence s);

        void onFocusChange(boolean hasFocus);
    }
}
