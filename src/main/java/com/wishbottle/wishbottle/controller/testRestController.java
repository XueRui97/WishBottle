package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.bean.Log;
import com.wishbottle.wishbottle.service.AccountInfoService;
import com.wishbottle.wishbottle.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class testRestController {
    @Autowired
    AccountInfoService accountInfoService;
    @Autowired
    LogService logService;
    static  AccountInfo presentAccount=new AccountInfo();
    @PostMapping("/weChatLogin")
    public boolean addStudent(@RequestBody AccountInfo accountInfo)
    {
        System.out.println("yesyesyes");
        System.out.println(accountInfo.getPassword());
        System.out.println(accountInfo.getEmail());
        List<AccountInfo> accountInfoList=accountInfoService.queryByEmailOrName(accountInfo.getEmail());
        System.out.println("yesyesyes");
        if(accountInfoList.isEmpty()){
            System.out.println("无该用户");
            System.out.println("yesyesyes");
            return  false;
        }
        else{
            //登录成功
            if(accountInfoList.get(0).getPassword().equals(accountInfo.getPassword())){
                presentAccount=accountInfoList.get(0);
                System.out.println(accountInfo.getPassword());
                System.out.println("yesyesyes");
                return true;
            }
            else {
                System.out.println("账号与密码不匹配！");
                System.out.println("yesyesyes");
                return  false;
            }
        }
    }
    @GetMapping("/weChataddLog/{str}")//ip:location
    public boolean weChataddLog(@PathVariable("str") String  str){
            System.out.println(str);
            String[] re=str.split(":");
            Log log=new Log(presentAccount,re[0],new Date(),re[1]);
            System.out.println(re[0]+"    "+re[1]);
            //添加日志
            // Date date=new Date();
            //Log alog=new Log(presentAccount,ipStr,date,addressStr);
            logService.save(log);
            return  false;
    }
    @PostMapping("/weChatSignup")
    public Integer signup(@RequestBody AccountInfo accountInfo){
        System.out.println("nonononono");
        System.out.println(accountInfo.getNikeName());
        System.out.println(accountInfo.getEmail());
        System.out.println(accountInfo.getPassword());
        System.out.println("nonononono");
        List<AccountInfo> accountInfoList1=accountInfoService.queryByEmailOrName(accountInfo.getEmail());
        List<AccountInfo> accountInfoList2=accountInfoService.queryByEmailOrName(accountInfo.getNikeName());
        if(accountInfoList1.isEmpty()&&accountInfoList2.isEmpty()){
            AccountInfo account = new AccountInfo(accountInfo.getNikeName(),accountInfo.getEmail(), accountInfo.getPassword());
            accountInfoService.addAccountInfo(account);
            presentAccount=account;
            System.out.println("yes");
            return 0;
        }
        else if(accountInfoList1.isEmpty())
            return 1;
        else return 2;
    }

}
