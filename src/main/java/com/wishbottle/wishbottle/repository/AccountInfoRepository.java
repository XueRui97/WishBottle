package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountInfoRepository extends JpaRepository<AccountInfo,Integer> {
    @Query("select a from AccountInfo a where a.NikeName=?1 and a.Password=?2")
    public List<AccountInfo> queryByName(String name,String password);
    @Query("select a from AccountInfo a where a.NikeName like ?1 " +
            "or a.Email like ?1")
    public List<AccountInfo> queryBySearch(String search);
}
