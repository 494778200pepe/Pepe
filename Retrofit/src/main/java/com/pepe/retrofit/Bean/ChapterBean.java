package com.pepe.retrofit.Bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class ChapterBean {

    /**
     * error_code : 200
     * reason : 请求成功！
     * result : {"total":558,"limit":20,"comicName":"火影忍者","chapterList":[{"name":"第01卷","id":139833},{"name":"第02卷","id":139834},{"name":"第03卷","id":139836},{"name":"第04卷","id":139837}]}
     */

    private int error_code;
    private String reason;
    /**
     * total : 558
     * limit : 20
     * comicName : 火影忍者
     * chapterList : [{"name":"第01卷","id":139833},{"name":"第02卷","id":139834},{"name":"第03卷","id":139836},{"name":"第04卷","id":139837}]
     */

    private ResultBean result;

    public static ChapterBean objectFromData(String str) {

        return new Gson().fromJson(str, ChapterBean.class);
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
        private String comicName;
        /**
         * name : 第01卷
         * id : 139833
         */

        private List<ChapterListBean> chapterList;

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

        public String getComicName() {
            return comicName;
        }

        public void setComicName(String comicName) {
            this.comicName = comicName;
        }

        public List<ChapterListBean> getChapterList() {
            return chapterList;
        }

        public void setChapterList(List<ChapterListBean> chapterList) {
            this.chapterList = chapterList;
        }

        public static class ChapterListBean {
            private String name;
            private int id;

            public static ChapterListBean objectFromData(String str) {

                return new Gson().fromJson(str, ChapterListBean.class);
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
