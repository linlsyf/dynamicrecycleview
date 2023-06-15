package com.easy.recycleview.bean;

import com.easy.recycleview.custom.baseview.button.IOSSwitchButton;
import com.easy.recycleview.inter.IDyItemBean;
import com.easy.recycleview.inter.IItemView;
import com.easysoft.dynamicrecycleview.R;

import java.io.Serializable;
import java.util.UUID;

/**
 *创建者：林党宏
 *时间：2017/2/13
 *注释：通用通讯录界面item数据源
 */
public class DyItemBean implements IDyItemBean, Serializable,Cloneable{

    /** 执行回调*/
    /** 状态切换监听*/
//    private OnStateChangeListener mListener;
    /**id*/
    private  String id= UUID.randomUUID()+"";
    /**绑定数据*/
    private  Object bindObject;
    private  String parentId="";
    /**分组*/
    private  String section ="";
    /** 是否显示删除布局*/
    private  boolean isSectionShowDelete=false;
    /**隶属的选择类型 如部门或者人员*/
    private  String selectType ="";
    /**界面类型*/
    private  int viewType = IItemView.ViewTypeEnum.ITEM.value();

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
    /**是否点击事件是否执行*/
    private  boolean isOnItemClickAble =true;
    /**是否全部点击事件是否执行*/
    private  boolean isOnItemAllClickAble =true;

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
//    private  int rightFirstTvColor = 0;
    private  int rightFirstTvColor = R.color.common_title_color;

    /**右侧按钮文字*/
    private  String rightFirstButtonText="";
    /**右侧按钮图片资源id */
    private  int rightFirstButtonBgResId=0;
    /**右侧提示信息 */
    private  int  rightUnreadCount=0;
    /**右侧选择框是否显示 */
    private  boolean showRightCheckbox=false;

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

    /**title设置 */
    TitleSettings titleSettings =new TitleSettings();
    /**hint设置 */
    HintSettings hintSettings =new HintSettings();
    /**右侧第二个图片设置 */
    RightSecondImgSettings rightSecondImgSettings =new RightSecondImgSettings();
    /**输入框内容配置*/
    AddressEditSettings eidtSettings =new AddressEditSettings();
    /**右侧头部图片设置 */
    AddressHeadImgeSettings headImgeSettings =new AddressHeadImgeSettings();
    /**右侧第一个图片配置*/
    AddressRightFistImgeSettings rightFistImgeSettings =new AddressRightFistImgeSettings();
    /**右侧第一个图片配置*/
    RightCenterScaleImgSettings rightCenterScaleImgSettings=new RightCenterScaleImgSettings();


    /**右侧第一个图片配置*/
    RootlayoutSetting rootlayoutSetting =new RootlayoutSetting();


    CentLayoutConfig centLayoutConfig;
    /**
     * 默认为6 一行显示
     */
    private BgSetting bgSetting=new BgSetting();


    public  final  static  String  TYPE_GridLayoutManager="GridLayoutManager";
    public  final  static String  TYPE_RelativeLayout="RelativeLayout";
    public  final  static String  TYPE_LinearLayout="LinearLayout";
    private  String  parentLayoutType=TYPE_GridLayoutManager;

    int spanSize = 6;
    private IOSSwitchButton.OnStateChangeListener onRightCheckBoxListener;

    public String getParentLayoutType() {
        return parentLayoutType;
    }

    public void setParentLayoutType(String parentLayoutType) {
        this.parentLayoutType = parentLayoutType;
    }

    public String getId() {
        return id;
    }

    public Object getBindObject() {
        return bindObject;
    }

    public String getParentId() {
        return parentId;
    }

    @Override
    public String getSection() {
        return section;
    }

    public boolean isSectionShowDelete() {
        return isSectionShowDelete;
    }

