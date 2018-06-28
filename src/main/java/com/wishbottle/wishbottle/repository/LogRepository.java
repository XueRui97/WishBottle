package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<Log,Integer> {
    //
    @Query("select a from Log a where a.accountInfo.NikeName like ?1 ")
    public List<Log> queryBySearch(String search);
    @Query("select a from Log a where a.accountInfo.AccountID= ?1 ")
    public List<Log> queryBySearch(Integer search);
}

