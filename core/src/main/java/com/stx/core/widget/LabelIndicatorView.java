package com.stx.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stx.core.R;


/**
 * Created by jxnk25 on 2016/11/7.
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 自定义组合控件实现 个人中心常见标签项效果(无icon图标，只有文字)
 */
public class LabelIndicatorView extends ViewGroup {

    private View contentView;
    private TextView labelTextView,tipTextView;
    private ImageView indicatorImageView;

    public LabelIndicatorView(Context context) {
        super(context);
        init(null);
    }

    public LabelIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        contentView.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    private void init(AttributeSet attrs) {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.layout_widget_label_indicator_view, null);
        labelTextView = (TextView) contentView.findViewById(R.id.label);
        tipTextView = (TextView) contentView.findViewById(R.id.tips);
        indicatorImageView = (ImageView) contentView.findViewById(R.id.indicator);
        addView(contentView);

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LabelIndicatorView);
            labelTextView.setText(typedArray.getString(R.styleable.LabelIndicatorView_label_title));
            tipTextView.setText(typedArray.getString(R.styleable.LabelIndicatorView_label_tips));
            indicatorImageView.setImageResource(typedArray.getResourceId(R.styleable.LabelIndicatorView_label_Indicator, 0));
            typedArray.recycle();
        }
    }

    public void setTipText(String text) {
        if (tipTextView != null) {
            tipTextView.setText(text);
        }
    }

    public void setTipColor(@ColorRes int color) {
        if (tipTextView != null) {
            tipTextView.setTextColor(getResources().getColor(color));
        }
    }

}
