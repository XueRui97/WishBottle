//登录日志控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Log;
import com.wishbottle.wishbottle.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class LogController {
    @Autowired
    private LogService logService;
    String searchString="Search...";
    @GetMapping("/logPage")//跳转到登录日志界面
    public String log(Model model){
        List<Log> list=logService.getAllLog();
        model.addAttribute("logs",list);
        model.addAttribute("searchString",searchString);
        return "logPage";
    }
    //查询
    @PostMapping("/searchLog")
    public String searchLog(@RequestParam("searchBox") String searchBox, Model model){
        List<Log> logList = null;
        if (!searchBox.isEmpty())
            this.searchString=searchBox;
        //根据AccountID进行查询
        Pattern pattern = Pattern.compile("[0-9]*");
        if(pattern.matcher(searchString).matches()){
            Integer in=Integer.valueOf(searchString);
            logList = logService.search(in);
            //System.out.println(in);
            }
        //根据NikeName进行查询
        else {
            //System.out.println(searchBox);
            logList = logService.search("%" + this.searchString + "%");
           // System.out.println(logList.size());
        }
        model.addAttribute("logs",logList);
        model.addAttribute("searchString",searchString);
        return "logPage";
    }
}
