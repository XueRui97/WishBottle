//心愿类
package com.wishbottle.wishbottle.bean;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Wish {
    @Id
    @GeneratedValue
    private Integer WishID;//心愿ID，主键
    @ManyToOne
    private  AccountInfo accountInfo;//发布心愿的用户的ID，外键——用户管理
    @Column(nullable = false,length =80)
    private String Title;//心愿标题
    @Column(nullable = false,length =240)
    private String Content;//心愿内容
    @Column(nullable = false)
    private boolean Permision=true;//心愿权限（仅自己可见:false,所有人可见：true)
    private int BrowseNum;//浏览量
    private int GoodNum;//点赞量
    private Date RelTime;//发布时间
//get,set
    public Integer getWishID() {
        return WishID;
    }

    public void setWishID(Integer wishID) {
        WishID = wishID;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public boolean isPermision() {
        return Permision;
    }

    public String showPermision() {
        if (Permision)
            return "所有人";
        else
            return "仅自己";
    }

    public void setPermision(boolean permision) {
        Permision = permision;
    }

    public int getBrowseNum() {
        return BrowseNum;
    }

    public void setBrowseNum(int browseNum) {
        BrowseNum = browseNum;
    }

    public int getGoodNum() {
        return GoodNum;
    }

    public void setGoodNum(int goodNum) {
        GoodNum = goodNum;
    }

    public Date getRelTime() {
        return RelTime;
    }

    public void setRelTime(Date relTime) {
        RelTime = relTime;
    }

    public Wish(AccountInfo accountInfo, String title, String content, boolean permision) {
        this.accountInfo = accountInfo;
        Title = title;
        Content = content;
        Permision = permision;
        BrowseNum = 0;
        GoodNum = 0;
        RelTime = new Date();
    }
    public Wish() {
    }
}
