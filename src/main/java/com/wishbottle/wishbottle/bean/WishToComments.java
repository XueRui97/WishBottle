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

    /**
     * 构造方法
     * @param wishID
     * @param commentsList
     */
    public WishToComments(Integer wishID, List<Comments> commentsList) {
        WishID = wishID;
        this.commentsList = commentsList;
    }

    /**
     * 构造方法
     */
    public WishToComments() {
    }

    public List<Comments> getByWishID(int WishID){
        for(WishToComments wishToComments:wishToCommentsList)
            if(wishToComments.getWishID()==WishID)
                return wishToComments.getCommentsList();
        return null;
    }


}
