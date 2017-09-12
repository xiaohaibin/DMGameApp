package com.stx.xhb.dmgameapp.share;

/**
 * Author:xiaohaibin
 * <p>
 * CrateTime:2016-12-15 11:06
 * <p>
 * Description:分享渠道
 */
public class ShareChannel {

    private int icon;
    private String name;

    public ShareChannel(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

}
