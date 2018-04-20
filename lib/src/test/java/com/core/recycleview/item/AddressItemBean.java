package com.core.recycleview.item;

import java.io.Serializable;

import com.core.recycleview.button.IOSSwitchButton;
import com.core.recycleview.item.bean.AddressEditSettings;
import com.core.recycleview.item.bean.AddressHeadImgeSettings;
import com.core.recycleview.item.bean.AddressRightFistImgeSettings;
import com.core.recycleview.item.bean.AddressRightSecondImgSettings;
import com.easysoft.costumes.R;

/**
 *创建者：林党宏
 *时间：2017/2/13
 *注释：通用通讯录界面item数据源
 */
public class AddressItemBean implements Serializable,Cloneable{
    /**id*/
    private  String id="";
    private  String parentId="";
    /**分组*/
    private  String section ="";
    /** 是否显示删除布局*/
    private  boolean isSectionShowDelete=false;
    /**隶属的选择类型 如部门或者人员*/
    private  String selectType ="";
    /**界面类型*/
    private  int viewType =IItemView.ViewTypeEnum.ITEM.value();
    /**标题*/
    private  String title="";
    /**副标题*/
    private  String hint ="";
    /**是否有聊天资格*/
    private boolean isTalk=true;
    /**副标题是否显示*/
    private  boolean hintShow =false;
    /**副标题magin left*/
    private  int hintLeftMagin =0;
    /**是否显示左侧checkbox*/
    private  boolean isShowLeftCheckBox =false;
    /**左侧checkbox选中状态*/
    private  boolean leftCheckBoxIsChecked =false;
    /**左侧第二个文字*/
    private  String leftSecondText ="";
    /**左侧第二图片*/
    private  int leftSecondImgResId =0;
    /**左侧第二个文字颜色*/
    private  int leftSecondTextColor =0;
    /**左侧第二个文字背景图*/
    private  int leftSecondTextbg =0;

    /**右侧文字*/
    private  String rightFirstText="";
    /**右侧文字 必须由资源文件里面的定义颜色*/
    private  int rightFirstTvColor = R.color.common_title_color;

    /**右侧按钮文字*/
    private  String rightFirstButtonText="";
    /**右侧按钮图片资源id */
    private  int rightFirstButtonBgResId=0;
    /**右侧提示信息 */
    private  int  rightUnreadCount=0;
    /**右侧选择框是否显示 */
    private  boolean showRightCheckbox=false;
    /**右侧可点击图片资源id */
    private  int rightCenterScaleImgResId=0;
    /**内容布局magin */
    private  int contentLayoutMagin=0;
    /**在列表中的位置 */
    private int position=0;

    private boolean isRightCheckBoxSelect =false;

    private  boolean isAddSideBar=true;
    private  boolean showCleanImg=true;
      private int itemHight=0;
    /**该项数据控制的view是否可以编辑 */
    private boolean itemCanEdit=true;

    /**右侧第二个文字*/
    private  String rightSecondText ="";
    /**点击监听事件 */
    IItemView.onItemClick onItemListener;
     /**右侧切换按钮监听 */
    IOSSwitchButton.OnStateChangeListener  onRightCheckBoxListener;
    /**右侧第二个图片设置 */
    AddressRightSecondImgSettings addressRightSecondImgSettings =new AddressRightSecondImgSettings();
    /**输入框内容配置*/
    AddressEditSettings eidtSettings =new AddressEditSettings();
    /**右侧头部图片设置 */
    AddressHeadImgeSettings headImgeSettings =new AddressHeadImgeSettings();
    /**右侧第一个图片配置*/
    AddressRightFistImgeSettings rightFistImgeSettings =new AddressRightFistImgeSettings();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTalk() {
        return isTalk;
    }

