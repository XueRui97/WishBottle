package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.bean.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Integer> {
    /*@Modifying
    @Query(" from Collection a where a.collectionID=?1")
    public List<Collection > deleteCollectionByID(Integer id);
    */
}
