package com.core.recycleview.item;

import java.util.List;

import android.content.Context;
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
import butterknife.Bind;
import butterknife.ButterKnife;
import dynamicrecycleview.easysoft.com.lib.R;

import com.core.imge.ImageUtils;
import com.core.recycleview.MessageCountView;
import com.core.recycleview.button.IOSSwitchButton;
import com.core.recycleview.item.bean.AddressRightSecondImgSettings;
import com.core.recycleview.sectionview.MutiTypeSelectUtils;
import com.core.utils.DensityUtil;
import com.core.utils.StringUtils;

/**
 * 创建者：林党宏
 * 时间：2017/1/17
 * 注释：通用itemview  信息item 显示
 * 由数据控制是否显示界面
 */

public class ContentItemView extends LinearLayout implements IItemView {
    @Bind(R.id.rootlayout)
    LinearLayout mRootlayout;
    /** 右侧chcekbox */
    @Bind(R.id.switchButton)
    IOSSwitchButton mSwitchButton;
    /** 左侧选中图标 */
    @Bind(R.id.chkItem)
    CheckBox mLeftCheckBox;
    Context mContext;
    @Bind(R.id.titleTextView)
    TextView mTitleTextView;
    @Bind(R.id.hintTextView)
    TextView mHintTextView;
    @Bind(R.id.noticeTextView)
    TextView mNoticeTextView;
    @Bind(R.id.noticeImgView)
    ImageView mNoticeImageView;
    @Bind(R.id.rightFirstTextView)
    TextView mRightFirstTextView;
    @Bind(R.id.headImgeView)
    ImageView mImageView;
    @Bind(R.id.rightSecondImgeView)
    ImageView mRightSecondImgeView;
    @Bind(R.id.rightFirstImgeView)
    ImageView mRightFirstImageView;
    /** 右侧按钮 */
    @Bind(R.id.rightFirstButton)
    Button mRightFirstButton;
    /** 右侧布局 */
    @Bind(R.id.rightLayout)
    LinearLayout mRightLayout;
    /** 右侧点击图片 */
    @Bind(R.id.rightCenterScaleImgeView)
    ImageView mRightCenterScaleImgeView;
    @Bind(R.id.rightCenterScaleImgeLayout)
    LinearLayout mRightCenterScaleImgeLayout;
    /** 输入框 */
    @Bind(R.id.edtLayout)
    EdittextLayoutView mContentEditLayout;
    /** 内容布局可设置magin布局 */
    @Bind(R.id.rootContentLayout)
    LinearLayout mContenLayout;
    /** 内容布局可设置magin布局 */
    @Bind(R.id.messageCountView)
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
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_item, this, true);
        ButterKnife.bind(this);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public void initData(final AddressItemBean dataItemBean) {
        //添加头部 //添加左侧侧布局
        mBindItemBean = dataItemBean;
        LayoutParams params = (LayoutParams) mContenLayout.getLayoutParams();
        if (dataItemBean.getContentLayoutMagin() != 0) {
            mContentMagin = dataItemBean.getContentLayoutMagin();
        } else {
            mContentMagin = DensityUtil.dip2px(mContext,2);
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
        if (StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgUrl())|| dataItemBean.getHeadImgeSettings().getHeadImgDrawableId() != 0 || StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgUserId())) {//添加头像资源id
            mImageView.setVisibility(View.VISIBLE);
            LayoutParams mHeadParams = (LayoutParams) mImageView.getLayoutParams();
            mHeadParams.gravity = Gravity.CENTER_VERTICAL;

            if (dataItemBean.getHeadImgeSettings().getHeadImgWidth() != 0&&dataItemBean.getHeadImgeSettings().getHeadImgHeight() !=0){
                mHeadParams.width = dataItemBean.getHeadImgeSettings().getHeadImgWidth();
                mHeadParams.height = dataItemBean.getHeadImgeSettings().getHeadImgHeight();
            }else  if (dataItemBean.getHeadImgeSettings().getHeadImgRadius() != 0) {
                mHeadParams.width = dataItemBean.getHeadImgeSettings().getHeadImgRadius();
                mHeadParams.height = dataItemBean.getHeadImgeSettings().getHeadImgRadius();
            }
            mImageView.setLayoutParams(mHeadParams);
        } else {
            mImageView.setVisibility(View.GONE);
        }
        if (dataItemBean.getHeadImgeSettings().getHeadImgDrawableId() != 0) {
//            ImageManager.get().loadDefaultHeadImg(mImageView, dataItemBean.getHeadImgeSettings().getHeadImgDrawableId());

        } 
        else if (StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgUrl())) {
//            ImageManager.get().loadDefaultHeadImg(mImageView, dataItemBean.getHeadImgeSettings().getHeadImgDrawableId());
   		 ImageUtils.getInStance().load( dataItemBean.getHeadImgeSettings().getHeadImgUrl(), mImageView);

        } 
        else if (StringUtils.isNotEmpty(dataItemBean.getHeadImgeSettings().getHeadImgUserId())) {
//            String name = dataItemBean.getHeadImgeSettings().getHeadImgUserName();
//            String enumType = dataItemBean.getHeadImgeSettings().getHeadImgType();
//            String headLoadType = ChatType.USER.getCode();
//            if (enumType.equals(AddressItemImgEnum.GROUP.toString())) {
//                headLoadType = ChatType.GROUP.getCode();
//            }
//            ImageManager.get().loadHeadImg(mImageView,
//                    dataItemBean.getHeadImgeSettings().getHeadImgUserId(),
//                    name, headLoadType);
        }
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
            mRightFirstTextView.setText(dataItemBean.getRightFirstText());
            mRightFirstTextView.setVisibility(View.VISIBLE);
        } else {
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
        AddressRightSecondImgSettings secondImgSetting=dataItemBean.getAddressRightSecondImgSettings();
        if (StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgURL())||StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgStorePath()) ||secondImgSetting.getRightSecondImgResId() != 0) {
            LayoutParams mHeadParams = (LayoutParams) mRightSecondImgeView.getLayoutParams();
            mHeadParams.gravity = Gravity.CENTER_VERTICAL;
            if (dataItemBean.getAddressRightSecondImgSettings().getRightSecondImgRadius() != 0) {
                mHeadParams.width = dataItemBean.getAddressRightSecondImgSettings().getRightSecondImgRadius();
                mHeadParams.height = dataItemBean.getAddressRightSecondImgSettings().getRightSecondImgRadius();
            }
            int contentMargin = DensityUtil.dip2px(mContext,5);
            mHeadParams.rightMargin = contentMargin;
            mRightSecondImgeView.setLayoutParams(mHeadParams);
            if (StringUtils.isNotEmpty(secondImgSetting.getRightSecondImgURL())) {
            	mRightSecondImgeView.setVisibility(View.VISIBLE);
            	ImageUtils.getInStance().load(secondImgSetting.getRightSecondImgURL(), mRightSecondImgeView);
            }
            else if (StringUtils.isNotEmpty(dataItemBean.getAddressRightSecondImgSettings().getRightSecondImgStorePath())) {
                mRightSecondImgeView.setVisibility(View.VISIBLE);
                ImageUtils.getInStance().loadPath(dataItemBean.getAddressRightSecondImgSettings().getRightSecondImgStorePath(), mRightSecondImgeView);
            }
            else if (dataItemBean.getAddressRightSecondImgSettings().getRightSecondImgResId() != 0) {
                mRightSecondImgeView.setVisibility(View.VISIBLE);
                ImageUtils.getInStance().loadResourceId( dataItemBean.getAddressRightSecondImgSettings().getRightSecondImgResId(), mRightSecondImgeView);
            }
          
        } else {
            boolean isShowEmptyImge = dataItemBean.getAddressRightSecondImgSettings().isShowEmptyImg();
            if (isShowEmptyImge) {
                mRightSecondImgeView.setVisibility(View.VISIBLE);
//                ImageManager.get().loadDefaultHeadImg(mRightSecondImgeView, R.drawable.common_empty_photo);
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
//            ImageManager.get().loadDefaultHeadImg(mRightCenterScaleImgeView, dataItemBean.getRightCenterScaleImgResId());
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
//            ImageManager.get().loadDefaultHeadImg(mRightFirstImageView, dataItemBean.getRightFistImgeSettings().getRightFirstImgResId());
        } else {
            mRightFirstImageView.setVisibility(View.GONE);
        }
        initListener();
    }

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


