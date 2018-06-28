//评论控制类
package com.wishbottle.wishbottle.controller;
import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    String searchString="Search...";
    @GetMapping("/commentPage")//跳转到评论管理页面
    public String comment(Model model){
        searchString="Search...";
        List<Comments> list=commentsService.getAllComments();
        model.addAttribute("comments",list);
        model.addAttribute("searchString",searchString);
        return "commentPage";
    }
    @GetMapping("/deleteComment/{CommentsID}")//删除功能
    public String deletAccount(@PathVariable("CommentsID") Integer id, Model model){
        searchString="Search...";
        Optional<Comments> comments = commentsService.findByID(id);
        commentsService.deleteComment(comments.get());
        List<Comments> list=commentsService.getAllComments();
        model.addAttribute("comments",list);
        model.addAttribute("searchString",searchString);
        return "redirect:/commentPage";
    }
    //查询
    @PostMapping("/searchComment")
    public String searchWish(@RequestParam("searchBox") String searchBox, Model model){
        List<Comments> commentsList=null;
        if (!searchBox.isEmpty())
            this.searchString=searchBox;
        Pattern pattern = Pattern.compile("[0-9]*");
        //根据WishID进行查询
        if(pattern.matcher(searchString).matches()){
            Integer in=Integer.valueOf(searchString);
            commentsList = commentsService.search(in);
            //System.out.println(in);
        }
        else {
           // System.out.println(searchBox);
            commentsList = commentsService.search("%" + this.searchString + "%");
            //System.out.println(commentsList.size());
        }
        model.addAttribute("comments",commentsList);
        model.addAttribute("searchString",searchString);
        return "commentPage";
    }
}