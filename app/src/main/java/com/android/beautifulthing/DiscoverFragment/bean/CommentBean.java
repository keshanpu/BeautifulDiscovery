package com.android.beautifulthing.DiscoverFragment.bean;

import java.util.List;

/**
 * Created by ydy on 2016/9/10.
 */
public class CommentBean {
    /**
     * has_next : 0
     * comments : [{"content":"好可爱","created_at":1473476700000,"id":3755,"author":{"username":"Migu","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/7/17/c5a9e5af-0a0b-40a3-aa16-b24db3a6bfae.jpg","id":7774,"sign":"遇见最美的自己"}},{"content":"酷","created_at":1473253008000,"id":3633,"author":{"username":"Y徐阿浩","avatar_url":"http://tva3.sinaimg.cn/crop.0.0.100.100.180/006hOix0jw8ey0plh18c8j302s02sq2r.jpg","id":52157,"sign":"遇见最美的自己"}},{"content":"好想戴戴","created_at":1473242932000,"id":3624,"author":{"username":"弹弹珠的小时候","avatar_url":"http://wx.qlogo.cn/mmopen/OADFJvKicONuS8My3DLL0VW95mSL2rKLA0eGhZotibr7kCXwQz9ib11ZuVgIzyYmhGJyBmsWayxmI1UnoAOfYen8xagCYm4ex3G/0","id":52079,"sign":"遇见最美的自己"}},{"content":"太酷了吧","created_at":1473179672000,"id":3601,"author":{"username":"☆ Summer杨 ☆","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/7/20/8291c2ab-59c9-459d-93dd-fffb915fedde.jpg","id":8753,"sign":"遇见最美的自己"}}]
     */

    private DataBean data;
    /**
     * data : {"has_next":0,"comments":[{"content":"好可爱","created_at":1473476700000,"id":3755,"author":{"username":"Migu","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/7/17/c5a9e5af-0a0b-40a3-aa16-b24db3a6bfae.jpg","id":7774,"sign":"遇见最美的自己"}},{"content":"酷","created_at":1473253008000,"id":3633,"author":{"username":"Y徐阿浩","avatar_url":"http://tva3.sinaimg.cn/crop.0.0.100.100.180/006hOix0jw8ey0plh18c8j302s02sq2r.jpg","id":52157,"sign":"遇见最美的自己"}},{"content":"好想戴戴","created_at":1473242932000,"id":3624,"author":{"username":"弹弹珠的小时候","avatar_url":"http://wx.qlogo.cn/mmopen/OADFJvKicONuS8My3DLL0VW95mSL2rKLA0eGhZotibr7kCXwQz9ib11ZuVgIzyYmhGJyBmsWayxmI1UnoAOfYen8xagCYm4ex3G/0","id":52079,"sign":"遇见最美的自己"}},{"content":"太酷了吧","created_at":1473179672000,"id":3601,"author":{"username":"☆ Summer杨 ☆","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/7/20/8291c2ab-59c9-459d-93dd-fffb915fedde.jpg","id":8753,"sign":"遇见最美的自己"}}]}
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
        private int has_next;
        /**
         * content : 好可爱
         * created_at : 1473476700000
         * id : 3755
         * author : {"username":"Migu","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/7/17/c5a9e5af-0a0b-40a3-aa16-b24db3a6bfae.jpg","id":7774,"sign":"遇见最美的自己"}
         */

        private List<CommentsBean> comments;

        public int getHas_next() {
            return has_next;
        }

        public void setHas_next(int has_next) {
            this.has_next = has_next;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            private String content;
            private long created_at;
            private int id;
            /**
             * username : Migu
             * avatar_url : http://dstatic.zuimeia.com/user/avatar/2016/7/17/c5a9e5af-0a0b-40a3-aa16-b24db3a6bfae.jpg
             * id : 7774
             * sign : 遇见最美的自己
             */

            private AuthorBean author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreated_at() {
                return created_at;
            }

            public void setCreated_at(long created_at) {
                this.created_at = created_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public static class AuthorBean {
                private String username;
                private String avatar_url;
                private int id;
                private String sign;

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }
            }
        }
    }
}
