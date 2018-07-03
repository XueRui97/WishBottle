//个人主页控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.*;
import com.wishbottle.wishbottle.bean.Collection;
import com.wishbottle.wishbottle.service.AccountInfoService;
import com.wishbottle.wishbottle.service.CollectionService;
import com.wishbottle.wishbottle.service.CommentsService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
@RequestMapping("/")
public class PersonController {
    private Integer wishNum,goodNum,commentNum;
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private WishService wishService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CollectionService collectionService;
    //树洞
    @GetMapping("/tree")
    public String tree(Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            return returnTree(model,"treePage");
        }
        else
            return "loginPage";
    }
    //发布心愿
    @PostMapping("/addWish")
    public String addWish(@RequestParam("wishTitle") String wishTitle,
                          @RequestParam("wishContent") String wishContent,
                          @RequestParam("visibility") String visibility,
                          Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            boolean  Permision=visibility.equals("all");
            Wish awish=new Wish(AccountInfoController.presentAccount,wishTitle,wishContent,Permision);
            wishService.addWish(awish);
            return returnTree(model,"treePage");
        }
        else
            return "loginPage";

    }
    //修改用户信息
    @PostMapping("/editPost")
    public String updateAccountInfo(@RequestParam("userNameEdit") String name,
                                    @RequestParam("emailEdit") String email,
                                    @RequestParam("birthdayEdit") Date birthday,
                                    @RequestParam("introEdit") String introEdit,
                                    Model model) {
        if(AccountInfoController.presentAccount.getEmail()!=null){
            List<AccountInfo> accountInfoList1=accountInfoService.queryByEmailOrName(name);
            List<AccountInfo> accountInfoList2=accountInfoService.queryByEmailOrName(email);
            if((accountInfoList1.size()==0&&accountInfoList2.size()==0)||//name,email都是不在数据库的
                (accountInfoList1.size()==0&&accountInfoList2.size()==1
                        &&email.equals(AccountInfoController.presentAccount.getEmail()))||//name不再数据库中,email为原来的
                accountInfoList1.size()==1&&accountInfoList2.size()==0
                        &&name.equals(AccountInfoController.presentAccount.getNikeName())||//email不再数据库中,name为原来的
                accountInfoList1.size()==1&&accountInfoList2.size()==1
                        &&name.equals(AccountInfoController.presentAccount.getNikeName())
                        &&email.equals(AccountInfoController.presentAccount.getEmail())) //name，email都为原来的
            {
                AccountInfoController.presentAccount.setBirthday(birthday);
                AccountInfoController.presentAccount.setSelfIntro(introEdit);
                AccountInfoController.presentAccount.setNikeName(name);
                AccountInfoController.presentAccount.setEmail(email);
            }
                accountInfoService.updateAccountInfo(AccountInfoController.presentAccount);
               // System.out.println(AccountInfoController.presentAccount.getAccountID() + "  " + accountInfo.getAccountID());
                return returnTree(model,"treePage");
        }
        else
            return "loginPage";
    }
    //删除个人心愿
    @GetMapping("/deleteMyWish/{WishID}")
    public String deletMyWish(@PathVariable("WishID") Integer id, Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            Optional<Wish> wishs =wishService.findByID(id);

            //delete collection
            List<Collection> collectionList=collectionService.getAllCollection();
            for(Collection acollection :collectionList)
                if(acollection.getWish().getWishID()==id)
                    collectionService.deleteCollection(acollection);

            //delete comment
            List<Comments> commentsList=commentsService.getAllComments();
            for(Comments acomment:commentsList)
                if(acomment.getWish().getWishID()==id)
                    commentsService.deleteComment(acomment);
            wishService.deleteWish(wishs.get());
            return  returnTree(model,"redirect:/tree#tab-bottom-left1");
        }

        else
            return "loginPage";
    }
    //删除发布的评论
    @GetMapping("/deleteMyComment/{CommentID}")
    public String deletMyComment(@PathVariable("CommentID") Integer id, Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            Optional<Comments> comments =commentsService.findByID(id);
            commentsService.deleteComment(comments.get());
            return  returnTree(model,"redirect:/tree#tab-bottom-left5");
        }
        else
            return "loginPage";
    }
    //删除收藏心愿
    @GetMapping("/deleteMyCollection/{CollectionID}")
    public String deletMyCollection(@PathVariable("CollectionID") Integer id, Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            Optional<Collection> collection =collectionService.findByID(id);
            collectionService.deleteCollection(collection.get());
            return  returnTree(model,"redirect:/tree#tab-bottom-left4");
        }
        else
            return "loginPage";
    }
    //发表评论
    @PostMapping("/addComment")
    public String adddComment(@RequestParam("cmEdit") String cmEdit,
                              @RequestParam("wishID") Integer wishID,
                              Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            Wish awish=wishService.findByID(wishID).get();
            Comments acomment=new Comments(awish,AccountInfoController.presentAccount,cmEdit);
            commentsService.addComment(acomment);//保存评论
            awish.setCommentNum(awish.getCommentNum()+1);//wish的评论数加1
            wishService.updateWish(awish);
            return returnTree(model,"treePage");
        }
        else
            return "loginPage";
    }
    //添加或删除收藏
    @PostMapping("/collect")
    public String addCollection(@RequestParam("collectWishID") Integer WishID,Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            List<Collection> collectionList=collectionService.searchByAccountIDAndWishID(AccountInfoController.presentAccount.getAccountID(),WishID);
            Wish awish=wishService.findByID(WishID).get();
            if(collectionList.isEmpty())
            {
                Collection acollection=new Collection(AccountInfoController.presentAccount,awish);
                collectionService.add(acollection);
                awish.setCollectionNum(awish.getCollectionNum()+1);
                wishService.updateWish(awish);
            }
            else {
                collectionService.deleteCollection(collectionList.get(0));
                awish.setCollectionNum(awish.getCollectionNum()-1);
                wishService.updateWish(awish);
            }
            return returnTree(model,"treePage");
        }
        else
            return "loginPage";
    }
    //返回心愿、心愿找评论、评论、被评论和收藏的list方法
    private String returnTree(Model model,String returnStr){
        //我的心愿
        List<Wish> wishList=wishService.getByAccountID(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("myWish",wishList);
        //
        WishToComments aWishToComment=//初始化，不能为空null
                new WishToComments(wishList.get(0).getWishID(),commentsService.search(wishList.get(0).getWishID()));
        for(Wish wish:wishList)
            aWishToComment.wishToCommentsList.add(
                    new WishToComments(wish.getWishID(),commentsService.search(wish.getWishID())));
        model.addAttribute("aWishToComment",aWishToComment);

        //点赞数
        int goodNum=0;
        for(Wish awish:wishList){
            goodNum+=awish.getGoodNum();
        }
        model.addAttribute("goodNum",goodNum);
        //我的评论
        List<Comments> myList=commentsService.queryByAccountID(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("myComments",myList);
        //对我的评论
        List<Comments> otherList=commentsService.queryOtherComment(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("otherComments",otherList);
        model.addAttribute("presentAccount",AccountInfoController.presentAccount);
        //我的收藏
        List<Collection> myCollection=collectionService.queryMyCollection(AccountInfoController.presentAccount.getAccountID());
        model.addAttribute("myCollection",myCollection);
        return returnStr;
    }

}
