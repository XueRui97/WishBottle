//账号控制类
package com.wishbottle.wishbottle.controller;


import com.wishbottle.wishbottle.bean.AccountInfo;
import org.springframework.web.bind.annotation.GetMapping;

public class AccountInfoController {
    @GetMapping()
    public String  index(){
        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setNikeName("13");
        accountInfo.setAvatar("1asdas");
        accountInfo.setEmail("1231@qq.com");
        accountInfo.setPassword("123qweqwe");
        accountInfo.setSelfIntro("1231aweqweqwe");
        return "index";
    }


}
