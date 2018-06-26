//心愿类
package com.wishbottle.wishbottle.bean;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Wish {
    @Id
    @GeneratedValue
    private Integer WishID;//心愿ID
    //private Integer AccoundID;
    @ManyToOne
    private  AccountInfo accountInfo;//账号ID
    @Column(nullable = false,length =80)
    private String Title;//心愿标题
    @Column(nullable = false,length =240)
    private String Content;//心愿内容
    @Column(nullable = false)
    private boolean Permision=true;//心愿权限（仅自己可见:false,公开：true)
    private int BrowseNum;//浏览量
    private int GoodNum;//点赞量
    private Date RelTime;//发布时间

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
}
