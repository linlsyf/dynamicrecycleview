package com.easy.recycleview.recycleview.item;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


//import com.core.imge.ImageUtils;
import com.easy.recycleview.recycleview.EdittextLayoutView;
import com.easy.recycleview.recycleview.MessageCountView;
import com.easy.recycleview.recycleview.button.IOSSwitchButton;
import com.easy.recycleview.recycleview.item.bean.RightSecondImgSettings;
import com.easy.recycleview.recycleview.sectionview.MutiTypeSelectUtils;
import com.easy.recycleview.utils.DensityUtil;
import com.easy.recycleview.utils.StringUtils;
import com.easysoft.dynamicrecycleview.R;

/**
 * 创建者：林党宏
 * 时间：2017/1/17
 * 注释：通用itemview  信息item 显示
 * 由数据控制是否显示界面
 */

public class ContentItemView extends LinearLayout implements IItemView {
    LinearLayout mRootlayout;
    /** 右侧chcekbox */
    IOSSwitchButton mSwitchButton;
    /** 左侧选中图标 */
    CheckBox mLeftCheckBox;
    Context mContext;
    TextView mTitleTextView;
    TextView mHintTextView;
    TextView mNoticeTextView;
    ImageView mNoticeImageView;
    TextView mRightFirstTextView;
    ImageView mImageView;
    ImageView mRightSecondImgeView;
    ImageView mRightFirstImageView;
    /** 右侧按钮 */
    Button mRightFirstButton;
    /** 右侧布局 */
    LinearLayout mRightLayout;
    /** 右侧点击图片 */
    ImageView mRightCenterScaleImgeView;
    LinearLayout mRightCenterScaleImgeLayout;
    /** 输入框 */
    EdittextLayoutView mContentEditLayout;
    /** 内容布局可设置magin布局 */
    LinearLayout mContenLayout;
    /** 内容布局可设置magin布局 */
    MessageCountView mMessageCountView;
    /** 绑定数据 */
    AddressItemBean mBindItemBean = new AddressItemBean();
    /** 多选控制工具 */
    MutiTypeSelectUtils mSelectUtils;
    /** magin */
    private int mContentMagin = 0;

    private boolean mChangeSelectRefresh = false;

    public ContentItemView(Context context) {
        super(context);
        initUI(context);
    }

