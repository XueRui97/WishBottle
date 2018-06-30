package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
    @Query("select a from Comments a where a.accountInfo.NikeName like ?1 " +
            "or a.wish.Content like ?1 or a.CMContent like ?1")
    public List<Comments> queryBySearch(String search);
    @Query("select a from Comments a where a.wish.WishID=?1")
    public List<Comments> queryBySearch(Integer search);
    @Query("select a from Comments a where a.accountInfo.AccountID=?1")
    public List<Comments> queryByAccountID(Integer accountID);
    @Query("select a from Comments a,Wish b where  b.accountInfo.AccountID=?1 and" +
            " a.wish.WishID=b.WishID")
    public List<Comments> queryOtherComment(Integer accountID);
}
