//收藏类
package com.wishbottle.wishbottle.bean;


import javax.persistence.*;
import java.util.Date;
import java.sql.DatabaseMetaData;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Collection {
    @Id
    @GeneratedValue
    private Integer collectionID;
    @Column(nullable = false)
    private Date CLTime;//收藏时间ss
    @ManyToOne
    private AccountInfo accountInfo;
    @ManyToOne
    private Wish wish;


    public Integer getCollectionID() {
        return collectionID;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public Wish getWish() {
        return wish;
    }

    public Date getCLTime() {
        return CLTime;
    }

    public void setCollectionID(Integer collectionID) {
        this.collectionID = collectionID;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }

    public void setCLTime(Date CLTime) {
        this.CLTime = CLTime;
    }
}
