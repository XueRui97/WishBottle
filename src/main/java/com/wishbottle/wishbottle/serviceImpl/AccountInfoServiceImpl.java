package com.wishbottle.wishbottle.serviceImpl;

import com.wishbottle.wishbottle.bean.AccountInfo;
import com.wishbottle.wishbottle.repository.AccountInfoRepository;
import com.wishbottle.wishbottle.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
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
   public List<AccountInfo> queryByAccountName(String name,String password){
        return accountInfoRepository.queryByName(name,password);
    }
    @Override
    public List<AccountInfo> getAllAccountInfo(){
        return accountInfoRepository.findAll();
    }
    @Override
    public Optional<AccountInfo> findByID(Integer id){
        return  accountInfoRepository.findById(id);
    }
    /*@Override
    public void deleteAccountInfo(AccountInfo accountInfo){
       accountInfoRepository.delete(accountInfo);

    }*/
}