    public ContentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    private void initUI(Context context) {
        mContext = context;
        View rootView  = LayoutInflater.from(context).inflate(R.layout.view_item, null);
        addView(rootView);

         mRootlayout=(LinearLayout) rootView.findViewById(R.id.rootlayout);

         mSwitchButton=(IOSSwitchButton) rootView.findViewById(R.id.switchButton);
        /** 左侧选中图标 */
         mLeftCheckBox=(CheckBox) rootView.findViewById(R.id.chkItem);
         mTitleTextView=(TextView)rootView.findViewById(R.id.titleTextView);
         mHintTextView=(TextView)rootView.findViewById(R.id.hintTextView);
         mNoticeTextView=(TextView)rootView.findViewById(R.id.noticeTextView);
         mNoticeImageView=(ImageView) rootView.findViewById(R.id.noticeImgView);
         mRightFirstTextView=(TextView)rootView.findViewById(R.id.rightFirstTextView);
         mImageView=(ImageView) rootView.findViewById(R.id.headImgeView);
         mRightSecondImgeView=(ImageView) rootView.findViewById(R.id.rightSecondImgeView);
         mRightFirstImageView=(ImageView) rootView.findViewById(R.id.rightFirstImgeView);
        /** 右侧按钮 */
         mRightFirstButton=(Button) rootView.findViewById(R.id.rightFirstButton);
        /** 右侧布局 */
         mRightLayout=(LinearLayout) rootView.findViewById(R.id.rightLayout);
        /** 右侧点击图片 */
         mRightCenterScaleImgeView=(ImageView) rootView.findViewById(R.id.rightCenterScaleImgeView);
         mRightCenterScaleImgeLayout=(LinearLayout) rootView.findViewById(R.id.rightCenterScaleImgeLayout);
        /** 输入框 */
         mContentEditLayout=(EdittextLayoutView) rootView.findViewById(R.id.edtLayout);
        /** 内容布局可设置magin布局 */
         mContenLayout=(LinearLayout) rootView.findViewById(R.id.rootContentLayout);
        /** 内容布局可设置magin布局 */
         mMessageCountView=(MessageCountView) rootView.findViewById(R.id.messageCountView);

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public void initData(final AddressItemBean dataItemBean) {
        //添加头部 //添加左侧侧布局
        mBindItemBean = dataItemBean;
        LayoutParams params = (LayoutParams) mContenLayout.getLayoutParams();
        if (dataItemBean.getContentLayoutMagin() != 0) {
            mContentMagin = dataItemBean.getContentLayoutMagin();
        } else {
            mContentMagin = DensityUtil.dip2px(mContext,10);
        }
        LayoutParams rootParams = (LayoutParams) mRootlayout.getLayoutParams();
        if (mBindItemBean.getItemHight() != 0) {
            rootParams.height = mBindItemBean.getItemHight();
        } else {
            rootParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        params.topMargin = mContentMagin;
        params.bottomMargin = mContentMagin;
        mContenLayout.setLayoutParams(params);
        mRootlayout.setLayoutParams(rootParams);
        if (dataItemBean.isShowLeftCheckBox()) {
            mLeftCheckBox.setVisibility(View.VISIBLE);
            boolean isChecked = dataItemBean.isLeftCheckBoxIsChecked();
            if (mSelectUtils != null && !mChangeSelectRefresh) {
                isChecked = checkContain(dataItemBean);
                dataItemBean.setLeftCheckBoxIsChecked(isChecked);
            }
            mChangeSelectRefresh = false;
            mLeftCheckBox.setChecked(isChecked);
        } else {
            mLeftCheckBox.setVisibility(View.GONE);
        }

        HeadImageViewConfig.load(dataItemBean,mImageView);

        mTitleTextView.setText(dataItemBean.getTitle());
        if (dataItemBean.isHintShow()) {
            mHintTextView.setVisibility(View.VISIBLE);
            mHintTextView.setText(dataItemBean.getHint());
            if (dataItemBean.getHintLeftMagin() != 0) {
                LayoutParams mHintParams =
                        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                mHintParams.leftMargin = dataItemBean.getHintLeftMagin();
                mHintTextView.setLayoutParams(mHintParams);
            }
        } else {
            mHintTextView.setVisibility(View.GONE);
        }
        if (StringUtils.isNotEmpty(dataItemBean.getLeftSecondText())) {
            mNoticeTextView.setText(dataItemBean.getLeftSecondText());
            mNoticeTextView.setVisibility(View.VISIBLE);
            if (dataItemBean.getLeftSecondTextColor() != 0) {
                mNoticeTextView.setTextColor(dataItemBean.getLeftSecondTextColor());
            }
            if (dataItemBean.getLeftSecondTextbg() != 0) {
                mNoticeTextView.setBackgroundResource(dataItemBean.getLeftSecondTextbg());
            } else {
                mNoticeTextView.setBackgroundResource(R.drawable.transparent);
            }
        } else {
            mNoticeTextView.setVisibility(View.GONE);
        }
        if (dataItemBean.getLeftSecondImgResId() != 0) {
            mNoticeImageView.setVisibility(View.VISIBLE);
            mNoticeImageView.setImageResource(dataItemBean.getLeftSecondImgResId());
        } else {
            mNoticeImageView.setVisibility(View.GONE);
        }
        //设置右侧是否显示
        if (dataItemBean.getRightFirstTvColor() == 0) {
            mRightFirstTextView.setTextColor(getResources().getColor(R.color.transparent));
        } else { //必须由资源文件里面的定义颜色
            mRightFirstTextView.setTextColor(getResources().getColor(dataItemBean.getRightFirstTvColor()));
        }
        if (StringUtils.isNotEmpty(dataItemBean.getRightFirstText())) {
               if (dataItemBean.getRightFirstText().contains("广")){
                   mRightFirstTextView.setGravity(Gravity.RIGHT);
                mRightFirstTextView.setSingleLine(false);
                mRightFirstTextView.setText(dataItemBean.getRightFirstText());
                mRightFirstTextView.setVisibility(View.VISIBLE);

            }else{
                   mRightFirstTextView.setText(dataItemBean.getRightFirstText());
                   mRightFirstTextView.setVisibility(View.VISIBLE);
               }

        }

        else {
            mRightFirstTextView.setVisibility(View.GONE);
        }
        if (dataItemBean.getEidtSettings().isShowEdittext()) {//输入框
            mContentEditLayout.setCallback(new EdittextLayoutView.CallbackListener() {
                @Override
                public void onCallback(String text) {
                    dataItemBean.getEidtSettings().setEditContent(text);
                }
            });
            mContentEditLayout.setVisibility(View.VISIBLE);
            if (!dataItemBean.getEidtSettings().isEdittextCanEdit()) {
                mContentEditLayout.setCanUserInput(false);
            } else {
                mContentEditLayout.setCanUserInput(true);
            }
            mContentEditLayout.setShowCleanImg(dataItemBean.isShowCleanImg());
            mContentEditLayout.setHint(dataItemBean.getEidtSettings().getEditHint());
            mContentEditLayout.setText(dataItemBean.getEidtSettings().getEditContent());
            mContentEditLayout.setOpenKeybord(dataItemBean.getEidtSettings().isOpenKeybord());
            dataItemBean.getEidtSettings().setOpenKeybord(false);
            mContentEditLayout.setInputType(dataItemBean.getEidtSettings().getInputType());
        } else {
            mContentEditLayout.setVisibility(View.GONE);
        }
//        //添加右侧图片显示如头像
        RightSecondImgSettings secondImgSetting=dataItemBean.getRightSecondImgSettings();
        if (StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgURL())||StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgStorePath()) ||secondImgSetting.getRightSecondImgResId() != 0) {
            LayoutParams mHeadParams = (LayoutParams) mRightSecondImgeView.getLayoutParams();
            mHeadParams.gravity = Gravity.CENTER_VERTICAL;
            if (dataItemBean.getRightSecondImgSettings().getRightSecondImgRadius() != 0) {
                mHeadParams.width = dataItemBean.getRightSecondImgSettings().getRightSecondImgRadius();
                mHeadParams.height = dataItemBean.getRightSecondImgSettings().getRightSecondImgRadius();
            }
            int contentMargin = DensityUtil.dip2px(mContext,5);
            mHeadParams.rightMargin = contentMargin;
            mRightSecondImgeView.setLayoutParams(mHeadParams);
            if (StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgURL())) {
                mRightSecondImgeView.setVisibility(View.VISIBLE);
                if (dataItemBean.getIloadImage() != null) {
                    dataItemBean.getIloadImage().load(secondImgSetting.getRightSecondImgURL(), mRightSecondImgeView);
                }
            }
            else if (StringUtils.isNotEmpty(dataItemBean.getRightSecondImgSettings().getRightSecondImgStorePath())) {
                mRightSecondImgeView.setVisibility(View.VISIBLE);
                if (dataItemBean.getIloadImage()!=null){
                    dataItemBean.getIloadImage().loadPath(dataItemBean.getRightSecondImgSettings().getRightSecondImgStorePath(), mRightSecondImgeView);
                }
            }
            else if (dataItemBean.getRightSecondImgSettings().getRightSecondImgResId() != 0) {
                mRightSecondImgeView.setVisibility(View.VISIBLE);
                if (dataItemBean.getIloadImage() != null) {
                    dataItemBean.getIloadImage().loadResourceId(dataItemBean.getRightSecondImgSettings().getRightSecondImgResId(), mRightSecondImgeView);
                }
            }
          
        } else {
            boolean isShowEmptyImge = dataItemBean.getRightSecondImgSettings().isShowEmptyImg();
            if (isShowEmptyImge) {
                mRightSecondImgeView.setVisibility(View.VISIBLE);
                if (dataItemBean.getIloadImage()!=null){
                    dataItemBean.getIloadImage().loadResourceId(R.drawable.empty_photo,mRightSecondImgeView);
                }
            } else {
                mRightSecondImgeView.setVisibility(View.GONE);
            }
        }
//        //添加选中按钮显示
        if (dataItemBean.isShowRightCheckbox()) {
            mSwitchButton.setCheckState(dataItemBean.isRightCheckBoxSelect());
            mSwitchButton.setVisibility(View.VISIBLE);
        } else {
            mSwitchButton.setVisibility(View.GONE);
        }
        if (StringUtils.isNotEmpty(dataItemBean.getRightFirstButtonText())) {
            mRightFirstButton.setVisibility(View.VISIBLE);
            mRightFirstButton.setText(dataItemBean.getRightFirstButtonText());
            if (dataItemBean.getRightFirstButtonBgResId() != 0) {
                mRightFirstButton.setVisibility(View.VISIBLE);
                mRightFirstButton.setBackgroundResource(dataItemBean.getRightFirstButtonBgResId());
            }
        } else {
            mRightFirstButton.setVisibility(View.GONE);
        }
        if (dataItemBean.getRightCenterScaleImgResId() != 0) {
            mRightCenterScaleImgeLayout.setVisibility(View.VISIBLE);
            if (dataItemBean.getIloadImage()!=null){
                dataItemBean.getIloadImage().loadResourceId(dataItemBean.getRightCenterScaleImgResId(),mRightCenterScaleImgeView);
            }
        } else {
            mRightCenterScaleImgeLayout.setVisibility(View.GONE);
        }
        if (dataItemBean.getRightUnreadCount() != 0) {
//            mMessageCountView.setVisibility(View.VISIBLE);
//            float textSize = ResourcesUtil.getResourcesFloat(mContext, R.string.recentcontactsitemview_textsize);
//            mMessageCountView.setTextSize(textSize);
//            mMessageCountView.setImage(R.drawable.item_recentcontacts_donotbother);
//            mMessageCountView.setMessageCount(dataItemBean.getRightUnreadCount());
        } else {
//            mMessageCountView.reset();
        }
        //添加右侧第一个图片如指向图片
        if (dataItemBean.getRightFistImgeSettings().getRightFirstImgResId() != 0) {
            LayoutParams mRightFirstParams = (LayoutParams) mRightFirstImageView.getLayoutParams();
            if (dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius() != 0) {
                mRightFirstParams.width = dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius();
                mRightFirstParams.height = dataItemBean.getRightFistImgeSettings().getRightFirstImgRadius();
            }
            boolean isInvisiable = dataItemBean.getRightFistImgeSettings().isInvisiable();
            if (isInvisiable) {
                mRightFirstImageView.setVisibility(View.INVISIBLE);
            } else {
                mRightFirstImageView.setVisibility(View.VISIBLE);
            }
            mRightFirstImageView.setLayoutParams(mRightFirstParams);
            if (dataItemBean.getIloadImage()!=null){
                dataItemBean.getIloadImage().loadResourceId(dataItemBean.getRightFistImgeSettings().getRightFirstImgResId(),mRightFirstImageView);
            }
        } else {
            mRightFirstImageView.setVisibility(View.GONE);
        }
        initListener();
    }

