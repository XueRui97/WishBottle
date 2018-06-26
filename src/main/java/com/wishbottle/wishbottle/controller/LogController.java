//日志控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Log;
import com.wishbottle.wishbottle.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/")
public class LogController {
    @Autowired
    private LogService logService;
    @GetMapping("/logPage")
    public String log(Model model){
        List<Log> list=logService.getAllLog();
        model.addAttribute("logs",list);
        return "logPage";
    }
}
