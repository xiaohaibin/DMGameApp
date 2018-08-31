package com.stx.xhb.dmgameapp.mvp.ui.main;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.stx.core.base.BaseMvpFragment;
import com.stx.core.mvp.IPresenter;
import com.stx.core.widget.LabelIndicatorView;
import com.stx.xhb.dmgameapp.R;
import com.stx.xhb.dmgameapp.data.entity.UserInfoBean;
import com.stx.xhb.dmgameapp.mvp.ui.activity.AboutActivity;
import com.stx.xhb.dmgameapp.mvp.ui.activity.LoginActivity;
import com.stx.xhb.dmgameapp.mvp.ui.activity.SettingActivity;
import com.stx.xhb.dmgameapp.utils.AppUser;
import com.stx.xhb.dmgameapp.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人中心Fragment
 */
public class UserFragment extends BaseMvpFragment {

    @Bind(R.id.iv_user_img)
    PorterShapeImageView mIvUserImg;
    @Bind(R.id.tv_account)
    TextView mTvAccount;
    @Bind(R.id.tv_info)
    TextView mTvInfo;
    @Bind(R.id.ll_login_info)
    LinearLayout mLlLoginInfo;
    @Bind(R.id.ll_no_login)
    LinearLayout mLlNoLogin;
    @Bind(R.id.tv_collect)
    TextView mTvCollect;
    @Bind(R.id.tv_message)
    TextView mTvMessage;
    @Bind(R.id.btn_setting)
    LabelIndicatorView mBtnSetting;
    @Bind(R.id.btn_about)
    LabelIndicatorView mBtnAbout;
    @Bind(R.id.btn_login_out)
    TextView mBtnLoginOut;

    public static UserFragment newInstance() {
        return new UserFragment();
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        showUserInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        showUserInfo();
    }

    @OnClick({R.id.ll_no_login, R.id.tv_collect, R.id.tv_message, R.id.btn_setting, R.id.btn_about, R.id.btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_no_login://登录
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_collect://收藏
                ToastUtil.show("收藏");
                break;
            case R.id.tv_message://消息
                ToastUtil.show("消息");
                break;
            case R.id.btn_setting://设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.btn_about://关于
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.btn_login_out://退出登录
                AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("退出提示")
                        .setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppUser.logout();
                                showUserInfo();
                                dialog.dismiss();
                            }
                        }).setMessage("确定要退出登录？").create();
                dialog.show();
                break;
            default:
                break;
        }
    }

    private void showUserInfo() {
        if (AppUser.isLogin()) {
            mLlLoginInfo.setVisibility(View.VISIBLE);
            mLlNoLogin.setVisibility(View.GONE);
            mBtnLoginOut.setVisibility(View.VISIBLE);
            UserInfoBean userInfoBean = AppUser.getUserInfoBean();
            if (userInfoBean.getHtml() != null) {
                mTvAccount.setText(userInfoBean.getHtml().getUsername());
                mTvInfo.setText("等级：" + userInfoBean.getHtml().getGrouptitle() + "\n"
                        + "积分：" + userInfoBean.getHtml().getCredits() + "\n"
                        + "帖子：" + userInfoBean.getHtml().getPosts());
            }
            Glide.with(getContext()).load(userInfoBean.getHtml().getAuthimg()).into(mIvUserImg);
        } else {
            mLlNoLogin.setVisibility(View.VISIBLE);
            mLlLoginInfo.setVisibility(View.GONE);
            mBtnLoginOut.setVisibility(View.GONE);
        }
    }

    @Override
    protected IPresenter onLoadPresenter() {
        return null;
    }
}
