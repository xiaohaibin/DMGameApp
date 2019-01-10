package com.stx.xhb.dmgameapp.mvp.ui.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.stx.core.base.BaseDialogFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.core.utils.SoftKeyBoardUtils;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

/**
 * @author: xiaohaibin.
 * @time: 2019/1/10
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 评论对话框
 */
public class PostCommentDilaog extends BaseDialogFragment {


    private EditText mEditText;

    public static PostCommentDilaog newInstance() {
        return new PostCommentDilaog();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_post_comment);
        findViews();
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return new ViewGroup.LayoutParams(ScreenUtil.getScreenWidth(getContext()), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void findViews() {
        mEditText = findView(R.id.ed_comment);
        mEditText.postDelayed(new Runnable() {
            @Override
            public void run() {
                SoftKeyBoardUtils.showSoftKeyboard(mEditText);
            }
        }, 400);

        findView(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.show("请输入评论内容");
                    return;
                }
                if (mOnPostCommentListener != null) {
                    mOnPostCommentListener.onPost(content);
                }
            }
        });
    }

    public void  clearContent(){
        mEditText.setText("");
    }

    private onPostCommentListener mOnPostCommentListener;

    public void setOnPostCommentListener(onPostCommentListener onPostCommentListener) {
        mOnPostCommentListener = onPostCommentListener;
    }

    public interface onPostCommentListener {
        void onPost(String content);
    }
}
