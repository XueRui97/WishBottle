//收藏类
package com.wishbottle.wishbottle.bean;


import javax.persistence.*;
import java.util.Date;
import java.sql.DatabaseMetaData;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Collection {
<<<<<<< HEAD
   @Id
   @GeneratedValue
   private Integer collectionID;
=======
    @Id
    @GeneratedValue
    private Integer collectionID;
>>>>>>> 44f9bf93130679985b251066df6593efe50824a3
    @Column(nullable = false)
    private Date CLTime;//收藏时间
    @ManyToOne
    private AccountInfo accountInfo;
    @ManyToOne
    private Wish wish;


    public Integer getCollectionID() {
        return collectionID;
    }

<<<<<<< HEAD
   public AccountInfo getAccountInfo() {
=======
    public AccountInfo getAccountInfo() {
>>>>>>> 44f9bf93130679985b251066df6593efe50824a3
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
