//个人主页控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.*;
import com.wishbottle.wishbottle.service.AccountInfoService;
import com.wishbottle.wishbottle.service.CollectionService;
import com.wishbottle.wishbottle.service.CommentsService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class PersonController {
    private Integer wishNum,goodNum,commentNum;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private WishService wishService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CollectionService collectionService;
    //树洞
    @GetMapping("/tree")
    public String tree(Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            return returnTree(model);
        }
        else
            return "loginPage";
    }
    //发布心愿
    @PostMapping("/addWish")
    public String addWish(@RequestParam("wishTitle") String wishTitle,
                          @RequestParam("wishContent") String wishContent,
                          @RequestParam("visibility") String visibility,
                          Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            boolean  Permision=visibility.equals("all");
            Wish awish=new Wish(AccountInfoController.presentAccount,wishTitle,wishContent,Permision);
            wishService.addWish(awish);
            return returnTree(model);
        }
        else
            return "loginPage";

    }
    //修改用户信息
    @PostMapping("/editPost")
    public String updateAccountInfo(@RequestParam("userNameEdit") String name,
                                    @RequestParam("emailEdit") String email,
                                    @RequestParam("birthdayEdit") Date birthday,
                                    @RequestParam("introEdit") String introEdit,
                                    Model model) {
        if(AccountInfoController.presentAccount.getEmail()!=null){
            List<AccountInfo> accountInfoList1=accountInfoService.queryByEmailOrName(name);
            List<AccountInfo> accountInfoList2=accountInfoService.queryByEmailOrName(email);
            if((accountInfoList1.size()==0&&accountInfoList2.size()==0)||//name,email都是不在数据库的
                (accountInfoList1.size()==0&&accountInfoList2.size()==1
                        &&email.equals(AccountInfoController.presentAccount.getEmail()))||//name不再数据库中,email为原来的
                accountInfoList1.size()==1&&accountInfoList2.size()==0
                        &&name.equals(AccountInfoController.presentAccount.getNikeName())||//email不再数据库中,name为原来的
                accountInfoList1.size()==1&&accountInfoList2.size()==1
                        &&name.equals(AccountInfoController.presentAccount.getNikeName())
                        &&email.equals(AccountInfoController.presentAccount.getEmail())) //name，email都为原来的
            {
                AccountInfoController.presentAccount.setBirthday(birthday);
                AccountInfoController.presentAccount.setSelfIntro(introEdit);
                AccountInfoController.presentAccount.setNikeName(name);
                AccountInfoController.presentAccount.setEmail(email);
            }
                accountInfoService.updateAccountInfo(AccountInfoController.presentAccount);
               // System.out.println(AccountInfoController.presentAccount.getAccountID() + "  " + accountInfo.getAccountID());
                return returnTree(model);
        }
        else
            return "loginPage";
    }
    //返回心愿、评论、被评论和收藏的list方法
    private String returnTree(Model model){
        //我的心愿
        List<Wish> wishList=wishService.getByAccountID(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("myWish",wishList);
        //点赞数
        int goodNum=0;
        for(Wish awish:wishList){
            goodNum+=awish.getGoodNum();
        }
        model.addAttribute("goodNum",goodNum);
        //我的评论
        List<Comments> myList=commentsService.queryByAccountID(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("myComments",myList);
        //对我的评论
        List<Comments> otherList=commentsService.queryOtherComment(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("otherComments",otherList);
        model.addAttribute("presentAccount",AccountInfoController.presentAccount);
        //我的收藏
        List<Collection> myCollection=collectionService.queryMyCollection(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("myCollection",myCollection);
        return "treePage";
    }
}
