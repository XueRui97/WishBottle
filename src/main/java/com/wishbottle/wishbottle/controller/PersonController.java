//个人主页控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.repository.WishRepository;
import com.wishbottle.wishbottle.service.AccountInfoService;
import com.wishbottle.wishbottle.service.CommentsService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.stream.events.Comment;
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

    //树洞
    @GetMapping("/tree")
    public String tree(Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            //我的心愿
            List<Wish> wishList=wishService.getByAccountID(AccountInfoController.presentAccount.getAccountID());
            model.addAttribute("myWish",wishList);
            /*//test
            for(Wish awish:wishList) {
                System.out.println(awish.getWishID());
                System.out.println(awish.getTitle());
                System.out.println(awish.getRelTime());
                System.out.println();
            }*/
            //我的评论
            List<Comments> myList=commentsService.queryByAccountID(AccountInfoController.presentAccount.getAccountID());
            model.addAttribute("myComments",myList);
            /*//test
            for(Comments acomment:myList) {
                System.out.println(acomment.getWish().getTitle());
                System.out.println(acomment.getAccountInfo().getNikeName());
                System.out.println(acomment.getCMContent());
                System.out.println();
            }*/
            //对我的评论
            List<Comments> otherList=commentsService.queryOtherComment(AccountInfoController.presentAccount.getAccountID());
            model.addAttribute("otherComments",otherList);
            /*//test
            for(Comments acomment:otherList) {
                System.out.println(acomment.getWish().getTitle());
                System.out.println(acomment.getAccountInfo().getNikeName());
                System.out.println(acomment.getCMContent());
                System.out.println();
            }*/
            model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            return "treePage";
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
            System.out.println(wishTitle+"\n"+wishContent+"\n"+visibility);
            model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            return "treePage";
        }
        else
            return "loginPage";

    }

}
