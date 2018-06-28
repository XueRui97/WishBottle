package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish,Integer> {
    //
    @Query("select a from Wish a where a.accountInfo.NikeName like ?1 " +
            "or a.Title like ?1 or a.Content like ?1")
    public List<Wish> queryBySearch(String search);
    @Query("select a from Wish a where a.WishID= ?1")
    public List<Wish> queryBySearch(Integer search);
}
