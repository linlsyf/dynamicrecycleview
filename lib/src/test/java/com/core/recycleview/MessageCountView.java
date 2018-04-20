package com.core.recycleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.core.base.BaseView;
import com.core.recycleview.base.MathsUtil;
import com.core.recycleview.base.StringUIUtil;
import com.core.utils.DensityUtil;

/**
 * 用于显示消息数量的一个小控件，高度需要设置，宽度会自定改变，不需要设置，设置了也没有用。
 */
public class MessageCountView extends BaseView {

    public enum Mode {
        MESSAGE_COUNT,
        IMAGE
    }

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private final ScaleAnimation mEnlarge;
    private final ScaleAnimation mNarrow;
    private final Animation.AnimationListener mNarrowListener;

    private Context mContext;

    /**消息数量*/
    private String mMessageCount;
    private int mMessageCountInt;

    private float mWidth;
    private float mHeight;
    private int mMeasureWidth;
    private int mMeasureHeight;
    private float radio;

    private Paint mPaint;

    private float mTextX;
    private float mTextY;
    private RectF mTextRectF;
    /**图片*/
    private Bitmap mImage;
    private RectF mImageRectF;
    private Mode mode;

    private float mCenterX;
    private float mCenterY;
    private float mCircleRadio;

    private boolean mIsOnlyShowPoint;

    private int mStartX, mStartY, mLeft, mTop, mRight, mBottom;
    private boolean mReduceW, mReduceH;

    private UnreadCountHideListener mUnreadCountHideListener;

