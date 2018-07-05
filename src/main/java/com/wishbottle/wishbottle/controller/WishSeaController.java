package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.bean.WishToComments;
import com.wishbottle.wishbottle.service.CommentsService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            WishToComments aWishToComment=//初始化，不能为空null
                    list.isEmpty()?new WishToComments():
                            new WishToComments(list.get(0).getWishID(),commentsService.search(list.get(0).getWishID()));
            if(!list.isEmpty())
                for(Wish wish:list)
                    aWishToComment.wishToCommentsList.add(
                            new WishToComments(wish.getWishID(),commentsService.search(wish.getWishID())));
            model.addAttribute("aWishToComment",aWishToComment);
            model.addAttribute("wishes", list);
            model.addAttribute("searchString", searchString);
            model.addAttribute("presentAccount", AccountInfoController.presentAccount);
            return "wishSeaPage";
        } else
            return "loginPage";
    }
}