//    private void loadImage(AddressItemBean dataItemBean) {
//        if (dataItemBean.getHeadImgeSettings().getHeadImgDrawableId() != 0) {
//            if (dataItemBean.getIloadImage()!=null){
//                dataItemBean.getIloadImage().loadResourceId(dataItemBean.getHeadImgeSettings().getHeadImgDrawableId(),mImageView);
//            }
//        }
//        else if (StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgPath())) {
//            if (dataItemBean.getIloadImage()!=null) {
//                dataItemBean.getIloadImage().loadPath(dataItemBean.getHeadImgeSettings().getHeadImgPath(), mImageView);
//            }
//        }
//        else if (StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgUrl())) {
//            if (dataItemBean.getIloadImage()!=null) {
//                dataItemBean.getIloadImage().load(dataItemBean.getHeadImgeSettings().getHeadImgUrl(), mImageView);
//            }
//        }
//        else if (null!=dataItemBean.getHeadImgeSettings().getBitmap()) {
//            if (dataItemBean.getIloadImage()!=null) {
//                dataItemBean.getIloadImage().load(dataItemBean.getHeadImgeSettings().getBitmap(), mImageView);
//            }
//        }
//    }

    @Override
    public void initSelectUtils(MutiTypeSelectUtils selectUtils) {
        this.mSelectUtils = selectUtils;
    }

    public boolean checkContain(AddressItemBean mItemMap) {
        boolean isChecked = false;
        boolean isContainType = mSelectUtils.getSelectedMap().containsKey(mItemMap.getSelectType());
        List<AddressItemBean> typeListMap = null;
        if (isContainType) {//从多类型中获取数据源
            typeListMap = mSelectUtils.getSelectedMap().get(mItemMap.getSelectType());
            if (typeListMap != null) {
                for (int i = 0; i < typeListMap.size(); i++) {
                    AddressItemBean selectItemMap = typeListMap.get(i);
                    if (selectItemMap.getId().equals(mItemMap.getId())) {
                        isChecked = true;
                        break;
                    }
                }

            }
        }
        return isChecked;
    }

    public void initListener() {
        if (mBindItemBean != null && mBindItemBean.getOnRightCheckBoxListener() != null) {
            mSwitchButton.setOnStateChangeListener(new IOSSwitchButton.OnStateChangeListener() {
                @Override
                public void onStateChanged(boolean isOn) {
                    mBindItemBean.setRightCheckBoxSelect(isOn);
                    if (mBindItemBean.getOnRightCheckBoxListener() != null) {
                        mBindItemBean.getOnRightCheckBoxListener().onStateChanged(isOn);
                    }
                }
            });
        }

        if (mBindItemBean == null || mBindItemBean.getOnItemListener() == null) {
            mRightFirstButton.setOnClickListener(null);
            mRightCenterScaleImgeLayout.setOnClickListener(null);
            mRightSecondImgeView.setOnClickListener(null);
            mRightFirstImageView.setOnClickListener(null);
            mRootlayout.setOnClickListener(null);
            mRootlayout.setOnLongClickListener(null);
            mRightFirstTextView.setOnClickListener(null);
            return;
        }
        final onItemClick onItemListener = mBindItemBean.getOnItemListener();
        mRightFirstButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(ClickTypeEnum.RIGHTBUTTION, mBindItemBean);
            }
        });
        mRightCenterScaleImgeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(ClickTypeEnum.RIGHT_SCALE_CENTER_IMG, mBindItemBean);
            }
        });
        mRightSecondImgeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(ClickTypeEnum.RIGHT_SECOND_IMG, mBindItemBean);
            }
        });
        mRightFirstImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onItemClick(ClickTypeEnum.RIGHT_FIRST_IMG, mBindItemBean);
            }
        });

        mRootlayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(onItemListener);
            }
        });

        mRootlayout.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemListener != null) {
                    onItemListener.onItemClick(ClickTypeEnum.ITEM_LONG, mBindItemBean);
                }
                return true;
            }
        });

        mRightFirstTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(onItemListener);
            }
        });
    }

    private void onItemClick(onItemClick onItemListener) {
        if (mBindItemBean.isShowLeftCheckBox()) {
            mLeftCheckBox.setVisibility(View.VISIBLE);
            boolean isChecked = mBindItemBean.isLeftCheckBoxIsChecked();
            if (mSelectUtils != null) {
                boolean selectReuslt = mSelectUtils.select(!isChecked, mBindItemBean);
                mBindItemBean.setLeftCheckBoxIsChecked(selectReuslt);
                mChangeSelectRefresh = true;
                initData(mBindItemBean);
            }
        }
        if (onItemListener != null) {
            onItemListener.onItemClick(ClickTypeEnum.ITEM, mBindItemBean);
        }
    }

    public void setRightFirstText(String rightFirstText) {
        mRightFirstTextView.setText(rightFirstText);
    }

    public String getEditextContent() {
        String content = mContentEditLayout.getText().trim();
        return content;
    }

    public AddressItemBean getBindItemBean() {
        return mBindItemBean;
    }



}


