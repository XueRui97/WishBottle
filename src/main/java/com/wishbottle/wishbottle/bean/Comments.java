//评论类
package com.wishbottle.wishbottle.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comments {
    @Id
    @GeneratedValue
    private Integer CMID;//评论ID
    //private Integer WishID;//心愿ID
     @ManyToOne
     private Wish wish;
    //private Integer AccoundID;//评论者ID
     @ManyToOne
     private AccountInfo accountInfo;
    @Column(nullable = false,length = 240)
   private String CMContent;//评论内容
    @Column(nullable = false)
    private Date CMTime;//评论时间

    public Integer getCMID() {
        return CMID;
    }

    public void setCMID(Integer CMID) {
        this.CMID = CMID;
    }

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getCMContent() {
        return CMContent;
    }

    public void setCMContent(String CMContent) {
        this.CMContent = CMContent;
    }

    public Date getCMTime() {
        return CMTime;
    }

    public void setCMTime(Date CMTime) {
        this.CMTime = CMTime;
    }
}
