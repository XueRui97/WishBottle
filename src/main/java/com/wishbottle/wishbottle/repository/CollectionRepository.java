package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Integer> {
    /*@Modifying
    @Query(" from Collection a where a.collectionID=?1")
    public List<Collection > deleteCollectionByID(Integer id);
    */
    @Query("select a from Collection a where a.accountInfo.NikeName like ?1 " +
            "or a.wish.Title like ?1 or a.wish.Content like ?1")
    public List<Collection> queryBySearch(String search);
    @Query("select a from Collection a where a.accountInfo.AccountID=?1"  )
    public List<Collection> queryMyCollection(Integer search);
}