    @Override
    public String getSelectType() {
        return selectType;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getHint() {
        return hint;
    }

    public boolean isTalk() {
        return isTalk;
    }

    public boolean isHintShow() {
        return hintShow;
    }

    public int getHintLeftMagin() {
        return hintLeftMagin;
    }

    @Override
    public boolean isShowLeftCheckBox() {
        return isShowLeftCheckBox;
    }

    public boolean isOnItemClickAble() {
        return isOnItemClickAble;
    }

    public boolean isOnItemAllClickAble() {
        return isOnItemAllClickAble;
    }

    @Override
    public boolean isLeftCheckBoxIsChecked() {
        return leftCheckBoxIsChecked;
    }

    public String getLeftSecondText() {
        return leftSecondText;
    }

    public int getLeftSecondImgResId() {
        return leftSecondImgResId;
    }

    public int getLeftSecondTextColor() {
        return leftSecondTextColor;
    }

    public int getLeftSecondTextbg() {
        return leftSecondTextbg;
    }

    public String getRightFirstText() {
        return rightFirstText;
    }

    public int getRightFirstTvColor() {
        return rightFirstTvColor;
    }

    public String getRightFirstButtonText() {
        return rightFirstButtonText;
    }

    public int getRightFirstButtonBgResId() {
        return rightFirstButtonBgResId;
    }

    public int getRightUnreadCount() {
        return rightUnreadCount;
    }

    public boolean isShowRightCheckbox() {
        return showRightCheckbox;
    }

    public int getContentLayoutMagin() {
        return contentLayoutMagin;
    }

    public int getPosition() {
        return position;
    }

    public boolean isRightCheckBoxSelect() {
        return isRightCheckBoxSelect;
    }

    public boolean isAddSideBar() {
        return isAddSideBar;
    }

    public boolean isShowCleanImg() {
        return showCleanImg;
    }

    public int getItemHight() {
        return itemHight;
    }

    public boolean isItemCanEdit() {
        return itemCanEdit;
    }

    public String getRightSecondText() {
        return rightSecondText;
    }

    public IItemView.onItemClick getOnItemListener() {
        return onItemListener;
    }

    public TitleSettings getTitleSettings() {
        return titleSettings;
    }

    public HintSettings getHintSettings() {
        return hintSettings;
    }

    public RightSecondImgSettings getRightSecondImgSettings() {
        return rightSecondImgSettings;
    }

    public AddressEditSettings getEidtSettings() {
        return eidtSettings;
    }

    public AddressHeadImgeSettings getHeadImgeSettings() {
        return headImgeSettings;
    }

    public AddressRightFistImgeSettings getRightFistImgeSettings() {
        return rightFistImgeSettings;
    }

    public RightCenterScaleImgSettings getRightCenterScaleImgSettings() {
        return rightCenterScaleImgSettings;
    }

    @Override
    public CentLayoutConfig getCentLayoutConfig() {
        return centLayoutConfig;
    }

    public BgSetting getBgSetting() {
        return bgSetting;
    }

    @Override
    public int getSpanSize() {
        return spanSize;
    }

    public IOSSwitchButton.OnStateChangeListener getOnRightCheckBoxListener() {
        return onRightCheckBoxListener;
    }

//==============以下为set
    public void setId(String id) {
        this.id = id;
    }

    public void setBindObject(Object bindObject) {
        this.bindObject = bindObject;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public void setSection(String section) {
        this.section = section;
    }

    public void setSectionShowDelete(boolean sectionShowDelete) {
        isSectionShowDelete = sectionShowDelete;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setTalk(boolean talk) {
        isTalk = talk;
    }

    public void setHintShow(boolean hintShow) {
        this.hintShow = hintShow;
    }

    public void setHintLeftMagin(int hintLeftMagin) {
        this.hintLeftMagin = hintLeftMagin;
    }

    @Override
    public void setShowLeftCheckBox(boolean showLeftCheckBox) {
        isShowLeftCheckBox = showLeftCheckBox;
    }

    public void setOnItemClickAble(boolean onItemClickAble) {
        isOnItemClickAble = onItemClickAble;
    }

    public void setOnItemAllClickAble(boolean onItemAllClickAble) {
        isOnItemAllClickAble = onItemAllClickAble;
    }

    @Override
    public void setLeftCheckBoxIsChecked(boolean leftCheckBoxIsChecked) {
        this.leftCheckBoxIsChecked = leftCheckBoxIsChecked;
    }

    public void setLeftSecondText(String leftSecondText) {
        this.leftSecondText = leftSecondText;
    }

    public void setLeftSecondImgResId(int leftSecondImgResId) {
        this.leftSecondImgResId = leftSecondImgResId;
    }

    public void setLeftSecondTextColor(int leftSecondTextColor) {
        this.leftSecondTextColor = leftSecondTextColor;
    }

    public void setLeftSecondTextbg(int leftSecondTextbg) {
        this.leftSecondTextbg = leftSecondTextbg;
    }

    public void setRightFirstText(String rightFirstText) {
        this.rightFirstText = rightFirstText;
    }

    public void setRightFirstTvColor(int rightFirstTvColor) {
        this.rightFirstTvColor = rightFirstTvColor;
    }

    public void setRightFirstButtonText(String rightFirstButtonText) {
        this.rightFirstButtonText = rightFirstButtonText;
    }

    public void setRightFirstButtonBgResId(int rightFirstButtonBgResId) {
        this.rightFirstButtonBgResId = rightFirstButtonBgResId;
    }

    public void setRightUnreadCount(int rightUnreadCount) {
        this.rightUnreadCount = rightUnreadCount;
    }

    public RootlayoutSetting getRootlayoutSetting() {
        return rootlayoutSetting;
    }

    public void setRootlayoutSetting(RootlayoutSetting rootlayoutSetting) {
        this.rootlayoutSetting = rootlayoutSetting;
    }

    public void setShowRightCheckbox(boolean showRightCheckbox) {
        this.showRightCheckbox = showRightCheckbox;
    }

    public void setContentLayoutMagin(int contentLayoutMagin) {
        this.contentLayoutMagin = contentLayoutMagin;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    public void setRightCheckBoxSelect(boolean rightCheckBoxSelect) {
        isRightCheckBoxSelect = rightCheckBoxSelect;
    }

    public void setAddSideBar(boolean addSideBar) {
        isAddSideBar = addSideBar;
    }

    public void setShowCleanImg(boolean showCleanImg) {
        this.showCleanImg = showCleanImg;
    }

    public void setItemHight(int itemHight) {
        this.itemHight = itemHight;
    }

    public void setItemCanEdit(boolean itemCanEdit) {
        this.itemCanEdit = itemCanEdit;
    }

    public void setRightSecondText(String rightSecondText) {
        this.rightSecondText = rightSecondText;
    }

    public void setOnItemListener(IItemView.onItemClick onItemListener) {
        this.onItemListener = onItemListener;
    }

    public void setTitleSettings(TitleSettings titleSettings) {
        this.titleSettings = titleSettings;
    }

    public void setHintSettings(HintSettings hintSettings) {
        this.hintSettings = hintSettings;
    }

    public void setRightSecondImgSettings(RightSecondImgSettings rightSecondImgSettings) {
        this.rightSecondImgSettings = rightSecondImgSettings;
    }

    public void setEidtSettings(AddressEditSettings eidtSettings) {
        this.eidtSettings = eidtSettings;
    }

    public void setHeadImgeSettings(AddressHeadImgeSettings headImgeSettings) {
        this.headImgeSettings = headImgeSettings;
    }

    public void setRightFistImgeSettings(AddressRightFistImgeSettings rightFistImgeSettings) {
        this.rightFistImgeSettings = rightFistImgeSettings;
    }

    public void setRightCenterScaleImgSettings(RightCenterScaleImgSettings rightCenterScaleImgSettings) {
        this.rightCenterScaleImgSettings = rightCenterScaleImgSettings;
    }

    public void setCentLayoutConfig(CentLayoutConfig centLayoutConfig) {
        this.centLayoutConfig = centLayoutConfig;
    }

    public void setBgSetting(BgSetting bgSetting) {
        this.bgSetting = bgSetting;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setOnRightCheckBoxListener(IOSSwitchButton.OnStateChangeListener onRightCheckBoxListener) {
        this.onRightCheckBoxListener = onRightCheckBoxListener;
    }
}
