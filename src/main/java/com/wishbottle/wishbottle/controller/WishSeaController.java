package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.bean.WishToComments;
import com.wishbottle.wishbottle.service.CommentsService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class WishSeaController {
    @Autowired
    private WishService wishService;
    @Autowired
    private CommentsService commentsService;
    String searchString="Search...";
    //心愿海
    @GetMapping("/wishSea")
    public String  wishSea(Model model) {
        if (AccountInfoController.presentAccount.getEmail() != null&&AccountInfoController.presentAccount.getLevel()==3) {
            searchString = "Search...";
            //公开的心愿
            List<Wish> list = wishService.getByPermision(true);

            Collections.reverse(list); // 倒序排列

            WishToComments aWishToComment=//初始化，不能为空null
                    list.isEmpty()?new WishToComments():
                            new WishToComments(list.get(0).getWishID(),commentsService.search(list.get(0).getWishID()));
            if(!list.isEmpty())
                for(Wish wish:list)
                    aWishToComment.wishToCommentsList.add(
                            new WishToComments(wish.getWishID(),commentsService.search(wish.getWishID())));
            List<Wish> top10=wishService.getTop10();//今日点赞量前十的心愿
            List<Wish> ranWish=wishService.getRan10();//随机产生的十个心愿
            model.addAttribute("top10Wishes",top10);
            model.addAttribute("randomWishes", ranWish);
            model.addAttribute("aWishToComment",aWishToComment);
            model.addAttribute("wishes", list);
            model.addAttribute("searchString", searchString);
            model.addAttribute("presentAccount", AccountInfoController.presentAccount);
            return "wishSeaPage";
        } else
            return "loginPage";
    }
    //心愿海发表评论
    @PostMapping("/addWishSeaComment")
    public String adddComment(@RequestParam("cmEdit") String cmEdit,
                              @RequestParam("wishID") Integer wishID,
                              Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            Wish awish=wishService.findByID(wishID).get();
            Comments acomment=new Comments(awish,AccountInfoController.presentAccount,cmEdit);
            commentsService.addComment(acomment);//保存评论
            awish.setCommentNum(awish.getCommentNum()+1);//wish的评论数加1
            wishService.updateWish(awish);
            return "redirect:/wishSea";
        }
        else
            return  "redirect:/login";
    }
}
