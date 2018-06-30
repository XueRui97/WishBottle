//个人主页控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Collection;
import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.bean.Wish;
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
            /*
            //我的心愿
            List<Wish> wishList=wishService.getByAccountID(AccountInfoController.presentAccount.getAccountID());
            model.addAttribute("myWish",wishList);
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
            return "treePage";*/
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
            //model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            //return "treePage";
            return returnTree(model);
        }
        else
            return "loginPage";

    }
    private String returnTree(Model model){
        //我的心愿
        List<Wish> wishList=wishService.getByAccountID(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("myWish",wishList);
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
