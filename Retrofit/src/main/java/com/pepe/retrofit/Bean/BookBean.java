package com.pepe.retrofit.Bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class BookBean {

    /**
     * error_code : 200
     * reason : 请求成功！
     * result : {"total":20492,"limit":20,"bookList":[{"name":"大话降龙","type":"少年漫画","area":"国漫","des":"","finish":false,"lastUpdate":20150508},{"name":"秀逗高校","type":"少年漫画","area":"日本漫画","des":"","finish":false,"lastUpdate":20150504}]}
     */

    private int error_code;
    private String reason;
    /**
     * total : 20492
     * limit : 20
     * bookList : [{"name":"大话降龙","type":"少年漫画","area":"国漫","des":"","finish":false,"lastUpdate":20150508},{"name":"秀逗高校","type":"少年漫画","area":"日本漫画","des":"","finish":false,"lastUpdate":20150504}]
     */

    private ResultBean result;

    public static BookBean objectFromData(String str) {

        return new Gson().fromJson(str, BookBean.class);
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int total;
        private int limit;
        /**
         * name : 大话降龙
         * type : 少年漫画
         * area : 国漫
         * des :
         * finish : false
         * lastUpdate : 20150508
         */

        private List<BookListBean> bookList;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<BookListBean> getBookList() {
            return bookList;
        }

        public void setBookList(List<BookListBean> bookList) {
            this.bookList = bookList;
        }

        public static class BookListBean {
            private String name;
            private String type;
            private String area;
            private String des;
            private boolean finish;
            private int lastUpdate;
            private String coverImg;

            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
            }

            public static BookListBean objectFromData(String str) {

                return new Gson().fromJson(str, BookListBean.class);
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }

            public boolean isFinish() {
                return finish;
            }

            public void setFinish(boolean finish) {
                this.finish = finish;
            }

            public int getLastUpdate() {
                return lastUpdate;
            }

            public void setLastUpdate(int lastUpdate) {
                this.lastUpdate = lastUpdate;
            }
        }
    }
}
