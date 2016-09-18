package com.android.beautifulthing.CommonActivity.bean;

/**
 * Created by ydy on 2016/9/18.
 */
public class ApkUpdateBean {
    /**
     * update_desc : 1.画报增加作者展示及申请成为作者功能；<br/>2.画报及商品详情增加设计师及作品展示，增加评论展示；<br/>3.修复了程序猿搞出来的 bug
     * apk_url : http://apps.wandoujia.com/redirect?signature=fba27c2&url=http%3A%2F%2Fapk.wandoujia.com%2Fd%2F91%2F85510f6cb746083dbbfda1df5ea3e91d.apk&pn=com.zuiapps.zuiworld&md5=85510f6cb746083dbbfda1df5ea3e91d&apkid=19341512&vc=10182&size=9537858&tokenId=zuimeiyingyong&pos=t%2Fdetail
     * update_type : 3
     * last_ver_code : 10182
     */

    private DataBean data;
    /**
     * data : {"update_desc":"1.画报增加作者展示及申请成为作者功能；<br/>2.画报及商品详情增加设计师及作品展示，增加评论展示；<br/>3.修复了程序猿搞出来的 bug","apk_url":"http://apps.wandoujia.com/redirect?signature=fba27c2&url=http%3A%2F%2Fapk.wandoujia.com%2Fd%2F91%2F85510f6cb746083dbbfda1df5ea3e91d.apk&pn=com.zuiapps.zuiworld&md5=85510f6cb746083dbbfda1df5ea3e91d&apkid=19341512&vc=10182&size=9537858&tokenId=zuimeiyingyong&pos=t%2Fdetail","update_type":3,"last_ver_code":10182}
     * result : 1
     */

    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private String update_desc;
        private String apk_url;
        private int update_type;
        private int last_ver_code;

        public String getUpdate_desc() {
            return update_desc;
        }

        public void setUpdate_desc(String update_desc) {
            this.update_desc = update_desc;
        }

        public String getApk_url() {
            return apk_url;
        }

        public void setApk_url(String apk_url) {
            this.apk_url = apk_url;
        }

        public int getUpdate_type() {
            return update_type;
        }

        public void setUpdate_type(int update_type) {
            this.update_type = update_type;
        }

        public int getLast_ver_code() {
            return last_ver_code;
        }

        public void setLast_ver_code(int last_ver_code) {
            this.last_ver_code = last_ver_code;
        }
    }
}
