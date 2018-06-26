package com.wishbottle.wishbottle.service;
import com.wishbottle.wishbottle.bean.AccountInfo;

import java.util.List;

public interface AccountInfoService {
   AccountInfo addAccountInfo(AccountInfo accountInfo);
   List<AccountInfo> queryByAccountName(String name,String password);
}
