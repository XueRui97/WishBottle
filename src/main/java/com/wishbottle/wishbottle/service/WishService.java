//心愿管理服务接口
package com.wishbottle.wishbottle.service;

import com.wishbottle.wishbottle.bean.Wish;
import java.util.List;
import java.util.Optional;

public interface WishService {
    List<Wish>  getAllWish();
    Optional<Wish> findByID(Integer id);
    void deleteWish(Wish wish);
    List<Wish> search(String search);
    List<Wish> search(Integer search);
}