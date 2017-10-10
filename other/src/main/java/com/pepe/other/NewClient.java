package com.pepe.other;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wang on 2017/10/9.
 */

public class NewClient implements Parcelable {
    public int id;
    public Bitmap bitmap;

    /**
     * 当前对象的内容描述,一般返回0即可
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * 将当前对象写入序列化结构中
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.bitmap,0);
    }

    /**
     * 从序列化后的对象中创建原始对象
     * @param in
     */
    protected NewClient(Parcel in) {
        this.id = in.readInt();
        //Bitmap，此方法序列需要传递当前线程的上下文类加载器，否则会报无法找到类的错误
        this.bitmap = in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

    /**
     * public static final一个都不能少，内部对象CREATOR的名称也不能改变，必须全部大写。
     * 重写接口中的两个方法：
     * createFromParcel(Parcel in) 实现从Parcel容器中读取传递数据值,封装成Parcelable对象返回逻辑层，
     * newArray(int size) 创建一个类型为T，长度为size的数组，供外部类反序列化本类数组使用。
     */
    public static final Parcelable.Creator<NewClient> CREATOR = new Parcelable.Creator<NewClient>() {
                /**
                 * 从序列化后的对象中创建原始对象
                 */
                @Override
                public NewClient createFromParcel(Parcel source) {
                    return new NewClient(source);
                }

                /**
                 * 创建指定长度的原始对象数组
                 * @param size
                 * @return
                 */
                @Override
                public NewClient[] newArray(int size) {
                    return new NewClient[size];
                }
            };
}
