package com.stx.xhb.dmgameapp.entity;

import java.util.List;

/**
 * Created by xhb on 2016/1/22.
 * 评论列表实体
 */
public class ChapterCommentEntity {

    /**
     * code : 1
     * description : {"data":[{"id":"5585977","aid":"2315431","typeid":"0","username":"匿名发表","ip":"124.234.248.226","ip1":"124234250234144","ip2":"7cb8af67a5ab95d","ischeck":"1","dtime":"1449061212","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"这是武士嘛。。。。","cid":"","reid":"0","topid":"0","floor":"9","reply":"0"},{"id":"5533817","aid":"2315431","typeid":"0","username":"匿名发表","ip":"120.131.73.42","ip1":"120131734214468","ip2":"9ef659b7bb20ccc","ischeck":"1","dtime":"1446810529","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"saswqwqw","cid":"","reid":"0","topid":"0","floor":"8","reply":"0"},{"id":"5408178","aid":"2315431","typeid":"0","username":"汪汪","ip":"117.114.151.171","ip1":"","ip2":"","ischeck":"1","dtime":"1442802026","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"今天天气真不错！！","cid":"","reid":"0","topid":"0","floor":"7","reply":"0"},{"id":"5322844","aid":"2315431","typeid":"0","username":"鍖垮悕","ip":"101.81.87.177","ip1":"","ip2":"","ischeck":"1","dtime":"1439779789","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"鎸哄ソ鐨勬枃绔�","cid":"","reid":"0","topid":"0","floor":"6","reply":"0"},{"id":"5140219","aid":"2315431","typeid":"0","username":"匿名","ip":"180.153.228.91","ip1":"","ip2":"","ischeck":"1","dtime":"1433750441","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"5","reply":"0"},{"id":"4942379","aid":"2315431","typeid":"0","username":"匿名","ip":"117.136.0.225","ip1":"","ip2":"","ischeck":"1","dtime":"1425709835","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"4","reply":"0"},{"id":"4942377","aid":"2315431","typeid":"0","username":"匿名","ip":"117.136.0.225","ip1":"","ip2":"","ischeck":"1","dtime":"1425709699","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"3","reply":"0"},{"id":"4942376","aid":"2315431","typeid":"0","username":"匿名","ip":"117.136.0.225","ip1":"","ip2":"","ischeck":"1","dtime":"1425709664","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"2","reply":"0"}],"paging":{"n":"1","size":8,"start":0,"total":"9","totalpage":2}}
     */

    private String code;
    /**
     * data : [{"id":"5585977","aid":"2315431","typeid":"0","username":"匿名发表","ip":"124.234.248.226","ip1":"124234250234144","ip2":"7cb8af67a5ab95d","ischeck":"1","dtime":"1449061212","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"这是武士嘛。。。。","cid":"","reid":"0","topid":"0","floor":"9","reply":"0"},{"id":"5533817","aid":"2315431","typeid":"0","username":"匿名发表","ip":"120.131.73.42","ip1":"120131734214468","ip2":"9ef659b7bb20ccc","ischeck":"1","dtime":"1446810529","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"saswqwqw","cid":"","reid":"0","topid":"0","floor":"8","reply":"0"},{"id":"5408178","aid":"2315431","typeid":"0","username":"汪汪","ip":"117.114.151.171","ip1":"","ip2":"","ischeck":"1","dtime":"1442802026","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"今天天气真不错！！","cid":"","reid":"0","topid":"0","floor":"7","reply":"0"},{"id":"5322844","aid":"2315431","typeid":"0","username":"鍖垮悕","ip":"101.81.87.177","ip1":"","ip2":"","ischeck":"1","dtime":"1439779789","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"鎸哄ソ鐨勬枃绔�","cid":"","reid":"0","topid":"0","floor":"6","reply":"0"},{"id":"5140219","aid":"2315431","typeid":"0","username":"匿名","ip":"180.153.228.91","ip1":"","ip2":"","ischeck":"1","dtime":"1433750441","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"5","reply":"0"},{"id":"4942379","aid":"2315431","typeid":"0","username":"匿名","ip":"117.136.0.225","ip1":"","ip2":"","ischeck":"1","dtime":"1425709835","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"4","reply":"0"},{"id":"4942377","aid":"2315431","typeid":"0","username":"匿名","ip":"117.136.0.225","ip1":"","ip2":"","ischeck":"1","dtime":"1425709699","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"3","reply":"0"},{"id":"4942376","aid":"2315431","typeid":"0","username":"匿名","ip":"117.136.0.225","ip1":"","ip2":"","ischeck":"1","dtime":"1425709664","mid":"0","bad":"0","good":"0","ftype":"","face":"0","msg":"挺好的文章","cid":"","reid":"0","topid":"0","floor":"2","reply":"0"}]
     * paging : {"n":"1","size":8,"start":0,"total":"9","totalpage":2}
     */

    private DescriptionEntity description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DescriptionEntity getDescription() {
        return description;
    }

    public void setDescription(DescriptionEntity description) {
        this.description = description;
    }

    public static class DescriptionEntity {
        /**
         * n : 1
         * size : 8
         * start : 0
         * total : 9
         * totalpage : 2
         */

        private PagingEntity paging;
        /**
         * id : 5585977
         * aid : 2315431
         * typeid : 0
         * username : 匿名发表
         * ip : 124.234.248.226
         * ip1 : 124234250234144
         * ip2 : 7cb8af67a5ab95d
         * ischeck : 1
         * dtime : 1449061212
         * mid : 0
         * bad : 0
         * good : 0
         * ftype :
         * face : 0
         * msg : 这是武士嘛。。。。
         * cid :
         * reid : 0
         * topid : 0
         * floor : 9
         * reply : 0
         */

        private List<DataEntity> data;

        public PagingEntity getPaging() {
            return paging;
        }

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public static class PagingEntity {
            private String n;
            private int size;
            private int start;
            private String total;
            private int totalpage;


            public int getSize() {
                return size;
            }

        }

        public static class DataEntity {
            private String id;
            private String aid;
            private String typeid;
            private String username;
            private String ip;
            private String ip1;
            private String ip2;
            private String ischeck;
            private String dtime;
            private String mid;
            private String bad;
            private String good;
            private String ftype;
            private String face;
            private String msg;
            private String cid;
            private String reid;
            private String topid;
            private String floor;
            private String reply;

            public String getId() {
                return id;
            }

            public String getTypeid() {
                return typeid;
            }

            public String getUsername() {
                return username;
            }

            public String getDtime() {
                return dtime;
            }

            public String getMsg() {
                return msg;
            }

            public String getFloor() {
                return floor;
            }

        }
    }
}
