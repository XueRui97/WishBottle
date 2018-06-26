package com.wishbottle.wishbottle.bean;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
//@Entity
//@Embeddable
public class Key implements Serializable {
    /*@ManyToOne
    private AccountInfo accountInfo;
    @ManyToOne
    private Wish wish;

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }
    */
}
