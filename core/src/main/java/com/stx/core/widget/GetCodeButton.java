package com.stx.core.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class GetCodeButton extends TextView {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x10:
                    setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    public GetCodeButton(Context context) {
        super(context);
    }

    public GetCodeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param seconds seconds秒后重试
     */
    public void disableIn(int seconds) {
        setEnabled(false);
        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(0x10, seconds * 1000);
    }

}
