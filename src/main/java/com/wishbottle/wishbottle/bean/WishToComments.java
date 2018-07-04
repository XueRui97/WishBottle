package com.wishbottle.wishbottle.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 从wish导航到comments
 * 用于显示comments
 */
public class WishToComments {
    private Integer WishID;
    private List<Comments> commentsList;
    //存放WishToComments对象列表，用于显示心愿的评论
    public ArrayList<WishToComments> wishToCommentsList=new ArrayList<>();
    //存放collection对象列表，用于查询是否已经收藏
    public List<Collection> collectionList=new ArrayList<>();
    private Integer collectionNum;

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public List<Collection> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
    }

    public Integer getWishID() {
        return WishID;
    }

    public void setWishID(Integer wishID) {
        WishID = wishID;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public boolean hasCollection(Integer wishID,Integer accountInfoID){
        for(Collection collection:collectionList) {
            System.out.println("getwishID"+collection.getWish().getWishID());
            System.out.println("wishID"+wishID);
           //System.out.println(collection.getWish().getWishID().equals(wishID));
            System.out.println("getAccountID"+collection.getAccountInfo().getAccountID());
            System.out.println("accountID"+accountInfoID);
            //System.out.println(collection.getAccountInfo().getAccountID().equals( accountInfoID));
            if (collection.getWish().getWishID().equals(wishID)
                    && collection.getAccountInfo().getAccountID().equals( accountInfoID)){

                System.out.println(1);
                return true;}
        }
        return false;
    }
    // 构造方法
    public WishToComments(Integer wishID, List<Comments> commentsList) {
        WishID = wishID;
        this.commentsList = commentsList;
    }

   //构造方法
    public WishToComments() {
    }

    public List<Comments> getByWishID(int WishID){
        for(WishToComments wishToComments:wishToCommentsList)
            if(wishToComments.getWishID()==WishID)
                return wishToComments.getCommentsList();
        return null;
    }


}
