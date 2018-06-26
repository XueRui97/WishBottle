//登录
package com.wishbottle.wishbottle.bean;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.sql.DatabaseMetaData;
import java.util.Date;

@Entity
public class Log {
    @Id
    @GeneratedValue
    private Integer LogID;//日志ID
    //private Integer AccountID;//账号ID
    @ManyToOne
    private AccountInfo accountInfo;
    @Column(nullable = false,length = 30)
    private String IP;//客户端IP地址
    @Column(nullable = false)
    private Date LogTime;//登录时间
    @Column(nullable=false,length = 100)
    private String Address;//客户端地址

    public Integer getLogID() {
        return LogID;
    }

    public void setLogID(Integer logID) {
        LogID = logID;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Date getLogTime() {
        return LogTime;
    }

    public void setLogTime(Date logTime) {
        LogTime = logTime;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
