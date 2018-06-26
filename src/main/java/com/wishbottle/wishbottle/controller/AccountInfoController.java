//账号控制类
package com.wishbottle.wishbottle.controller;


import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class AccountInfoController {
    @Autowired
    private AccountInfoService AccountInfoService;
    @GetMapping("accountPage")
    public String account(Model model){
        List<AccountInfo> list=AccountInfoService.getAllAccountInfo();
        model.addAttribute("account",list);
        return "accountPage";
    }
    /*
    public String  first(){
        //add
       /* AccountInfo accountInfo=new AccountInfo();
        accountInfo.setNikeName("13");
        accountInfo.setAvatar("1asdas");
        accountInfo.setEmail("1231@qq.com");
        accountInfo.setPassword("123qweqwe");
        accountInfo.setSelfIntro("1231aweqweqwe");
        Date d = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        accountInfo.setRegestTime(d);
        accountInfoService.addAccountInfo(accountInfo);
    */
        //find
        /*List<AccountInfo> accountInfoList=accountInfoService.queryByAccountName("12","12");
        if(accountInfoList.isEmpty()){
            System.out.print("查不到");
        }
        else {
            AccountInfo accountInfo1 = accountInfoList.get(0);
            System.out.print(accountInfo1.getAccountID());
        }
        return "index";
    }*/
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    //@GetMapping("/accountPage")
    public String account(){
        return "accountPage";
    }
   // @GetMapping("/wishPage")
   // public String wish(){
    //    return "wishPage";
   // }
    //@GetMapping("/commentPage")
    //public String comment(){
    //    return "commentPage";
    //}
   // @GetMapping("/collectPage")
    public String collect(){
        return "collectPage";
    }

}
