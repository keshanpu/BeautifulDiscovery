package com.android.beautifulthing.DesignerFragment.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class DesignerShopBean {

    /**
     * shop_image : http://dstatic.zuimeia.com/brand/shop/2016/8/29/2fcdd97f-1c45-4d19-8b46-3c635548db0a.jpg
     * shops : [{"city":"北京","address":"CHINA ORIENTAL PLAZA - Shop AA28 and 30, Ground Floor, 1 East Chang An Avenue, Oriental Plaza","id":153,"name":"CHINA ORIENTAL PLAZA "}]
     * online_shop_image : http://dstatic.zuimeia.com/brand/shop/2016/8/29/a65a29bd-1718-4a59-8bd6-1a58edf22003.jpg
     * online_shops : [{"link_url":"https://www.moschino.com/us/","id":145,"name":"官方网店"}]
     */

    private DataBean data;
    /**
     * data : {"shop_image":"http://dstatic.zuimeia.com/brand/shop/2016/8/29/2fcdd97f-1c45-4d19-8b46-3c635548db0a.jpg","shops":[{"city":"北京","address":"CHINA ORIENTAL PLAZA - Shop AA28 and 30, Ground Floor, 1 East Chang An Avenue, Oriental Plaza","id":153,"name":"CHINA ORIENTAL PLAZA "}],"online_shop_image":"http://dstatic.zuimeia.com/brand/shop/2016/8/29/a65a29bd-1718-4a59-8bd6-1a58edf22003.jpg","online_shops":[{"link_url":"https://www.moschino.com/us/","id":145,"name":"官方网店"}]}
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
                    "shop_image='" + shop_image + '\'' +
                    ", online_shop_image='" + online_shop_image + '\'' +
                    ", shops=" + shops +
                    ", online_shops=" + online_shops +
                    '}';
        }

        private String shop_image;
        private String online_shop_image;
        /**
         * city : 北京
         * address : CHINA ORIENTAL PLAZA - Shop AA28 and 30, Ground Floor, 1 East Chang An Avenue, Oriental Plaza
         * id : 153
         * name : CHINA ORIENTAL PLAZA
         */

        private List<ShopsBean> shops;
        /**
         * link_url : https://www.moschino.com/us/
         * id : 145
         * name : 官方网店
         */

        private List<OnlineShopsBean> online_shops;

        public String getShop_image() {
            return shop_image;
        }

        public void setShop_image(String shop_image) {
            this.shop_image = shop_image;
        }

        public String getOnline_shop_image() {
            return online_shop_image;
        }

        public void setOnline_shop_image(String online_shop_image) {
            this.online_shop_image = online_shop_image;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public List<OnlineShopsBean> getOnline_shops() {
            return online_shops;
        }

        public void setOnline_shops(List<OnlineShopsBean> online_shops) {
            this.online_shops = online_shops;
        }

        public static class ShopsBean {
            @Override
            public String toString() {
                return "ShopsBean{" +
                        "city='" + city + '\'' +
                        ", address='" + address + '\'' +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }

            private String city;
            private String address;
            private int id;
            private String name;


            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class OnlineShopsBean {
            @Override
            public String toString() {
                return "OnlineShopsBean{" +
                        "link_url='" + link_url + '\'' +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }

            private String link_url;
            private int id;
            private String name;

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
