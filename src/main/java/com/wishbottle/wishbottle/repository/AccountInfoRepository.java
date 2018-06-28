package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountInfoRepository extends JpaRepository<AccountInfo,Integer> {
    @Query("select a from AccountInfo a where a.Email=?1 or a.NikeName=?1")
    public List<AccountInfo> queryByEmailOrName(String EmailOrName);
    @Query("select a from AccountInfo a where a.NikeName like ?1 " +
            "or a.Email like ?1")
    public List<AccountInfo> queryBySearch(String search);
}
