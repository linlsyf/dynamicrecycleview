package com.core.recycleview.item;

import android.content.Context;
import android.text.InputType;
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

import com.core.recycleview.EdittextLayoutView;
import com.core.recycleview.SimpleMap;
import com.core.recycleview.button.IOSSwitchButton;
import com.core.recycleview.sectionview.MutiTypeSelectUtils;
import com.core.utils.DensityUtil;
import com.easysoft.dynamicrecycleview.R;

/**
 *创建者：林党宏
 *时间：2017/1/17
 *注释：通用itemview  信息item 显示
 * 由数据控制是否显示界面
 */

public class ItemView extends LinearLayout implements IItemView {
    /**标题*/
    public static String TITLE="title";

    public static String IMGAE_NAME="imge_name";
    /**hint*/
    public static String HINT="hint";
    /**hint的偏移量*/
    public static String HINT_LEFT_MARGIN="hint_left_margin";
    /**是否显示左侧checkbox*/
    public static String SHOW_LEFT_CHCECKBOX="show_left_checkbox";

    /**是否显示左侧checkbox*/
    public static String IS_LEFT_CHCECKBOX_CHECKED="is_left_checkbox_checked";

    /**是否显示右侧checkbox*/
    public static String SHOW_RIGHT_CHECKBOX="show_right_checkbox";

    /**头像资源文件*/
    public static String HEAD_IMG_DRAWABLE_ID="head_img_drawable_id";
    /**左侧头像图片资源id */
    public static String HEAD_IMG_RADIUS = "head_img_radius";
    /**头像用户id*/
    public static String HEAD_IMG_USER_ID="head_img_id";
    /**左侧第二个文字*/
    public static String LEFT_SECOND_TEXT="left_second_txt";
    /**左侧第二个文字*/
    public static String LEFT_SECOND_TEXT_COLOR="left_second_txt_color";
    /**左侧第二个文字背景图片*/
    public static String LEFT_SECOND_TEXT_BACKGROUND="left_second_txt_backgroud";

    /**右侧图片如箭头指向，加载图片*/
    public static String RIGHT_FIRST_DRAWABLE_ID="right_first_img_drawable_id";

    /**右侧第二个图片用户id*/
    public static String RIGHT_SECOND_IMG_USER_ID="right_second_img_user_id";

    /**右侧第二个图片用户id*/
    public static String RIGHT_SECOND_DRAWABLE_ID="right_second_img_drawable_id";
    /**右侧第一个图片文字*/
    public static String RIGHT_FIRST_TEXT="right_first_text";
    /**右侧第一个图片文字*/
    public static String RIGHT_FIRST_BUTTON_TEXT="right_first_button_text";
    /**右侧侧第一个图片宽高 */
    public static String RIGHT_FIRST_IMG_RADIUS = "right_first_img_radius";
    /**右侧侧第二个图片宽高 */
    public static String RIGHT_SECOND_IMG_RADIUS = "right_second_img_radius";
   /**右侧按钮图片资源id */
   public static final String RIGHT_FIRST_BUTTON_BG_RESID = "right_first_button_bg_resid";
   /**右侧按钮图片资源id */
   public static final String RIGHT_SCALE_CENTER_IMG_RESID = "right_scale_img_resid";
   /**右侧输入框是否显示 */
   public static final String RIGHT_EDIT_LAYOUT_SHOW= "right_edit_layout_show";
   /**右侧输入框是否可编辑 */
   public static final String RIGHT_EDIT_LAYOUT_CAN_EDIT= "right_edit_layout_chanedit";
    /**提示内容 */
    public static final String RIGHT_EDIT_LAYOUT_HINT= "right_edit_layout_hint";



    /**编辑内容 */
    public static final String EDIT_CONTENT= "eidt_content";
    /**编辑内容类型 */
    public static final String EDIT_INPUT_TYPE= "eidt_InputType";

