//账号管理服务类
package com.wishbottle.wishbottle.serviceImpl;

import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.repository.AccountInfoRepository;
import com.wishbottle.wishbottle.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Override
    public AccountInfo addAccountInfo(AccountInfo accountInfo){
        return accountInfoRepository.save(accountInfo);
    }
    @Override
   public List<AccountInfo> queryByEmailOrName(String EmailOrName){
        return accountInfoRepository.queryByEmailOrName(EmailOrName);
    }
    @Override//查询全部账号
    public List<AccountInfo> getAllAccountInfo(){
        return accountInfoRepository.findAll();
    }
    @Override//根据账号ID查询账号
    public Optional<AccountInfo> findByID(Integer id){
        return  accountInfoRepository.findById(id);
    }

    //修改用户信息
    @Override
    public AccountInfo updateAccountInfo(AccountInfo accountInfo) {
       return accountInfoRepository.save(accountInfo);
    }

    @Override
    public List<AccountInfo> search(String search) {
        return  accountInfoRepository.queryBySearch(search);
    }
}
