//心愿控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Collection;
import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.service.CollectionService;
import com.wishbottle.wishbottle.service.CommentsService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import java.util.Optional;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class WishController {
    @Autowired
    private WishService wishService;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CommentsService commentsService;
    String searchString="Search...";
    //跳转到心愿管理页面
    @GetMapping("/wishPage")
    public String log(Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null) {
            searchString="Search...";
            List<Wish> list=wishService.getAllWish();
            model.addAttribute("wishes",list);
            model.addAttribute("searchString",searchString);
            model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            return "wishPage";
        }
        else
            return "loginPage";
    }
    //删除功能
    @GetMapping("/deleteWish/{WishID}")
    public String deletWish(@PathVariable("WishID") Integer id, Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            searchString="Search...";
            Optional<Wish> wishs =wishService.findByID(id);

            //delete collection
            List<Collection> collectionList=collectionService.getAllCollection();
            for(Collection acollection :collectionList)
                if(acollection.getWish().getWishID()==id)
                    collectionService.deleteCollection(acollection);

            //delete comment
            List<Comments> commentsList=commentsService.getAllComments();
            for(Comments acomment:commentsList)
                if(acomment.getWish().getWishID()==id)
                    commentsService.deleteComment(acomment);

            wishService.deleteWish(wishs.get());
            List<Wish> list=wishService.getAllWish();
            model.addAttribute("wishes",list);
            model.addAttribute("searchString",searchString);
            model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            return "redirect:/wishPage";
        }

        else
            return "loginPage";
    }
    //查询
    @PostMapping("/searchWish")
    public String searchWish(@RequestParam("searchBox") String searchBox, Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null) {
            List<Wish> wishList=null;
            if (!searchBox.isEmpty())
                this.searchString=searchBox;
            Pattern pattern = Pattern.compile("[0-9]*");
            //根据WishID进行查询
            if(pattern.matcher(searchString).matches()){
                Integer in=Integer.valueOf(searchString);
                wishList = wishService.search(in);
            }
            //根据NikeName、Title、Content进行查询
            else {
                wishList = wishService.search("%" + this.searchString + "%");
            }
            model.addAttribute("wishes",wishList);
            model.addAttribute("searchString",searchString);
            model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            return "wishPage";
        }
        else
            return "loginPage";
    }
}