    @Bind(R.id.rootlayout)
    LinearLayout  mRootlayout;
   /**右侧chcekbox */
   @Bind(R.id.switchButton)
   IOSSwitchButton mSwitchButton;
    /** 左侧选中图标*/
    @Bind(R.id.chkItem)
    CheckBox mLeftCheckBox;
    Context mContext;
    @Bind(R.id.titleTextView)
    TextView mTitleTextView;
    @Bind(R.id.hintTextView)
    TextView mHintTextView;
    @Bind(R.id.noticeTextView)
    TextView mNoticeTextView;
    @Bind(R.id.rightFirstTextView)
    TextView mRightFirstTextView;
    @Bind(R.id.headImgeView)
    ImageView mImageView;
    @Bind(R.id.rightSecondImgeView)
    ImageView mRightSecondImgeView;
    @Bind(R.id.rightFirstImgeView)
    ImageView mRightFirstImageView;
    /**右侧布局 */
    @Bind(R.id.rightFirstButton)
    Button mRightFirstButton;
    /**右侧布局 */
    @Bind(R.id.rightLayout)
    LinearLayout mRightLayout;
    /**右侧布局 */
    @Bind(R.id.rightCenterScaleImgeView)
    ImageView mRightCenterScaleImgeView;
    /**右侧布局 */
    @Bind(R.id.edtLayout)
    EdittextLayoutView mContentEditLayout;

    SimpleMap mItemMap;
    MutiTypeSelectUtils mSelectUtils;

    public ItemView(Context context) {
        super(context);
        initUI(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);

    }