    public void setTalk(boolean talk) {
        isTalk = talk;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isHintShow() {
        return hintShow;
    }

    public void setHintShow(boolean hintShow) {
        this.hintShow = hintShow;
    }

    public int getHintLeftMagin() {
        return hintLeftMagin;
    }

    public void setHintLeftMagin(int hintLeftMagin) {
        this.hintLeftMagin = hintLeftMagin;
    }

    public boolean isShowLeftCheckBox() {
        return isShowLeftCheckBox;
    }

    public void setShowLeftCheckBox(boolean showLeftCheckBox) {
        isShowLeftCheckBox = showLeftCheckBox;
    }

    public boolean isLeftCheckBoxIsChecked() {
        return leftCheckBoxIsChecked;
    }

    public void setLeftCheckBoxIsChecked(boolean leftCheckBoxIsChecked) {
        this.leftCheckBoxIsChecked = leftCheckBoxIsChecked;
    }



    public String getLeftSecondText() {
        return leftSecondText;
    }

    public void setLeftSecondText(String leftSecondText) {
        this.leftSecondText = leftSecondText;
    }

    public int getLeftSecondImgResId() {
        return leftSecondImgResId;
    }

    public void setLeftSecondImgResId(int leftSecondImgResId) {
        this.leftSecondImgResId = leftSecondImgResId;
    }

    public int getLeftSecondTextColor() {
        return leftSecondTextColor;
    }

    public void setLeftSecondTextColor(int leftSecondTextColor) {
        this.leftSecondTextColor = leftSecondTextColor;
    }

    public int getLeftSecondTextbg() {
        return leftSecondTextbg;
    }

    public void setLeftSecondTextbg(int leftSecondTextbg) {
        this.leftSecondTextbg = leftSecondTextbg;
    }





    public String getRightFirstText() {
        return rightFirstText;
    }

    public void setRightFirstText(String rightFirstText) {
        this.rightFirstText = rightFirstText;
    }

    public int getRightFirstTvColor() {
        return rightFirstTvColor;
    }

    public void setRightFirstTvColor(int rightFirstTvColor) {
        this.rightFirstTvColor = rightFirstTvColor;
    }

    public String getRightFirstButtonText() {
        return rightFirstButtonText;
    }

    public void setRightFirstButtonText(String rightFirstButtonText) {
        this.rightFirstButtonText = rightFirstButtonText;
    }

    public int getRightFirstButtonBgResId() {
        return rightFirstButtonBgResId;
    }

    public void setRightFirstButtonBgResId(int rightFirstButtonBgResId) {
        this.rightFirstButtonBgResId = rightFirstButtonBgResId;
    }

    public int getRightUnreadCount() {
        return rightUnreadCount;
    }

    public void setRightUnreadCount(int rightUnreadCount) {
        this.rightUnreadCount = rightUnreadCount;
    }

    public boolean isShowRightCheckbox() {
        return showRightCheckbox;
    }

    public void setShowRightCheckbox(boolean showRightCheckbox) {
        this.showRightCheckbox = showRightCheckbox;
    }

    public int getRightCenterScaleImgResId() {
        return rightCenterScaleImgResId;
    }

    public void setRightCenterScaleImgResId(int rightCenterScaleImgResId) {
        this.rightCenterScaleImgResId = rightCenterScaleImgResId;
    }

    public int getContentLayoutMagin() {
        return contentLayoutMagin;
    }

    public void setContentLayoutMagin(int contentLayoutMagin) {
        this.contentLayoutMagin = contentLayoutMagin;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public AddressHeadImgeSettings getHeadImgeSettings() {
        return headImgeSettings;
    }

    public void setHeadImgeSettings(AddressHeadImgeSettings headImgeSettings) {
        this.headImgeSettings = headImgeSettings;
    }

    public boolean isRightCheckBoxSelect() {
        return isRightCheckBoxSelect;
    }

    public void setRightCheckBoxSelect(boolean rightCheckBoxSelect) {
        this.isRightCheckBoxSelect = rightCheckBoxSelect;
    }

    public boolean isAddSideBar() {
        return isAddSideBar;
    }

    public void setAddSideBar(boolean addSideBar) {
        isAddSideBar = addSideBar;
    }

    public IItemView.onItemClick getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(IItemView.onItemClick onItemListener) {
        this.onItemListener = onItemListener;
    }

    public IOSSwitchButton.OnStateChangeListener getOnRightCheckBoxListener() {
        return onRightCheckBoxListener;
    }

    public void setOnRightCheckBoxListener(IOSSwitchButton.OnStateChangeListener onRightCheckBoxListener) {
        this.onRightCheckBoxListener = onRightCheckBoxListener;
    }

    public boolean isShowCleanImg() {
        return showCleanImg;
    }

    public void setShowCleanImg(boolean showCleanImg) {
        this.showCleanImg = showCleanImg;
    }

    public int getItemHight() {
        return itemHight;
    }

    public void setItemHight(int itemHight) {
        this.itemHight = itemHight;
    }


    public boolean isSectionShowDelete() {
        return isSectionShowDelete;
    }

    public void setSectionShowDelete(boolean sectionShowDelete) {
        isSectionShowDelete = sectionShowDelete;
    }
    public boolean isItemCanEdit() {
        return itemCanEdit;
    }

    public void setItemCanEdit(boolean itemCanEdit) {
        this.itemCanEdit = itemCanEdit;
    }

    public AddressRightSecondImgSettings getAddressRightSecondImgSettings() {
        return addressRightSecondImgSettings;
    }

    public void setAddressRightSecondImgSettings(AddressRightSecondImgSettings addressRightSecondImgSettings) {
        this.addressRightSecondImgSettings = addressRightSecondImgSettings;
    }

    public AddressEditSettings getEidtSettings() {
        return eidtSettings;
    }

    public void setEidtSettings(AddressEditSettings eidtSettings) {
        this.eidtSettings = eidtSettings;
    }

    public AddressRightFistImgeSettings getRightFistImgeSettings() {
        return rightFistImgeSettings;
    }

    public void setRightFistImgeSettings(AddressRightFistImgeSettings rightFistImgeSettings) {
        this.rightFistImgeSettings = rightFistImgeSettings;
    }

    public String getRightSecondText() {
        return rightSecondText;
    }

    public void setRightSecondText(String rightSecondText) {
        this.rightSecondText = rightSecondText;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
