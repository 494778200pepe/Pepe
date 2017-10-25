package com.pepe.retrofit;


import com.pepe.retrofit.Bean.BookBean;
import com.pepe.retrofit.Bean.CategoryBean;
import com.pepe.retrofit.Bean.ChapterBean;
import com.pepe.retrofit.Bean.ContentBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public interface MyService {
    @GET("comic/category")
    Call<CategoryBean> getCategory(@Query("key") String key);

    @GET("comic/book")
    Call<BookBean> getBookList(@Query("key") String key, @Query("skip") int skip, @Query("type") String type);


    @GET("comic/chapter")
    Call<ChapterBean> getChapterList(@Query("key") String key, @Query("skip") int skip, @Query("comicName") String comicName);

    @GET("comic/chapterContent")
    Call<ContentBean> getContentList(@Query("key") String key, @Query("comicName") String comicName, @Query("id") int id);
}
