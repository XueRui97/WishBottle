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

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/wishPage")//跳转到心愿管理页面
    public String log(Model model){
        searchString="Search...";
        List<Wish> list=wishService.getAllWish();
        model.addAttribute("wishes",list);
        model.addAttribute("searchString",searchString);
        return "wishPage";
    }
    @GetMapping("/deleteWish/{WishID}")//删除功能
    public String deletWish(@PathVariable("WishID") Integer id, Model model){
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
        return "redirect:/wishPage";
    }
    //查询
    @PostMapping("/searchWish")
    public String searchWish(@RequestParam("searchBox") String searchBox, Model model){
        if (!searchBox.isEmpty())
            this.searchString=searchBox;
        System.out.println(searchBox);
        List<Wish> wishList=wishService.search("%"+ this.searchString+"%");
        System.out.println(wishList.size());
        model.addAttribute("wishes",wishList);
        model.addAttribute("searchString",searchString);
        return "wishPage";
    }
}