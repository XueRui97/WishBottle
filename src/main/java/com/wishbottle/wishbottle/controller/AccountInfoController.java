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
    @GetMapping()
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
        }*/
        return "index";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/accountPage")
    public String account(Model model){
        List<AccountInfo> list=accountInfoService.getAllAccountInfo();
        model.addAttribute("account",list);
        return "accountPage";
    }
    //删除学生信息
   /* @GetMapping("/deleteAccount/{AccountID}")
    public String deletAccount(@PathVariable("AccountID") Integer id,Model model){
        Optional<AccountInfo> accountInfo = accountInfoService.findByID(id);
        accountInfoService.deleteAccountInfo(accountInfo.get());
        List<AccountInfo> list=accountInfoService.getAllAccountInfo();
        model.addAttribute("account",list);
        return "accountPage";
    }
*/
    /*@GetMapping("/wishPage")
    public String wish(){
        return "wishPage";
    }*/
    /*@GetMapping("/commentPage")
    public String comment(){
        return "commentPage";
    }*/
   /* @GetMapping("/collectPage")
    public String collect(){
        return "collectPage";
    }*/

}
