package com.android.beautifulthing.DesignerFragment.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class DesignerDetilBean {

    /**
     * introduce_images : ["http://dstatic.zuimeia.com/common/image/2016/8/19/7bce1ee2-3dd2-49d2-93f2-0c844d2af53d_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/89b03770-b696-4563-a700-7b1a89d77d26_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/75ca603d-ef8b-48b0-ae30-306b32d2c7d0_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/b1466965-a393-4827-9569-980292295772_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/040ca588-220f-497a-babd-9fc8927a524a_1000x1000.jpeg"]
     * city : 波特兰
     * concept : 我喜欢把材料用在出其不意的地方
     * article_num : 0
     * name : Matt Pierce
     * product_num : 10
     * label : Wood & Faulk 创始人
     * avatar_url : http://dstatic.zuimeia.com/designer/avatar/2016/8/18/882c923a-25f8-4dd7-b4ed-c6f53690382c.jpg
     * is_followed : 0
     * id : 100
     * description : Wood Faulk 主要是设计旅行包和配饰，从小生活在美国中西部的 Matt 热爱野营，喜欢 DIY 制作包包，好奇心强的Matt设计的包包既有粗犷的美国中西部豪放风格，又带有几分细腻和柔情，同时在方便和实用度方面的细节设计也十分用心。
     */

    private DataBean data;
    /**
     * data : {"introduce_images":["http://dstatic.zuimeia.com/common/image/2016/8/19/7bce1ee2-3dd2-49d2-93f2-0c844d2af53d_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/89b03770-b696-4563-a700-7b1a89d77d26_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/75ca603d-ef8b-48b0-ae30-306b32d2c7d0_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/b1466965-a393-4827-9569-980292295772_1000x1000.jpeg","http://dstatic.zuimeia.com/common/image/2016/8/19/040ca588-220f-497a-babd-9fc8927a524a_1000x1000.jpeg"],"city":"波特兰","concept":"我喜欢把材料用在出其不意的地方","article_num":0,"name":"Matt Pierce","product_num":10,"label":"Wood & Faulk 创始人","avatar_url":"http://dstatic.zuimeia.com/designer/avatar/2016/8/18/882c923a-25f8-4dd7-b4ed-c6f53690382c.jpg","is_followed":0,"id":100,"description":"Wood Faulk 主要是设计旅行包和配饰，从小生活在美国中西部的 Matt 热爱野营，喜欢 DIY 制作包包，好奇心强的Matt设计的包包既有粗犷的美国中西部豪放风格，又带有几分细腻和柔情，同时在方便和实用度方面的细节设计也十分用心。"}
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "city='" + city + '\'' +
                    ", concept='" + concept + '\'' +
                    ", article_num=" + article_num +
                    ", name='" + name + '\'' +
                    ", product_num=" + product_num +
                    ", label='" + label + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", is_followed=" + is_followed +
                    ", id=" + id +
                    ", description='" + description + '\'' +
                    ", introduce_images=" + introduce_images +
                    '}';
        }

        private String city;
        private String concept;
        private int article_num;
        private String name;
        private int product_num;
        private String label;
        private String avatar_url;
        private int is_followed;
        private int id;
        private String description;
        private List<String> introduce_images;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConcept() {
            return concept;
        }

        public void setConcept(String concept) {
            this.concept = concept;
        }

        public int getArticle_num() {
            return article_num;
        }

        public void setArticle_num(int article_num) {
            this.article_num = article_num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProduct_num() {
            return product_num;
        }

        public void setProduct_num(int product_num) {
            this.product_num = product_num;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getIs_followed() {
            return is_followed;
        }

        public void setIs_followed(int is_followed) {
            this.is_followed = is_followed;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getIntroduce_images() {
            return introduce_images;
        }

        public void setIntroduce_images(List<String> introduce_images) {
            this.introduce_images = introduce_images;
        }
    }
}
