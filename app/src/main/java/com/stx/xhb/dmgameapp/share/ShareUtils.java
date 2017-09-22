package com.stx.xhb.dmgameapp.share;

import android.app.Activity;

import com.stx.xhb.dmgameapp.utils.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;


/**
 * Created by jxnk25 on 2016/12/23.
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description： 分享工具类
 */
public class ShareUtils {

    private static ShareUtils shareUtils;

    public static ShareUtils getInstance() {
        if (shareUtils == null) {
            synchronized (ShareUtils.class) {
                if (shareUtils == null) {
                    shareUtils = new ShareUtils();
                }
            }
        }
        return shareUtils;
    }

    /**
     * 第三方平台分享
     *
     * @param activity
     * @param platform 分享平台  SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN SHARE_MEDIA.WEIXIN_CIRCLE
     * @param url
     * @param title
     * @param text
     * @param image
     */
    public void sharePlatform(final Activity activity, final SHARE_MEDIA platform, final String url, final String title, final String text, final UMImage image) {
        //由于qq空间分享友盟判断有问题，暂时这么处理
        if (platform != SHARE_MEDIA.QZONE && !UMShareAPI.get(activity).isInstall(activity, platform)) {
            switch (platform) {
                case QQ:
                case QZONE:
                    ToastUtil.show("尚未安装QQ,请先安装QQ");
                    break;
                case WEIXIN:
                case WEIXIN_CIRCLE:
                    ToastUtil.show("尚未安装微信,请先安装微信");
                    break;
                case SINA:
                    ToastUtil.show("尚未安装新浪微博,请先安装新浪微博");
                    break;
            }
            return;
        }
        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(new UMWeb(url,title,text,image))
                .share();
    }

}

