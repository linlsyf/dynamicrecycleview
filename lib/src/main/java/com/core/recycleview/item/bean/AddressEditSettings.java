package com.core.recycleview.item.bean;

import android.text.InputType;

import java.io.Serializable;

/**
 * Created by ldh on 2017/4/24.
 */

public class AddressEditSettings implements Serializable{
    /**右侧输入框是否显示 */
    private  boolean showEdittext=false;
    /**右侧输入框是否可编辑 */
    private  boolean edittextCanEdit=true;
    /**右侧输入框内容 */
    private  String  editContent="";
    /**是否弹出输入框 */
    private  boolean  openKeybord=false;

    /**右侧输入框提示内容 */
    private  String  editHint="";

    /**编辑内容类型 */
    private  int inputType= InputType.TYPE_CLASS_TEXT;


    public boolean isShowEdittext() {
        return showEdittext;
    }

    public void setShowEdittext(boolean showEdittext) {
        this.showEdittext = showEdittext;
    }

    public boolean isEdittextCanEdit() {
        return edittextCanEdit;
    }

    public void setEdittextCanEdit(boolean edittextCanEdit) {
        this.edittextCanEdit = edittextCanEdit;
    }
    public boolean isOpenKeybord() {
        return openKeybord;
    }

    public void setOpenKeybord(boolean openKeybord) {
        this.openKeybord = openKeybord;
    }


    public String getEditContent() {
        return editContent;
    }

    public void setEditContent(String editContent) {
        this.editContent = editContent;
    }

    public String getEditHint() {
        return editHint;
    }

    public void setEditHint(String editHint) {
        this.editHint = editHint;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

}
