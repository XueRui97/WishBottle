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
    static  AccountInfo presentAccount=new AccountInfo();
    //初始页面——登录
    @GetMapping()
    public String  first(){
        presentAccount=new AccountInfo();
        return "loginPage";
    }
    //登录页面——登录
    @GetMapping("/login")
    public String  login(){
        presentAccount=new AccountInfo();
        return "loginPage";
    }
    //树洞页面——树洞
    @GetMapping("/tree")
    public String  tree(){
        return "treePage";
    }
    //跳转到数据总览页面
    @GetMapping("/index")
    public String index(Model model){
        if(presentAccount.getEmail()!=null) {
            model.addAttribute("presentAccount",presentAccount);
            return "index";
        }
        else
            return "loginPage";
    }
    //跳转到用户管理页面
    @GetMapping("/accountPage")
    public String account(Model model){
        if(presentAccount.getEmail()!=null){
            List<AccountInfo> list=accountInfoService.getAllAccountInfo();
            model.addAttribute("account",list);
            model.addAttribute("searchString",searchString);
            model.addAttribute("presentAccount",presentAccount);
            return "accountPage";
        }
       else
           return "loginPage";
    }
    //查询
    @PostMapping("/searchAccount")
    public String searchAccount(@RequestParam("searchBox") String searchBox, Model model){
        if(presentAccount.getEmail()!=null){
            if (!searchBox.isEmpty())
                this.searchString=searchBox;
            //System.out.println(searchBox);
            List<AccountInfo> accountInfoList=accountInfoService.search("%"+ this.searchString+"%");
            //System.out.println(accountInfoList.size());
            model.addAttribute("account",accountInfoList);
            model.addAttribute("searchString",searchString);
            model.addAttribute("presentAccount",presentAccount);
            return "accountPage";
        }
        else
            return "loginPage";
    }
    //登录验证
    @PostMapping("/loginPost")
    public String login(@RequestParam("login-email") String EmailOrName,
                         @RequestParam("login-password") String PassWord,
                         Model model){
        List<AccountInfo> accountInfoList=accountInfoService.queryByEmailOrName(EmailOrName);
        if(accountInfoList.isEmpty()){
            System.out.println("无该用户");
            return "loginPage";
        }
        else{
            if(accountInfoList.get(0).getPassword().equals(PassWord)){
                System.out.println("welcom to computer");
                presentAccount=accountInfoList.get(0);
                model.addAttribute("presentAccount",presentAccount);
                return "treePage";
            }
            else {
                System.out.println("账号与密码不匹配！");
                return "loginPage";
            }
        }
    }
    //注册验证
    @PostMapping("/signupPost")
    public String  signup(@RequestParam("signup-username") String Name,
                         @RequestParam("signup-email") String Email,
                         @RequestParam("signup-password") String PassWord,
                         Model model){
        List<AccountInfo> accountInfoList1=accountInfoService.queryByEmailOrName(Name);
        List<AccountInfo> accountInfoList2=accountInfoService.queryByEmailOrName(Email);
        if(accountInfoList1.isEmpty()&&accountInfoList2.isEmpty()){
            AccountInfo account=new AccountInfo(Name,Email,PassWord);
            accountInfoService.addAccountInfo(account);
            presentAccount=account;
            model.addAttribute("presentAccount",presentAccount);
            return "treePage";
        }
        else{
            return  "loginPage";
        }
    }
}
