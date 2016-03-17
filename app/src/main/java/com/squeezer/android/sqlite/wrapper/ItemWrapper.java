package com.squeezer.android.sqlite.wrapper;

/**
 * Created by adnen on 3/17/16.
 */
public class ItemWrapper {

    private int mId;
    private int mIdCategory;
    private String mTitle;
    private String mInfo;

    public ItemWrapper() {

    }

    public ItemWrapper(int idCategory, String title, String info) {
        mIdCategory = idCategory;
        mTitle = title;
        mInfo = info;
    }

    public ItemWrapper(int id, int idCategory, String title, String info) {
        mIdCategory = idCategory;
        mTitle = title;
        mInfo = info;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getIdCategory() {
        return mIdCategory;
    }

    public void setIdCategory(int idCategory) {
        this.mIdCategory = idCategory;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        this.mInfo = info;
    }

    public void setItemWrapper(ItemWrapper itemWrapper) {
        mId = itemWrapper.getId();
        mIdCategory = itemWrapper.getIdCategory();
        mTitle = itemWrapper.getTitle();
        mInfo = itemWrapper.getInfo();
    }

}
