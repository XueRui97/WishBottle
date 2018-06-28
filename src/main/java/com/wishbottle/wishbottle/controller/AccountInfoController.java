//账号控制类
package com.wishbottle.wishbottle.controller;


import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.bean.Log;
import com.wishbottle.wishbottle.service.AccountInfoService;
import com.wishbottle.wishbottle.service.CollectionService;
import com.wishbottle.wishbottle.service.CommentsService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;

    private String searchString="Search...";
    @GetMapping()//初始页面——登录
    public String  first(){
        return "loginPage";
    }

    @GetMapping("/login")//登录页面——登录
    public String  login(){
        return "loginPage";
    }

    @GetMapping("/index")//跳转到数据总览页面
    public String index(){
        return "index";
    }

    @GetMapping("/accountPage")//跳转到用户管理页面
    public String account(Model model){
        List<AccountInfo> list=accountInfoService.getAllAccountInfo();
        model.addAttribute("account",list);
        model.addAttribute("searchString",searchString);
        return "accountPage";
    }


    //查询
    @PostMapping("/searchAccount")
    public String searchAccount(@RequestParam("searchBox") String searchBox, Model model){
        if (!searchBox.isEmpty())
            this.searchString=searchBox;
        System.out.println(searchBox);
        List<AccountInfo> accountInfoList=accountInfoService.search("%"+ this.searchString+"%");
        System.out.println(accountInfoList.size());
        model.addAttribute("account",accountInfoList);
        model.addAttribute("searchString",searchString);
        return "accountPage";
    }

}