    {
        mEnlarge = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mEnlarge.setInterpolator(new OvershootInterpolator(3.0f));
        mEnlarge.setDuration(250);
        mEnlarge.setFillAfter(true);
        mNarrow = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mNarrow.setInterpolator(new AnticipateInterpolator(3.0f));
        mNarrow.setDuration(250);
        mNarrow.setFillBefore(true);

        mNarrowListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
                if (mUnreadCountHideListener != null) mUnreadCountHideListener.onAnimationEnd(MessageCountView.this);
            }
        };
    }

    public MessageCountView(Context context) {
        this(context, null);
    }

    public MessageCountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        mode = Mode.MESSAGE_COUNT;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /*初始化未读数*/
        mMessageCountInt = 0;
        mWidth = -1;
        mHeight = -1;
    }

    @Override
    protected void onDetachedFromWindow() {
        /*清除动画*/
        clearAnimation();
        super.onDetachedFromWindow();
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        if (screenState == SCREEN_STATE_OFF) {
            clearAnimation();
        }
        super.onScreenStateChanged(screenState);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMeasureHeight, MeasureSpec.EXACTLY);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMeasureWidth, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        float min = Math.min(mWidth, mHeight);
        mCircleRadio = min / 4;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            mLeft = left;
            mTop = top;
            mRight = right;
            mBottom = bottom;

            ViewGroup.LayoutParams params = getLayoutParams();
            if (params != null && params instanceof RelativeLayout.LayoutParams) {
                int[] rules = ((RelativeLayout.LayoutParams) params).getRules();
                mReduceW = rules[RelativeLayout.ALIGN_RIGHT] != 0 ||
                        rules[RelativeLayout.ALIGN_PARENT_RIGHT] != 0;
                mReduceH = rules[RelativeLayout.ALIGN_BOTTOM] != 0 ||
                        rules[RelativeLayout.ALIGN_PARENT_BOTTOM] != 0;
            }
        }
    }

    private void measureLeftTop() {
        if (mReduceW) {
            mStartX = mRight - mMeasureWidth;
        } else {
            mStartX = mLeft;
        }
        if (mReduceH) {
            mStartY = mBottom - mMeasureHeight;
        } else {
            mStartY = mTop;
        }
    }

    private void initImageData() {
        if (mImage == null) {
            return;
        }

        measureTextWH("0", false);
        float imageWidth = mImage.getWidth();
        float imageHeight = mImage.getHeight();
        float scale = mMeasureHeight / imageHeight;
        imageWidth = MathsUtil.retainDecimal(1, imageWidth * scale);
        imageHeight = MathsUtil.retainDecimal(1, imageHeight * scale);
        /*上面只是为了获取高度而进行的测量，这里需要把图片缩放后的宽赋值给mMeasureWidth*/
        mMeasureWidth = (int) (imageWidth + 0.5);

        float imageX = (mMeasureWidth - imageWidth) / 2;
        float imageY = (mMeasureHeight - imageHeight) / 2;
        mImageRectF = new RectF(imageX, imageY, imageX + imageWidth, imageY + imageHeight);

        requestViewUpdate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mode == Mode.MESSAGE_COUNT) {
            drawMessageCount(canvas);
        } else if (mode == Mode.IMAGE) {
            drawImage(canvas);
        }
    }

    private void drawMessageCount(Canvas canvas) {
        if (mMessageCount == null || "".equals(mMessageCount)) {
            return;
        }
        mPaint.setColor(Color.RED);
        if (mIsOnlyShowPoint) {
            canvas.drawCircle(mCenterX, mCenterY, mCircleRadio, mPaint);
        } else {
            /*画圆角矩形*/
            canvas.drawRoundRect(mTextRectF, radio, radio, mPaint);
            /*画数字*/
            mPaint.setColor(Color.WHITE);
            canvas.drawText(mMessageCount, mTextX, mTextY, mPaint);
        }
    }

    private void drawImage(Canvas canvas) {
        if (mImage != null && mImageRectF != null) {
            canvas.drawBitmap(mImage, null, mImageRectF, null);
        }
    }

    private void requestSizeChange() {
        if (mMessageCount == null || "".equals(mMessageCount)) {
            return;
        }

        measureTextWH(mMessageCount, true);
        /*初始化圆角矩形区域*/
        mTextRectF = new RectF(0, 0, mMeasureWidth, mMeasureHeight);
        /*初始化半径*/
        radio = mMeasureHeight / 2;

        requestViewUpdate();
    }

    private void requestViewUpdate() {
        /*当宽高不为-1时，表示当前View已经过了测量阶段，而当当前测量宽高和系统测量宽高不同时，则需要重新请求测量*/
        if (mWidth != mMeasureWidth || mHeight != mMeasureHeight) {
            measureLeftTop();
            layout(mStartX, mStartY, mStartX + mMeasureWidth, mStartY + mMeasureHeight);
            if (mWidth != -1 && mHeight != -1 && getVisibility() != GONE) requestLayout();
        } else {
            if (mWidth != -1 && mHeight != -1 && getVisibility() != GONE) invalidate();
        }
    }

    private void measureTextWH(String text, boolean measureWidth) {
        float[] wh = StringUIUtil.measureText(mPaint, text);
        float mTextWidth = wh[0];
        float mTextHeight = wh[1];

        mMeasureHeight = (int) (wh[1] * 1.2f + 0.5);
        if (!measureWidth) {
            return;
        }
        float length;
        if (mMessageCount.length() == 1) {
            length = mMeasureHeight;
        } else if (mMessageCount.length() <= 3) {
            length = mMeasureHeight / 2 + mTextWidth;
        } else {
            throw new RuntimeException("未读数字符串长度太长：" + mMessageCount + "，长度：" + mMessageCount.length());
        }
        mTextX = (length - mTextWidth) / 2;
        mTextY = (mMeasureHeight + mTextHeight) / 2 - mPaint.descent();
        mMeasureWidth = (int) (length + 0.5F);
    }

    /**
     * 设置未读数字体大小，入参是dp
     */
    public void setTextSize(float textSize) {
        textSize = DensityUtil.dip2px(mContext, textSize);
        mPaint.setTextSize(textSize);
    }
    /**
     * 设置未读数字体大小，入参是px
     */
    public void setTextSizeByPX(float px) {
        mPaint.setTextSize(px);
    }

    /**
     * 获取当前未读数泡泡中的未读数
     * @return 未读数
     */
    public int getMessageCount() {
        return mMessageCountInt;
    }

    /**
     * 消息数泡泡复位，防止控件复用时发生不正常的动画
     */
    public void reset() {
        clearAnimation();
        setVisibility(GONE);
    }

    /**
     * 设置大于0的数字会显示出一个带泡泡的数字，设置小于等于0控件消失
     * @param count 消息数
     */
    public void setMessageCount(final int count) {
        final boolean modeChange = mode == Mode.IMAGE;
        mode = Mode.MESSAGE_COUNT;
        /*获取未读数*/
        mMessageCountInt = count;

        if (mMessageCountInt <= 0) {
            HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    /*清除未完成的动画，必须有*/
                    clearAnimation();
                    if (getVisibility() != GONE) {
                        if (modeChange) {
                            setVisibility(GONE);
                        } else {
                            mNarrow.setAnimationListener(mNarrowListener);
                            startAnimation(mNarrow);
                        }
                    }
                }
            });
        } else {
            /*未读数转换为字符串*/
            final String messageCount = mMessageCountInt < 100
                    ? String.valueOf(mMessageCountInt) : "99+";
            /*这里如果立即执行的话，会遇到动画播放不完整的情况，所以需要post到主线程队列执行*/
            HANDLER.post(new Runnable() {
                @Override
                public void run() {
                    /*先判断两次未读数是否相同，减少不必要的操作，增强性能*/
                    if (!messageCount.equals(mMessageCount) || modeChange) {
                        mMessageCount = messageCount;
                        /*测量当前未读数字符串宽高并根据情况请求重新测量和布局*/
                        requestSizeChange();
                    }

                    /*如果消失动画正在执行，则立刻停止*/
                    mNarrow.setAnimationListener(null);
                    /*清除未完成的动画，必须有*/
                    clearAnimation();
                    if (getVisibility() != VISIBLE) {
                        setVisibility(VISIBLE);
                        startAnimation(mEnlarge);
                    }
                }
            });
        }
    }

    /**
     * 设置图片，设置NULL控件消失
     * @param image 图片
     */
    public void setImage(Bitmap image) {
        mode = Mode.IMAGE;

        mImage = image;

        initImageData();

        if (mImage == null) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
        }
    }

    public void setImage(int id) {
        Bitmap image = ImageUtils.BitmapUtil.getBitmapById(mContext, id);
        setImage(image);
    }

    public void setUnreadCountHideListener(UnreadCountHideListener listener) {
        mUnreadCountHideListener = listener;
        if (mUnreadCountHideListener != null && (getVisibility() == GONE || mode == Mode.IMAGE))
            mUnreadCountHideListener.onAnimationEnd(this);
    }

    public Mode getMode() {
        return mode;
    }

    public void onlyShowPoint(boolean onlyShowPoint) {
        mIsOnlyShowPoint = onlyShowPoint;
    }

    public interface UnreadCountHideListener {

        void onAnimationEnd(MessageCountView view);

    }

}
