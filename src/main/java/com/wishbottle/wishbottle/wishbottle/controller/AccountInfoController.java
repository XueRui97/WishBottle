//账号控制类
package com.wishbottle.wishbottle.controller;


import com.wishbottle.wishbottle.bean.*;
import com.wishbottle.wishbottle.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private WishService wishService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private LogService logService;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GoodService goodService;
    private String searchString="Search...";
    static  AccountInfo presentAccount=new AccountInfo();
    //初始页面——登录
    @GetMapping()
    public String  first(Model model){
        return  "redirect:/login";
    }
    //登录页面——登录
    @GetMapping("/login")
    public String  login(Model model){
        if(presentAccount.getEmail()!=null&&presentAccount.getLevel()==3)
        {
            return "redirect:/tree";
        }
        else if(presentAccount.getEmail()!=null&&presentAccount.getLevel()<=2)
            return "redirect:/index";
        presentAccount=new AccountInfo();
        return "loginPage";
    }

    //账号设置
    @GetMapping("/setting")
    public String  setting(){
        return "settingPage";
    }

    //跳转到数据总览页面
    @GetMapping("/index")
    public String index(Model model){
        if(presentAccount.getEmail()!=null) {
            model.addAttribute("presentAccount",presentAccount);
            return "index";
        }
        else
            return "loginPage";
    }
    //跳转到用户管理页面
    @GetMapping("/accountPage")
    public String account(Model model){
        if(presentAccount.getEmail()!=null&&presentAccount.getLevel()<=2){
            List<AccountInfo> list=accountInfoService.getAllAccountInfo();
            model.addAttribute("account",list);
            model.addAttribute("searchString",searchString);
            model.addAttribute("presentAccount",presentAccount);
            return "accountPage";
        }
       else
            return  "redirect:/login";
    }
    //查询
    @PostMapping("/searchAccount")
    public String searchAccount(@RequestParam("searchBox") String searchBox, Model model){
        if(presentAccount.getEmail()!=null){
            if (!searchBox.isEmpty())
                this.searchString=searchBox;
            List<AccountInfo> accountInfoList=accountInfoService.search("%"+ this.searchString+"%");
            model.addAttribute("account",accountInfoList);
            model.addAttribute("searchString",searchString);
            model.addAttribute("presentAccount",presentAccount);
            return "accountPage";
        }
        else
            return  "redirect:/login";
    }
    //登录验证
    @PostMapping("/loginPost")
    public String login(@RequestParam("login-email") String EmailOrName,@RequestParam("login-password") String PassWord,
                        @RequestParam("ipStr") String ipStr,@RequestParam("addressStr") String addressStr,Model model){
        List<AccountInfo> accountInfoList=accountInfoService.queryByEmailOrName(EmailOrName);
        if(accountInfoList.isEmpty()){
            System.out.println("无该用户");
            return  "redirect:/login";
        }
        else{
            //登录成功
            if(accountInfoList.get(0).getPassword().equals(PassWord)){
                presentAccount=accountInfoList.get(0);
                //添加日志
                Date date=new Date();
                Log alog=new Log(presentAccount,ipStr,date,addressStr);
                logService.save(alog);
                if(presentAccount.getLevel()==3)
                    return "redirect:/tree";
                else
                    return "redirect:/index";
            }
            else {
                System.out.println("账号与密码不匹配！");
                return  "redirect:/login";
            }
        }
    }
    /**
     * 注册验证
     */
    @PostMapping("/signupPost")
    public String  signup(@RequestParam("signup-username") String Name,@RequestParam("signup-email") String Email,
                         @RequestParam("signup-password") String PassWord,Model model){
        List<AccountInfo> accountInfoList1=accountInfoService.queryByEmailOrName(Name);
        List<AccountInfo> accountInfoList2=accountInfoService.queryByEmailOrName(Email);
        if(accountInfoList1.isEmpty()&&accountInfoList2.isEmpty()){
            AccountInfo account=new AccountInfo(Name,Email,PassWord);
            accountInfoService.addAccountInfo(account);
            presentAccount=account;
            return "redirect:/tree";
        }
        else{
            return  "redirect:/login";
        }
    }
    //超级用户注册管理员用户账号
    @PostMapping("/signupAdmitPost")
    public String signupAdmit(@RequestParam("signup-username") String name,@RequestParam("signup-email") String email,
                        @RequestParam("signup-password") String password,Model model){
        List<AccountInfo> accountInfoList1=accountInfoService.queryByEmailOrName(name);
        List<AccountInfo> accountInfoList2=accountInfoService.queryByEmailOrName(email);
        //System.out.println(accountInfoList1.isEmpty()+"    "+accountInfoList2.isEmpty());
        if(accountInfoList1.isEmpty()&&accountInfoList2.isEmpty()){
            AccountInfo account=new AccountInfo(name,email,password);
            account.setLevel(2);
            accountInfoService.addAccountInfo(account);
            //System.out.println("注册成功");
        }
        return "redirect:/accountPage";

    }
    //退出登录
    @GetMapping("/logout")
    public String logout(){
        presentAccount=new AccountInfo();
        //System.out.println(presentAccount.getEmail()+"  "+presentAccount.getLevel());
        return "redirect:/login";
    }
    //返回心愿、评论、被评论和收藏的list方法
    private String returnTree(Model model){
        model.addAttribute("presentAccount",presentAccount);
        //我的心愿
        List<Wish> wishList=wishService.getByAccountID(presentAccount.getAccountID());
        model.addAttribute("myWish",wishList);
        //
        WishToComments aWishToComment=//初始化，不能为空null
                wishList.isEmpty()? new WishToComments():
                new WishToComments(wishList.get(0).getWishID(),commentsService.search(wishList.get(0).getWishID()));
       if(!wishList.isEmpty())
            for(Wish wish:wishList)
                aWishToComment.wishToCommentsList.add(
                        new WishToComments(wish.getWishID(),commentsService.search(wish.getWishID())));

        //点赞数
        int goodNum=0;
        for(Wish awish:wishList){
            goodNum+=awish.getGoodNum();
        }
        model.addAttribute("goodNum",goodNum);
        //我的评论
        List<Comments> myList=commentsService.queryByAccountID(presentAccount.getAccountID());
        model.addAttribute("myComments",myList);
        //对我的评论
        List<Comments> otherList=commentsService.queryOtherComment(presentAccount.getAccountID());
        model.addAttribute("otherComments",otherList);
        //我的收藏
        List<Collection> myCollection=collectionService.queryMyCollection(presentAccount.getAccountID());
        model.addAttribute("myCollection",myCollection);
        aWishToComment.setCollectionList(myCollection);
        //我的赞
        List<Good> myGood=goodService.queryMyGood(presentAccount.getAccountID());
        aWishToComment.setGoodList(myGood);
        aWishToComment.setAccountInfoID(presentAccount.getAccountID());
        model.addAttribute("aWishToComment",aWishToComment);
        return "treePage";
    }

    //跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    //处理文件上传
    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile file,
                                          HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        //获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        System.out.println("path:"+path.getAbsolutePath());

        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"/src/main/resources/static/assets/img/ /");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        String filePath =upload.getAbsolutePath();
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return "uploadimg success";
    }
}
