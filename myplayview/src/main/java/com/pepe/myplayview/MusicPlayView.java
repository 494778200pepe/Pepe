package com.pepe.myplayview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;



/**
 * 代码说明： 1.
 * 注释掉了磁盘一起转动的效果，现在的方案不是最好的，建议若是想实现，可以把圆形封面和磁盘合并成一张图(最好在CicicleImageView这里面做
 * )。给一个旋转动画。两个动画，两个View，帧的频率不会那么高 2. 注释掉了上一首、下一首切换的时候渐变的动画效果，原图从1-0 ，新图从0-1
 * 的渐变。 在模拟器上会报错，主要是因为改变ImageView的背景那一行报错。
 *
 * @author liQiang
 *
 */

public class MusicPlayView extends RelativeLayout {

	private Context mContext;
	// 旋转一周所用时间
	private static final int ROTATE_TIME = 15 * 1000;
	// 动画旋转重复执行的次数，这里代表无数次，似乎没有无限执行的属性，所以用了一个大数字代表
	private static final int ROTATE_COUNT = 10000;

	// 唱针动画时间
	private static final int NEEDLE_TIME = 2 * 500;
	// 唱针动画执行的角度
	private static final int NEEDLE_RADIUS = 18;

	// 封面、背景切换时候的渐变动画
	private static final int AVATART_DISC_ALPHA_TIME = 1 * 1000;

	private static final float AVATART_DISC_ALPHA_PERCENT = 0.1f;

	// 背景
	private ImageView mBackground;

	// 唱针
	private ImageView mNeedle;
	// 唱片
	// private ImageView mDisc;
	// 封面
	private CircleImageView mAvatar;

	private boolean isPlay = false;

	AnimatorSet animSet;

	ObjectAnimator mAniAvatar;

	// 封面更换时的渐变效果
	public ObjectAnimator mAniAlphaAvatarHide;
	ObjectAnimator mAniAlphaAvatarShow;

	//唱针移动动画
	ObjectAnimator mAniNeedle;

	float mValueAvatar;
	float mValueDisc;
	float mValueNeedle;

	private Bitmap currBitmap;

	// private int mCurrentImageResource = 0;

	public MusicPlay mMusicPlayListener;

	public MusicPlayView(Context context, AttributeSet attrs) {
		super(context);
		mContext = context;
		animSet = new AnimatorSet();
	}

	public MusicPlayView(Context context) {
		super(context);
		mContext = context;
		animSet = new AnimatorSet();

		View view = View.inflate(context,R.layout.media_play_view,null);
		this.addView(view);

		mBackground = (ImageView) view.findViewById(R.id.bg);

		mAvatar = (CircleImageView) view.findViewById(R.id.avatar);

		mNeedle = (ImageView) view.findViewById(R.id.needle);

		// 封面页面动画
		mAniAlphaAvatarHide = ObjectAnimator.ofFloat(mAvatar, "alpha", 1,
				AVATART_DISC_ALPHA_PERCENT)
				.setDuration(AVATART_DISC_ALPHA_TIME);
		mAniAlphaAvatarHide.addListener(avatarAlphaHideListener);
		mAniAlphaAvatarShow = ObjectAnimator.ofFloat(mAvatar, "alpha",
				AVATART_DISC_ALPHA_PERCENT, 1).setDuration(2000);

		initAvatarAnimation(0f);
		//（126，42）是估算的控制杆的轴心相对图片左上角的位置
		mNeedle.setPivotX(126);
		mNeedle.setPivotY(42);
		initNeedleAnimation(-8f,-26f);

	}

