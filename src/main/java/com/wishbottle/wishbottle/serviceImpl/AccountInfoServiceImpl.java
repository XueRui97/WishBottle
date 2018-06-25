package com.wishbottle.wishbottle.serviceImpl;

import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.repository.AccountInfoRepository;
import com.wishbottle.wishbottle.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Override
    public AccountInfo addAccountInfo(AccountInfo accountInfo){
        return accountInfoRepository.save(accountInfo);
    }
    @Override
   public List<AccountInfo> queryByAccountName(String name){
        return accountInfoRepository.queryByName(name);
    }
}
