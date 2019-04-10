package com.easy.recycleview.recycleview.item;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


//import com.core.imge.ImageUtils;
import com.easy.recycleview.base.BaseLinearLayout;
import com.easy.recycleview.recycleview.EdittextLayoutView;
import com.easy.recycleview.recycleview.MessageCountView;
import com.easy.recycleview.recycleview.button.IOSSwitchButton;
import com.easy.recycleview.recycleview.item.config.ContentLayoutConfig;
import com.easy.recycleview.recycleview.item.config.EditLayoutConfig;
import com.easy.recycleview.recycleview.item.config.HeadImageViewConfig;
import com.easy.recycleview.recycleview.item.config.HintTextViewConfig;
import com.easy.recycleview.recycleview.item.config.LeftCheckBoxConfig;
import com.easy.recycleview.recycleview.item.config.ListenerConfig;
import com.easy.recycleview.recycleview.item.config.NoticeConfig;
import com.easy.recycleview.recycleview.item.config.RightCenterScaleImgConfig;
import com.easy.recycleview.recycleview.item.config.RightFirstButtonConfig;
import com.easy.recycleview.recycleview.item.config.RightFirstImageViewConfig;
import com.easy.recycleview.recycleview.item.config.RightFirstTextViewConfig;
import com.easy.recycleview.recycleview.item.config.RightSecondImgeViewConfig;
import com.easy.recycleview.recycleview.item.config.RightUnreadCountConfig;
import com.easy.recycleview.recycleview.item.config.RootlayoutConfig;
import com.easy.recycleview.recycleview.item.config.SwitchButtonConfig;
import com.easy.recycleview.recycleview.item.config.TitleTextViewConfig;
import com.easy.recycleview.recycleview.item.inter.IItemView;
import com.easy.recycleview.recycleview.sectionview.MutiTypeSelectUtils;
import com.easysoft.dynamicrecycleview.R;

/**
 * 创建者：林党宏
 * 时间：2017/1/17
 * 注释：通用itemview  信息item 显示
 * 由数据控制是否显示界面
 */

public class ContentItemView extends BaseLinearLayout implements IItemView {
    public LinearLayout mRootlayout;
    /** 右侧chcekbox */
    public IOSSwitchButton mSwitchButton;
    /** 左侧选中图标 */
    public CheckBox mLeftCheckBox;
    Context mContext;
    public TextView mTitleTextView;
    public TextView mHintTextView;
    public TextView mNoticeTextView;
    public  ImageView mNoticeImageView;
    public TextView mRightFirstTextView;
    ImageView mImageView;
    public ImageView mRightSecondImgeView;
    public ImageView mRightFirstImageView;
    /** 右侧按钮 */
    public Button mRightFirstButton;
    /** 右侧布局 */
    LinearLayout mRightLayout;
    /** 右侧点击图片 */
    public ImageView mRightCenterScaleImgeView;
    public LinearLayout mRightCenterScaleImgeLayout;
    /** 输入框 */
    public  EdittextLayoutView mContentEditLayout;
    /** 内容布局可设置magin布局 */
      public  LinearLayout mContenLayout;
    /** 内容布局可设置magin布局 */
    MessageCountView mMessageCountView;
    /** 绑定数据 */
    public AddressItemBean mBindItemBean = new AddressItemBean();
    /** 多选控制工具 */
    public MutiTypeSelectUtils mSelectUtils;

    public boolean mChangeSelectRefresh = false;
    public ContentItemView(Context context) {
        super(context);
        initUI(context);
    }

    public ContentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    protected void initUI(Context context) {
        mContext = context;
        View rootView  = LayoutInflater.from(context).inflate(R.layout.view_item, this,true);
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
        mBindItemBean = dataItemBean;//添加头部 //添加左侧侧布局
        ContentLayoutConfig.load(this,dataItemBean);
        RootlayoutConfig.load(this,dataItemBean);
        LeftCheckBoxConfig.load(this,dataItemBean);
        HeadImageViewConfig.load(dataItemBean,mImageView);
        TitleTextViewConfig.load(this,dataItemBean);
        HintTextViewConfig.load(this,dataItemBean);
        NoticeConfig.load(this,dataItemBean);
        RightFirstTextViewConfig.load(this,dataItemBean);
        EditLayoutConfig.load(this,dataItemBean);
        RightSecondImgeViewConfig.load(this,dataItemBean);
        SwitchButtonConfig.load(this,dataItemBean);
        RightFirstButtonConfig.load(this,dataItemBean);
        RightCenterScaleImgConfig.load(this,dataItemBean);
        RightUnreadCountConfig.load(this,dataItemBean);
        RightFirstImageViewConfig.load(this,dataItemBean);
        ListenerConfig.load(this);

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


