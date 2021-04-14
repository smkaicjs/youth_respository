package com.shimk.Txgc.bean;

import java.io.Serializable;

public class EverydayJavaBean {


    /**
     * code : 200
     * data : {"id":"2557","tag":"第七世界","origin":"R7CKY","content":"这个世界乍看之下无比美丽，却隐藏着现实的残酷。每个人总是带着虚假的面具，却无法掩盖内心的空虚。若你已经厌倦了一尘不变的生活，欢迎你来到属于我的第七世界。","datetime":"1544198545352"}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        /**
         * id : 2557
         * tag : 第七世界
         * origin : R7CKY
         * content : 这个世界乍看之下无比美丽，却隐藏着现实的残酷。每个人总是带着虚假的面具，却无法掩盖内心的空虚。若你已经厌倦了一尘不变的生活，欢迎你来到属于我的第七世界。
         * datetime : 1544198545352
         */

        private String id;
        private String tag;
        private String origin;
        private String content;
        private String datetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }
}
