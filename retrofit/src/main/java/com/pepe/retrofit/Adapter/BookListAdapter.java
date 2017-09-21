package com.pepe.retrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pepe.retrofit.Bean.BookBean;
import com.pepe.retrofit.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class BookListAdapter extends BaseAdapter {
    private Context mContext;
    private List<BookBean.ResultBean.BookListBean> bookList;
    public BookListAdapter(Context mContext, List<BookBean.ResultBean.BookListBean> bookList){
        this.mContext=mContext;
        this.bookList=bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_book, null,false);
            holder.book_img = (ImageView) convertView.findViewById(R.id.book_img);
            holder.book_name = (TextView) convertView.findViewById(R.id.book_name);
            holder.book_type = (TextView) convertView.findViewById(R.id.book_type);
            holder.book_area = (TextView) convertView.findViewById(R.id.book_area);
            holder.book_finish = (TextView) convertView.findViewById(R.id.book_finish);
            holder.book_lastUpdate = (TextView) convertView.findViewById(R.id.book_lastUpdate);
            AutoUtils.autoSize(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        BookBean.ResultBean.BookListBean book=bookList.get(position);

//            从URL加载
//            with(Context context) - 对于很多 Android API 调用，Context 是必须的。Glide 在这里也一样
//            load(String imageUrl) - 这里你可以指定哪个图片应该被加载，同上它会是一个字符串的形式表示一个网络图片的 URL
//            into(ImageView targetImageView) 你的图片会显示到对应的 ImageView 中。

//            从资源中加载
//            int resourceId = R.mipmap.ic_launcher;
//            Glide.with(context).load(resourceId).into(imageViewResource);

//            从文件中加载
//            这个文件可能不存在于你的设备中。然而你可以用任何文件路径，去指定一个图片路径。
//            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Running.jpg");
//            Glide.with(context).load(file).into(imageViewFile);

//            一个小助手功能：简单的从资源 id 转换成 Uri。
//            public static final String ANDROID_RESOURCE = "android.resource://";
//            public static final String FOREWARD_SLASH = "/";
//            private static Uri resourceIdToUri(Context context, int resourceId) {
//                return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
//            }

//            获取图片之前默认的图片
//            .placeholder(R.mipmap.ic_launcher) // can also be a drawable
//            错误占位符：.error()
//            .error(R.mipmap.future_studio_launcher) // will be displayed if the image cannot be loaded

//            淡出淡入动画，默认有这个效果
//            .crossFade()
//            crossFade() 方法还有另外重载方法 .crossFade(int duration)。
//            如果你想要去减慢（或加快）动画，随时可以传一个毫秒的时间给这个方法。
//            动画默认的持续时间是 300毫秒。

//            去除淡入淡出效果
//            .dontAnimate()

//            调整图片到你想要的尺寸
//            .override(600, 200) // resizes the image to these dimensions (in pixel). does not respect aspect ratio

//            缩放图像，默认.fitCenter()
//            CenterCrop()是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分。
//            ImageView 可能会完全填充，但图像可能不会完整显示。
//            .centerCrop() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.

//            fitCenter() 是裁剪技术，即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。
//            该图像将会完全显示，但可能不会填满整个 ImageView。
//            .fitCenter()

//            显示 Gif
//            与url相同
//            .asGif()  如果不是Gif图，则当做error处理
//            .error( R.drawable.full_cake )
//            .asBitmap()  只显示Gif图的第一帧

//            显示本地视频
//            String filePath = "/storage/emulated/0/Pictures/example_video.mp4";
//            Glide.with( context )
//                 .load( Uri.fromFile( new File( filePath ) ) )
//                 .into( imageViewGifAsBitmap );

//            如果图片变化很快，每次都要重新请求，那么
//            .skipMemoryCache( true )  跳过内存缓存，重新请求
//            .diskCacheStrategy( DiskCacheStrategy.NONE )  跳过磁盘缓存，重新请求

//            如果你请求的一个图像是 1000x1000 像素的，但你的 ImageView 是 500x500 像素的，
//            Glide 将会把这两个尺寸都进行缓存。

//            现在你将会理解对于 .diskCacheStrategy() 方法来说不同的枚举参数的意义：
//            DiskCacheStrategy.NONE        什么都不缓存，就像刚讨论的那样
//            DiskCacheStrategy.SOURCE      仅仅只缓存原来的全分辨率的图像。在我们上面的例子中，将会只有一个 1000x1000 像素的图片
//            DiskCacheStrategy.RESULT      仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
//            DiskCacheStrategy.ALL         缓存所有版本的图像（默认行为）

//            如果你有一张图片，你知道你将会经常操作处理，并做了一堆不同的版本，对其有意义的仅仅是缓存原始分辨率图片。
//            因此，我们用 DiskCacheStrategy.SOURCE 去告诉 Glide 仅仅保存原始图片：
//            Glide.with( context ).load( eatFoodyImages[2] )
//                    .diskCacheStrategy( DiskCacheStrategy.SOURCE )
//                    .into( imageViewFile );

//            递增priority(优先级)的列表（并不完全管用）：
//            .priority( Priority.HIGH )
//            Priority.LOW
//            Priority.NORMAL
//            Priority.HIGH
//            Priority.IMMEDIATE

//            保存缩略图，以复用
//            .thumbnail( 0.1f )
//            另开一个请求去请求缩略图
        // setup Glide request without the into() method
//            DrawableRequestBuilder<String> thumbnailRequest = Glide.with( context ).load( eatFoodyImages[2] );
        // pass the request as a a parameter to the thumbnail request
//            Glide.with( context ).load( UsageExampleGifAndVideos.gifUrl )
//                    .thumbnail( thumbnailRequest )
//                    .into( imageView3 );



        if(position%5==0){
            Glide.with(mContext).load(book.getCoverImg()+"1").placeholder(R.mipmap.book_default).error(R.mipmap.glide_error).crossFade().into(holder.book_img);
        }else if(position%2==0){

            Glide.with(mContext)
                    .load(book.getCoverImg())//url（包括Gif和本地视频的url），或者资源id
                    .placeholder(R.mipmap.book_default)//图片加载之前显示的图片
                    .error(R.mipmap.glide_error)//图片加载错误显示的图片
                    .crossFade()//默认淡入淡出
                    .fitCenter()//默认缩小全部显示
//                        .override(600, 50) // resizes the image to these dimensions (in pixel). does not respect asp
                    .into(holder.book_img);
        }else{
            Glide
                    .with(mContext)
                    .load(book.getCoverImg())
                    .placeholder(R.mipmap.book_default)
                    .error(R.mipmap.glide_error)
                    .crossFade()
                    .centerCrop()//完全填充，但图像可能不会完整显示
                    .into(holder.book_img);
        }
        holder.book_name.setText(position+book.getName());
        holder.book_type.setText(book.getType());
        holder.book_area.setText(book.getArea());
        holder.book_finish.setText(book.isFinish()==true?"已完结":"未完结");
        holder.book_lastUpdate.setText(book.getLastUpdate()+"");
        return convertView;
    }

    public class ViewHolder{
        ImageView book_img;
        TextView book_name;
        TextView book_type;
        TextView book_area;
        TextView book_finish;
        TextView book_lastUpdate;
    }
}
