package com.stx.xhb.dmgameapp.entity;

/**
 * Created by xhb on 2016/1/19.
 * 文章列表实体类
 */
public class ChapterListItem {


    private String id;
    private String typeid;
    private String typeid2;
    private String sortrank;
    private String flag;
    private String ismake;
    private String channel;
    private String arcrank;
    private String click;
    private String money;
    private String title;
    private String shorttitle;
    private String color;
    private String writer;
    private String source;
    private String litpic;
    private String pubdate;
    private String senddate;
    private String mid;
    private String keywords;
    private String lastpost;
    private String scores;
    private String goodpost;
    private String badpost;
    private String voteid;
    private String notpost;
    private String description;
    private String filename;
    private String dutyadmin;
    private String tackid;
    private String mtype;
    private String weight;
    private String fby_id;
    private String game_id;
    private String feedback;
    private String typedir;
    private String typename;
    private String corank;
    private String isdefault;
    private String defaultname;
    private String namerule;
    private String namerule2;
    private String ispart;
    private String moresite;
    private String siteurl;
    private String sitepath;
    private String arcurl;
    private String typeurl;

    public ChapterListItem(String id, String typeid, String title, String senddate, String litpic, String feedback, String arcurl) {
        this.id = id;//文章id
        this.typeid = typeid;//分类id
        this.title = title;//文章标题
        this.senddate = senddate;//发布时间
        this.litpic = litpic;//文章缩略图地址
        this.feedback = feedback;//评论数
        this.arcurl = arcurl;//文章网页地址
    }

    public String getArcrank() {
        return arcrank;
    }

    public void setArcrank(String arcrank) {
        this.arcrank = arcrank;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }

    public String getBadpost() {
        return badpost;
    }

    public void setBadpost(String badpost) {
        this.badpost = badpost;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCorank() {
        return corank;
    }

    public void setCorank(String corank) {
        this.corank = corank;
    }

    public String getDefaultname() {
        return defaultname;
    }

    public void setDefaultname(String defaultname) {
        this.defaultname = defaultname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDutyadmin() {
        return dutyadmin;
    }

    public void setDutyadmin(String dutyadmin) {
        this.dutyadmin = dutyadmin;
    }

    public String getFby_id() {
        return fby_id;
    }

    public void setFby_id(String fby_id) {
        this.fby_id = fby_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getGoodpost() {
        return goodpost;
    }

    public void setGoodpost(String goodpost) {
        this.goodpost = goodpost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getIsmake() {
        return ismake;
    }

    public void setIsmake(String ismake) {
        this.ismake = ismake;
    }

    public String getIspart() {
        return ispart;
    }

    public void setIspart(String ispart) {
        this.ispart = ispart;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLastpost() {
        return lastpost;
    }

    public void setLastpost(String lastpost) {
        this.lastpost = lastpost;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoresite() {
        return moresite;
    }

    public void setMoresite(String moresite) {
        this.moresite = moresite;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getNamerule2() {
        return namerule2;
    }

    public void setNamerule2(String namerule2) {
        this.namerule2 = namerule2;
    }

    public String getNamerule() {
        return namerule;
    }

    public void setNamerule(String namerule) {
        this.namerule = namerule;
    }

    public String getNotpost() {
        return notpost;
    }

    public void setNotpost(String notpost) {
        this.notpost = notpost;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getShorttitle() {
        return shorttitle;
    }

    public void setShorttitle(String shorttitle) {
        this.shorttitle = shorttitle;
    }

    public String getSitepath() {
        return sitepath;
    }

    public void setSitepath(String sitepath) {
        this.sitepath = sitepath;
    }

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public String getSortrank() {
        return sortrank;
    }

    public void setSortrank(String sortrank) {
        this.sortrank = sortrank;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTackid() {
        return tackid;
    }

    public void setTackid(String tackid) {
        this.tackid = tackid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypedir() {
        return typedir;
    }

    public void setTypedir(String typedir) {
        this.typedir = typedir;
    }

    public String getTypeid2() {
        return typeid2;
    }

    public void setTypeid2(String typeid2) {
        this.typeid2 = typeid2;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypeurl() {
        return typeurl;
    }

    public void setTypeurl(String typeurl) {
        this.typeurl = typeurl;
    }

    public String getVoteid() {
        return voteid;
    }

    public void setVoteid(String voteid) {
        this.voteid = voteid;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}


