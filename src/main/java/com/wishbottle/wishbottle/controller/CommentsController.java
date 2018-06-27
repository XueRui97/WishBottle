//评论控制类
package com.wishbottle.wishbottle.controller;
import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @GetMapping("/commentPage")//跳转到评论管理页面
    public String comment(Model model){
        List<Comments> list=commentsService.getAllComments();
        model.addAttribute("comments",list);
        return "commentPage";
    }
    @GetMapping("/deleteComment/{CommentsID}")//删除功能
    public String deletAccount(@PathVariable("CommentsID") Integer id, Model model){
       Optional<Comments> comments = commentsService.findByID(id);
       commentsService.deleteComment(comments.get());
        List<Comments> list=commentsService.getAllComments();
        model.addAttribute("comments",list);
        return "redirect:/commentPage";
    }
}