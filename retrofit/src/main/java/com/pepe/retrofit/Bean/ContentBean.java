package com.pepe.retrofit.Bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class ContentBean {

    /**
     * error_code : 200
     * reason : 请求成功！
     * result : {"imageList":[{"imageUrl":"http://imgs.juheapi.com/comic_xin/6L6b5be06L6%2B55qE5YaS6Zmp/237401/0-MjM3NDAxMA==.jpg","id":1},{"imageUrl":"http://imgs.juheapi.com/comic_xin/6L6b5be06L6%2B55qE5YaS6Zmp/237401/1-MjM3NDAxMQ==.jpg","id":2}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public static ContentBean objectFromData(String str) {

        return new Gson().fromJson(str, ContentBean.class);
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
        /**
         * imageUrl : http://imgs.juheapi.com/comic_xin/6L6b5be06L6%2B55qE5YaS6Zmp/237401/0-MjM3NDAxMA==.jpg
         * id : 1
         */

        private List<ImageListBean> imageList;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public List<ImageListBean> getImageList() {
            return imageList;
        }

        public void setImageList(List<ImageListBean> imageList) {
            this.imageList = imageList;
        }

        public static class ImageListBean {
            private String imageUrl;
            private int id;

            public static ImageListBean objectFromData(String str) {

                return new Gson().fromJson(str, ImageListBean.class);
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
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
