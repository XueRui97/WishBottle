//账号管理服务接口
package com.wishbottle.wishbottle.service;
import com.wishbottle.wishbottle.bean.AccountInfo;


import java.util.List;
import java.util.Optional;

public interface AccountInfoService {
   AccountInfo addAccountInfo(AccountInfo accountInfo);
   List<AccountInfo> queryByEmailOrName(String EmailOrName);
   List<AccountInfo> getAllAccountInfo();
   Optional<AccountInfo> findByID(Integer id);
  // void deleteAccountInfo(AccountInfo accountInfo);
   List<AccountInfo> search(String search);
}
