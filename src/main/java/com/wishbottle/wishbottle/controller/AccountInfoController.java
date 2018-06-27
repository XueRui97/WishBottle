//账号控制类
package com.wishbottle.wishbottle.controller;


import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.bean.Log;
import com.wishbottle.wishbottle.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;
    @GetMapping()//初始页面——数据总览页面
    public String  first(){
        return "index";
    }
    @GetMapping("/index")//跳转到数据总览页面
    public String index(){
        return "index";
    }

    @GetMapping("/accountPage")//跳转到用户管理页面
    public String account(Model model){
        List<AccountInfo> list=accountInfoService.getAllAccountInfo();
        model.addAttribute("account",list);
        return "accountPage";
    }

}
