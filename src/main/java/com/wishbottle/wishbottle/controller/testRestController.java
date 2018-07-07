package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.bean.Collection;
import com.wishbottle.wishbottle.bean.Log;
import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.service.AccountInfoService;
import com.wishbottle.wishbottle.service.CollectionService;
import com.wishbottle.wishbottle.service.LogService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class testRestController {
    @Autowired
    AccountInfoService accountInfoService;
    @Autowired
    LogService logService;
    @Autowired
    WishService wishService;
    @Autowired
    CollectionService collectionService;
    static  AccountInfo presentAccount=new AccountInfo();
    //登录验证
    @PostMapping("/weChatLogin")
    public boolean addStudent(@RequestBody AccountInfo accountInfo)
    {
        System.out.println("yesyesyes");
        System.out.println(accountInfo.getPassword());
        System.out.println(accountInfo.getEmail());
        List<AccountInfo> accountInfoList=accountInfoService.queryByEmailOrName(accountInfo.getEmail());
        System.out.println("yesyesyes");
        if(accountInfoList.isEmpty()){
            System.out.println("无该用户");
            System.out.println("yesyesyes");
            return  false;
        }
        else{
            //登录成功
            if(accountInfoList.get(0).getPassword().equals(accountInfo.getPassword())){
                presentAccount=accountInfoList.get(0);
                System.out.println(accountInfo.getPassword());
                System.out.println("yesyesyes");
                return true;
            }
            else {
                System.out.println("账号与密码不匹配！");
                System.out.println("yesyesyes");
                return  false;
            }
        }
    }
    //日志记录
    @GetMapping("/weChataddLog/{str}")//ip:location
    public boolean weChataddLog(@PathVariable("str") String  str){
            System.out.println(str);
            String[] re=str.split(":");
            Log log=new Log(presentAccount,re[0],new Date(),re[1]);
           //添加日志v
            // Date date=new Date();
            //Log alog=new Log(presentAccount,ipStr,date,addressStr);
            logService.save(log);
            return  false;
    }
    //注册账号
    @PostMapping("/weChatSignup")
    public Integer signup(@RequestBody AccountInfo accountInfo){
        System.out.println("nonononono");
        System.out.println(accountInfo.getNikeName());
        System.out.println(accountInfo.getEmail());
        System.out.println(accountInfo.getPassword());
        System.out.println("nonononono");
        List<AccountInfo> accountInfoList1=accountInfoService.queryByEmailOrName(accountInfo.getEmail());
        List<AccountInfo> accountInfoList2=accountInfoService.queryByEmailOrName(accountInfo.getNikeName());
        //注册成功，返回0
        if(accountInfoList1.isEmpty()&&accountInfoList2.isEmpty()){
            AccountInfo account = new AccountInfo(accountInfo.getNikeName(),accountInfo.getEmail(), accountInfo.getPassword());
            accountInfoService.addAccountInfo(account);
            presentAccount=account;
            System.out.println("yes");
            return 0;
        }
        //email已经注册过,昵称没有注册过，返回1
        else if(accountInfoList1.isEmpty()&&!accountInfoList2.isEmpty())
            return 1;
        //昵称已经注册过,email没有注册过，
        else if(!accountInfoList1.isEmpty()&&accountInfoList2.isEmpty())
            return 2;
        //昵称和email都注册过
        else return 3;
    }
    //获取登录用户的信息
    @GetMapping("/AUser")
    public  Map<String,Object> auser(){
        System.out.println("present");
        Map<String,Object> map=new HashMap<String, Object>();
        List<AccountInfo> accountInfoList=new ArrayList<>();
        accountInfoList.add(presentAccount);
        map.put("userList",accountInfoList);
        return map;
    }
   //获取个人心愿
    @GetMapping("/reWish")
    public Map<String,Object>myWish(){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Wish> wishList=wishService.getByAccountID(presentAccount.getAccountID());
        Collections.reverse(wishList);// 倒序排列
        //accountInfoList.add(presentAccount);
        map.put("mywishList",wishList);
        return map;
    }
    //添加心愿
    @PostMapping("/weChatAddWish")
    public Map<String,Object> addWish(@RequestBody Wish wish)
    {
        Map<String,Object> modelMap = new HashMap<String, Object>();
        System.out.println("addWish");
       // System.out.println(wish.getTitle());
      //  System.out.println(wish.getContent());
      //  System.out.println(wish.getPermision());
        wish.setAccountInfo(presentAccount);
        wish.setRelTime(new Date());
        modelMap.put("success",wishService.addWish(wish));
        return modelMap;
    }
    //删除wish
    @GetMapping("/weChatDeleteWish/{id}")
    public boolean delete(@PathVariable("id") Integer id){
        try {
            wishService.deleteWish(id);
        }
        catch(Exception e){
            return  false;
        }
        return true;
    }
    //我的收藏
    @GetMapping("/reCollection")
    public Map<String,Object>myCollection(){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Collection> collectionList=collectionService.queryMyCollection(presentAccount.getAccountID());
        Collections.reverse(collectionList);// 倒序排列
        //accountInfoList.add(presentAccount);
        map.put("myCollectionList",collectionList);
        return map;
    }
    //获取个人心愿
    @GetMapping("/PublicWish")
    public Map<String,Object>publicWish(){
        Map<String,Object> map=new HashMap<String, Object>();
        List<Wish> wishList=wishService.getByPermision(true);//所有人可见
        Collections.reverse(wishList);// 倒序排列
        map.put("publicWishList",wishList);
        return map;
    }
}
