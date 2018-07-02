package com.wishbottle.wishbottle.bean;

import java.util.ArrayList;
import java.util.List;

public class WishToComments {
    private Integer WishID;
    private List<Comments> commentsList;
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

    public WishToComments(Integer wishID, List<Comments> commentsList) {
        WishID = wishID;
        this.commentsList = commentsList;
    }

    public WishToComments() {
    }

    public List<Comments> getByWishID(int WishID){
        for(WishToComments wishToComments:wishToCommentsList)
            if(wishToComments.getWishID()==WishID)
                return wishToComments.getCommentsList();
        return null;
    }

}
