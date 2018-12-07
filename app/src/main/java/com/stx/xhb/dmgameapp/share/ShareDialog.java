package com.stx.xhb.dmgameapp.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stx.core.base.BaseDialogFragment;
import com.stx.core.utils.ScreenUtil;
import com.stx.xhb.dmgameapp.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;


/**
 * Author:xiaohaibin
 * <p>
 * CrateTime:2017-05-05
 * <p>
 * Description:分享
 */
public class ShareDialog extends BaseDialogFragment implements View.OnClickListener {

    private RecyclerView channelRecyclerView;
    private TextView cancelButton;
    private List<ShareChannel> channels;
    private ShareAdapter shareAdapter;
    private String imageUrl;
    private String url;
    private String content;
    private String title;

    public static void share(FragmentManager manager, String title, String url, String content, String imageUrl) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("url", url);
        args.putString("content", content);
        args.putString("image", imageUrl);
        ShareDialog fragment = new ShareDialog();
        fragment.setArguments(args);
        fragment.show(manager, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        init();
        findViews();
    }

    private void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            url = bundle.getString("url");
            content = bundle.getString("content");
            imageUrl = bundle.getString("image");
        }
        channels = new ArrayList<>();
        channels.add(new ShareChannel(R.drawable.icon_wx_friend, "微信"));
        channels.add(new ShareChannel(R.drawable.icon_wx_circle, "朋友圈"));
        channels.add(new ShareChannel(R.drawable.icon_share_qq, "QQ"));
        channels.add(new ShareChannel(R.drawable.icon_share_qoze, "QQ空间"));
        shareAdapter = new ShareAdapter(getActivity()) {

            @Override
            public void bindItem(ShareAdapter.Item item, int position) {
                final ShareChannel channel = channels.get(position);
                item.iconImageView.setImageResource(channel.getIcon());
                item.nameTextView.setText(channel.getName());
            }

            @Override
            public void onItemClick(int position) {
                shareTo(position);
            }

            @Override
            public int getItemCount() {
                return channels.size();
            }
        };
    }

    private void shareTo(int position) {
        final String channel = channels.get(position).getName();
        switch (channel) {
            case "微信":
                ShareUtils.getInstance().sharePlatform(getActivity(),
                        SHARE_MEDIA.WEIXIN, url,
                        title,
                        content,
                        TextUtils.isEmpty(imageUrl) ? new UMImage(getActivity(), R.mipmap.ic_logo) : new UMImage(getActivity(), imageUrl)
                );
                dismiss();
                break;
            case "QQ":
                ShareUtils.getInstance().sharePlatform(
                        getActivity(),
                        SHARE_MEDIA.QQ,
                        url,
                        title,
                        content,
                        TextUtils.isEmpty(imageUrl) ? new UMImage(getActivity(), R.mipmap.ic_logo) : new UMImage(getActivity(), imageUrl)
                );
                dismiss();
                break;
            case "朋友圈":
                ShareUtils.getInstance().sharePlatform(getActivity(),
                        SHARE_MEDIA.WEIXIN_CIRCLE, url,
                        title,
                        content,
                        TextUtils.isEmpty(imageUrl) ? new UMImage(getActivity(), R.mipmap.ic_logo) : new UMImage(getActivity(), imageUrl)
                );
                dismiss();
                break;
            case "QQ空间":
                ShareUtils.getInstance().sharePlatform(
                        getActivity(),
                        SHARE_MEDIA.QZONE,
                        url,
                        title,
                        content,
                        TextUtils.isEmpty(imageUrl) ? new UMImage(getActivity(), R.mipmap.ic_logo) : new UMImage(getActivity(), imageUrl)
                );
                dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    protected void findViews() {
        channelRecyclerView = findView(R.id.share_channel);
        cancelButton = findView(R.id.share_cancel);
        channelRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        channelRecyclerView.setAdapter(shareAdapter);
        cancelButton.setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }
}
