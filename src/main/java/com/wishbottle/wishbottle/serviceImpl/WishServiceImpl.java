//心愿管理服务类
package com.wishbottle.wishbottle.serviceImpl;
import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.repository.WishRepository;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishServiceImpl implements WishService {
    @Autowired
    private WishRepository WishRepository;
    //查询全部心愿
    @Override
    public List<Wish> getAllWish() { return WishRepository.findAll(); }
    //按照WishID进行查询
    @Override
    public Optional<Wish> findByID(Integer id) {
        return WishRepository.findById(id);
    }
    //删除心愿
    @Override
    public void deleteWish(Wish wish) {
        WishRepository.delete(wish);
    }
    //查找
    @Override
    public List<Wish> search(String search) {
        return  WishRepository.queryBySearch(search);
    }
    //查找
    @Override
    public List<Wish> search(Integer search) {
        return  WishRepository.queryBySearch(search);
    }
    @Override
    public Wish addWish(Wish wish){
        return WishRepository.save(wish);
    }

    @Override
    public List<Wish> getByPermision(boolean permisiom) {
        return WishRepository.queryByPermision(permisiom);
    }

    @Override
    public List<Wish> getByAccountID(Integer accountID) {
        return WishRepository.queryByAccountID(accountID);
    }
}