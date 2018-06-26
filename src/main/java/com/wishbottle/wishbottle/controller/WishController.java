//心愿控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import java.util.List;
@Controller
@RequestMapping("/")
public class WishController {
    @Autowired
private WishService logService;
    @GetMapping("/wishPage")
    public String log(Model model){
        List<Wish> list=logService.getAllWish();
        model.addAttribute("wishes",list);
        return "wishPage";
    }
}
