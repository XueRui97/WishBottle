//评论控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @GetMapping("/commentPage")
    public String comment(Model model){
        List<Comments> list=commentsService.getAllComments();
        model.addAttribute("comments",list);
        return "commentPage";
    }
}
