//用户信息
package com.wishbottle.wishbottle.bean;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class AccountInfo {
    @Id
    @GeneratedValue
    private Integer AccountID;//账号ID
    @Column(unique = true,nullable=false,length = 32)//唯一值
    private String NikeName;//昵称
    @Column(unique = true,nullable=false,length = 20)
    private String Email;//邮箱
    @Column(length = 20,nullable=true)
    private String Password;//密码
    @Column(length = 100)
    private String SelfIntro;//自我介绍
    @Column(length = 32,nullable=false)
    private String Avatar;//头像照片路径\src\main\resources\static\assets\img
    private Date RegestTime;//注册时间

    public Integer getAccountID() {
        return AccountID;
    }

    public void setAccountID(Integer accountID) {
        AccountID = accountID;
    }

    public String getNikeName() {
        return NikeName;
    }

    public void setNikeName(String nikeName) {
        NikeName = nikeName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSelfIntro() {
        return SelfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        SelfIntro = selfIntro;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public Date getRegestTime() {
        return RegestTime;
    }

    public void setRegestTime(Date regestTime) {
        RegestTime = regestTime;
    }
}
