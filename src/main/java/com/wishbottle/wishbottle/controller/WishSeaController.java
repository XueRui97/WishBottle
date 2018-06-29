package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Wish;
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
    String searchString="Search...";
    //心愿海
    @GetMapping("/wishSea")
    public String  wishSea(Model model) {
        if (AccountInfoController.presentAccount.getEmail() != null) {
            searchString = "Search...";
            List<Wish> list = wishService.getByPermision(true);
            model.addAttribute("wishes", list);
            model.addAttribute("searchString", searchString);
            model.addAttribute("presentAccount", AccountInfoController.presentAccount);
            return "wishSeaPage";
        } else
            return "loginPage";
    }
}
