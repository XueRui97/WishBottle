//收藏控制类
package com.wishbottle.wishbottle.controller;
import com.wishbottle.wishbottle.bean.Collection;
import com.wishbottle.wishbottle.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @GetMapping("/collectPage")//跳转到收藏管理页面
    public String collection(Model model){
        List<Collection> list=collectionService.getAllCollection();
        model.addAttribute("collections",list);
        return "collectPage";
    }
}