	public MusicPlayView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		animSet = new AnimatorSet();
	}

	public interface MusicPlay {
		void onAvatarChange();
	}

	public void setMusicPlayerListener(MusicPlay listener) {
		this.mMusicPlayListener = listener;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mBackground = (ImageView) findViewById(R.id.bg);

		mAvatar = (CircleImageView) findViewById(R.id.avatar);

		mNeedle = (ImageView) findViewById(R.id.needle);

		// 封面页面动画
		mAniAlphaAvatarHide = ObjectAnimator.ofFloat(mAvatar, "alpha", 1,
				AVATART_DISC_ALPHA_PERCENT)
				.setDuration(AVATART_DISC_ALPHA_TIME);
		mAniAlphaAvatarHide.addListener(avatarAlphaHideListener);
		mAniAlphaAvatarShow = ObjectAnimator.ofFloat(mAvatar, "alpha",
				AVATART_DISC_ALPHA_PERCENT, 1).setDuration(2000);

		initAvatarAnimation(0f);
		//（126，42）是估算的控制杆的轴心相对图片左上角的位置
		mNeedle.setPivotX(126);
		mNeedle.setPivotY(42);
		initNeedleAnimation(-8f,-26f);
	}

	AnimatorListener avatarAlphaHideListener = new AnimatorListener() {

		@Override
		public void onAnimationStart(Animator arg0) {

		}

		@Override
		public void onAnimationRepeat(Animator arg0) {

		}

		@Override
		public void onAnimationEnd(Animator arg0) {
			mAniAlphaAvatarShow.start();
			changeImage(currBitmap, false);

		}

		@Override
		public void onAnimationCancel(Animator arg0) {

		}
	};

	/**
	 *
	 * 设置背景
	 *
	 * @param d
	 */
	public void setBackgroundDrawable(Drawable d) {
		mBackground.setBackgroundDrawable(d);
	}

	/**
	 *
	 * 设置背景
	 *
	 * @param bitmap
	 */
	public void setBackgroundResource(Bitmap bitmap) {
//		mBackground.setBackgroundDrawable(GaussianBlurUtil
//				.BoxBlurFilter(bitmap));
	}

	public void setAvatarImageResource(Bitmap bitmap) {
		try {
			mAvatar.setImageBitmap(bitmap);

		} catch (Exception e) {
			Log.d(Consts.TAG, "error : " + e);
			Log.d(Consts.TAG, "setImageBitmap---error");
		}
	}

	/**
	 * 播放
	 */
	public void play() {
		Log.d(Consts.TAG, "===> MusicPlayView play  isPlay = " + isPlay);
		if (isPlay) {
			if(mAniAvatar != null && !mAniAvatar.isRunning()){
				mAniAvatar.start();
			}
		}else{
			mAniAvatar.start();
			initNeedleAnimation(-26f, -8f);
			mAniNeedle.start();
			setPlay(true);
		}
	}

	/**
	 * 暂停
	 */
	public void pause() {
		Log.d(Consts.TAG, "===> MusicPlayView pause !isPlay = " + !isPlay);
		mAniAvatar.cancel();
		if (!isPlay) {
			return;
		}
		setPlay(false);
		initNeedleAnimation(-8f, -28f);
		mAniNeedle.start();
		Log.d("MusicPlayer", "======>pause--mValueAvatar:" + mValueAvatar);
		initAvatarAnimation(mValueAvatar);
	}

	public void prepare(Context context, String id, String albumArt) {
		Log.d("HqMusicPlayer", "===========>prepare");
//		String str =  "/mnt/sdcard/Android/data/com.android.providers.media/albumthumbs/1262301513073";
//		currBitmap = Utils.getMusicBitmap(context, albumArt);
		if(TextUtils.isEmpty(id)|| TextUtils.isEmpty(albumArt)){
			currBitmap = AlbumUtils.getDefaultArtwork(context, true);
		}else{
			currBitmap = AlbumUtils.getArtwork(context, Long.valueOf(id), Long.valueOf(albumArt),true,false);
		}
		mAniAlphaAvatarHide.start();

	}

	private void changeImage(final Bitmap bitmap, final boolean changebg) {
		postDelayed(new Runnable() {

			@Override
			public void run() {
				if (changebg) {
					setBackgroundResource(bitmap);
				}
				setAvatarImageResource(bitmap);

			}
		}, 0);
	}

	private Bitmap toConformBitmap(Bitmap background, Bitmap foreground) {
		if (background == null) {
			return null;
		}

		int bgWidth = background.getWidth();
		int bgHeight = background.getHeight();
		// int fgWidth = foreground.getWidth();
		// int fgHeight = foreground.getHeight();
		// create the new blank bitmap 创建一个新的和SRC长度宽度一样的位图
		Bitmap newbmp = Bitmap.createBitmap(bgWidth, bgHeight, Config.RGB_565);
		Canvas cv = new Canvas(newbmp);
		// draw bg into
		cv.drawBitmap(background, 0, 0, null);// 在 0，0坐标开始画入bg
		// draw fg into
		cv.drawBitmap(foreground, 0, 0, null);// 在 0，0坐标开始画入fg ，可以从任意位置画入
		// save all clip
		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		// store
		cv.restore();// 存储
		return newbmp;
	}

	public boolean isPlay() {
		return isPlay;
	}

	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}

	/**
	 * 初始化旋转封面动画对象
	 * @param valueAvatar
     */
	private void initAvatarAnimation(float valueAvatar) {
		mAniAvatar = ObjectAnimator.ofFloat(mAvatar, "rotation", valueAvatar,
				360f + valueAvatar);
		mAniAvatar.removeAllListeners();
		mAniAvatar.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				mValueAvatar = (Float) animation.getAnimatedValue("rotation");
//				Log.e("pepe", "角度 : " + mValueAvatar);
			}
		});
		mAniAvatar.setDuration(ROTATE_TIME);
		mAniAvatar.setInterpolator(new LinearInterpolator());
		mAniAvatar.setRepeatCount(ROTATE_COUNT);
	}

	/**
	 * 初始化唱针动画
	 * @param start
	 */
	private void initNeedleAnimation(float start,float end) {
		mAniNeedle = ObjectAnimator.ofFloat(mNeedle, "rotation", start, end).setDuration(NEEDLE_TIME);
	}

}