    private void initUI(Context context) {
        mContext=context;

        View rootView=   LayoutInflater.from(context).inflate( R.layout.view_item, this, true);

        ButterKnife.bind(this);

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public void initData(final SimpleMap map){
        //添加头部 //添加左侧侧布局
        mItemMap=map;
        if (map.containsKey(IS_LEFT_CHCECKBOX_CHECKED)){
            mLeftCheckBox.setVisibility(View.VISIBLE);
            boolean isChecked=map.getBoolean(IS_LEFT_CHCECKBOX_CHECKED);
            if (mSelectUtils!=null){
//                isChecked = checkContain(map);
                map.put(IS_LEFT_CHCECKBOX_CHECKED,isChecked);
            }
            mLeftCheckBox.setChecked(isChecked);

        }else{
            mLeftCheckBox.setVisibility(View.GONE);

        }
        if (map.containsKey(ItemView.HEAD_IMG_DRAWABLE_ID)||map.containsKey(ItemView.HEAD_IMG_USER_ID)) {//添加头像资源id
             mImageView.setVisibility(View.VISIBLE);
            LayoutParams mHeadParams=   (LayoutParams) mImageView.getLayoutParams();
                mHeadParams.gravity= Gravity.CENTER_VERTICAL;
                if (map.containsKey(HEAD_IMG_RADIUS)){
                    mHeadParams.width=map.getInt(HEAD_IMG_RADIUS);
                    mHeadParams.height=map.getInt(HEAD_IMG_RADIUS);
                }
            mImageView.setLayoutParams(mHeadParams);
        }else{
            mImageView.setVisibility(View.GONE);
        }
                if(map.containsKey(ItemView.HEAD_IMG_DRAWABLE_ID)){
                    mImageView.setImageResource(map.getInt(ItemView.HEAD_IMG_DRAWABLE_ID));
                }
                else if(map.containsKey(ItemView.HEAD_IMG_USER_ID)){
//                    String name= map.getString(ItemView.IMGAE_NAME)!=null?map.getString(ItemView.IMGAE_NAME):"";
//                    ImageManager.get().loadHeadImg(mImageView,
//                            map.getString(ItemView.HEAD_IMG_USER_ID),
//                            name,
//                            ChatType.USER.getCode()
//                    );
                }

        mTitleTextView.setText(map.getString(ItemView.TITLE));
        if (map.containsKey(ItemView.HINT)){
            mHintTextView.setVisibility(View.VISIBLE);
                mHintTextView.setText(map.getString(ItemView.HINT));

            if (map.containsKey(ItemView.HINT_LEFT_MARGIN)){
                LayoutParams mHintParams =
                        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    mHintParams.leftMargin=map.getInt(ItemView.HINT_LEFT_MARGIN);
                mHintTextView.setLayoutParams(mHintParams);
            }
        }else{
            mHintTextView.setVisibility(View.GONE);
        }

        if (map.containsKey(ItemView.LEFT_SECOND_TEXT)){
            mNoticeTextView.setText(map.getString(ItemView.LEFT_SECOND_TEXT));
            mNoticeTextView.setVisibility(View.VISIBLE);
            if (map.containsKey(LEFT_SECOND_TEXT_COLOR)){
                mNoticeTextView.setText(map.getInt(LEFT_SECOND_TEXT_COLOR));
            }
            if (map.containsKey(EDIT_CONTENT)){
                mNoticeTextView.setText(map.getString(EDIT_CONTENT));
            }else{
                mNoticeTextView.setText("");
            }
        }else{
            mNoticeTextView.setVisibility(View.GONE);

        }

        //设置右侧是否显示
            if (map.containsKey(RIGHT_FIRST_TEXT)){
                mRightFirstTextView.setText(map.getString(ItemView.RIGHT_FIRST_TEXT));
                mRightFirstTextView.setVisibility(View.VISIBLE);

            }else{
                mRightFirstTextView.setVisibility(View.GONE);
            }
            if (map.containsKey(RIGHT_EDIT_LAYOUT_SHOW)){//输入框
                mContentEditLayout.setCallback(new EdittextLayoutView.CallbackListener() {
                    @Override
                    public void onCallback(String text) {
                        map.put(EDIT_CONTENT,text);
                    }
                });
                mContentEditLayout.setVisibility(View.VISIBLE);
                if (map.containsKey(RIGHT_EDIT_LAYOUT_CAN_EDIT)){
                    mContentEditLayout.setCanUserInput(false);
                }else{
                    mContentEditLayout.setCanUserInput(true);
                }
                if (map.containsKey(RIGHT_EDIT_LAYOUT_HINT)){
                    mContentEditLayout.setHint(map.getString(RIGHT_EDIT_LAYOUT_HINT));
                }else{
                    mContentEditLayout.setHint("");

                }
                if (map.containsKey(EDIT_CONTENT)){
                    mContentEditLayout.setText(map.getString(EDIT_CONTENT));
                }else{
                    mContentEditLayout.setText("");
                }
                if (map.containsKey(EDIT_INPUT_TYPE)){
                    mContentEditLayout.setInputType(map.getInt(EDIT_INPUT_TYPE));
                }else{
                    mContentEditLayout.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }else{
                mContentEditLayout.setVisibility(View.GONE);
            }

//
//        //添加右侧图片显示如头像
        if (map.containsKey(RIGHT_SECOND_IMG_USER_ID)|map.containsKey(RIGHT_SECOND_DRAWABLE_ID)){

                LayoutParams mHeadParams=( LayoutParams)mRightSecondImgeView.getLayoutParams();
                mHeadParams.gravity= Gravity.CENTER_VERTICAL;

                if (map.containsKey(RIGHT_SECOND_IMG_RADIUS)){
                    mHeadParams.width=map.getInt(RIGHT_SECOND_IMG_RADIUS);
                    mHeadParams.height=map.getInt(RIGHT_SECOND_IMG_RADIUS);
                }
                int contentMargin= DensityUtil.dip2px(mContext,5);
                mHeadParams.rightMargin=contentMargin;
            mRightSecondImgeView.setLayoutParams(mHeadParams);

            if(map.containsKey(ItemView.RIGHT_SECOND_IMG_USER_ID)){
                mRightSecondImgeView.setVisibility(View.VISIBLE);

                String imageName= map.getString(ItemView.IMGAE_NAME)==null?"":"";
//                ImageManager.get().loadHeadImg(mRightSecondImgeView,
//                        map.getString(ItemView.RIGHT_SECOND_IMG_USER_ID),
//                        imageName,
//                        ChatType.USER.getCode()
//                );
            }
            if(map.containsKey(RIGHT_SECOND_DRAWABLE_ID)){
                mRightSecondImgeView.setVisibility(View.VISIBLE);
                mRightSecondImgeView.setImageResource(map.getInt(RIGHT_SECOND_DRAWABLE_ID));
            }
        }
        else {
            mRightSecondImgeView.setVisibility(View.GONE);
        }
//        //添加选中按钮显示
        if (map.containsKey(SHOW_RIGHT_CHECKBOX)){
          mSwitchButton.setVisibility(View.VISIBLE);
        }else{
            mSwitchButton.setVisibility(View.GONE);

        }
        if (map.containsKey(RIGHT_FIRST_BUTTON_TEXT)){
            mRightFirstButton.setVisibility(View.VISIBLE);
            mRightFirstButton.setText(map.getString(RIGHT_FIRST_BUTTON_TEXT));
            if(map.containsKey(RIGHT_FIRST_BUTTON_BG_RESID)){
                mRightFirstButton.setVisibility(View.VISIBLE);
                mRightFirstButton.setBackgroundResource(map.getInt(RIGHT_FIRST_BUTTON_BG_RESID));
            }
        }else{
            mRightFirstButton.setVisibility(View.GONE);

        }
        if (map.containsKey(RIGHT_SCALE_CENTER_IMG_RESID)){
            mRightCenterScaleImgeView.setVisibility(View.VISIBLE);
            mRightCenterScaleImgeView.setImageResource(map.getInt(RIGHT_SCALE_CENTER_IMG_RESID));
        }else{
            mRightCenterScaleImgeView.setVisibility(View.GONE);
        }
//            //添加右侧第一个图片如指向图片
        if (map.containsKey(RIGHT_FIRST_DRAWABLE_ID)){
                LayoutParams mRightFirstParams=(LayoutParams)mRightFirstImageView.getLayoutParams();
                if (map.containsKey(RIGHT_FIRST_IMG_RADIUS)){
                    mRightFirstParams.width=map.getInt(RIGHT_FIRST_IMG_RADIUS);
                    mRightFirstParams.height=map.getInt(RIGHT_FIRST_IMG_RADIUS);
                }
            mRightFirstImageView.setVisibility(View.VISIBLE);
            mRightFirstImageView.setLayoutParams(mRightFirstParams);
            mRightFirstImageView.setImageResource(map.getInt(ItemView.RIGHT_FIRST_DRAWABLE_ID));
        }else{
            mRightFirstImageView.setVisibility(View.GONE);

        }
    }

    @Override
    public void initSelectUtils(MutiTypeSelectUtils selectUtils) {
        this.mSelectUtils=selectUtils;

    }


//    public boolean checkContain( SimpleMap mItemMap){
//        if (mItemMap.get(AddressCommonKey.ID).equals("人员根目录__|0__|1")){
//            boolean toset=false;
//        }
//        boolean isChecked=false;
//        return isChecked;
//    }




    @Override
    public void initData(AddressItemBean map) {

    }

    public void setRightFirstText(String rightFirstText) {
//        this.rightFirstText = rightFirstText;
        mRightFirstTextView.setText(rightFirstText);
    }

    public String getEditextContent(){
        String content=mContentEditLayout.getText();
        return content;
    }
    public void setEditextText(String text){
        mContentEditLayout.setText(text);
    }


    public interface OnItemClickLisnter {
        void onItemClick(SimpleMap itemMap);
    }


}


