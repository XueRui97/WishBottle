//心愿管理服务接口
package com.wishbottle.wishbottle.service;

import com.wishbottle.wishbottle.bean.Wish;
import java.util.List;
import java.util.Optional;

public interface WishService {
    /**
     * //查询全部心愿
     * @return
     * 全部心愿
     */
    List<Wish>  getAllWish();
    /**
     * 按照WishID进行查询
     */
    Optional<Wish> findByID(Integer id);
    void deleteWish(Wish wish);
    List<Wish> search(String search);
    List<Wish> search(Integer search);
    Wish addWish(Wish wish);
    List<Wish> getByPermision(boolean permisiom);
    List<Wish> getByAccountID(Integer accountID);
    //更新心愿
    Wish updateWish(Wish awish);
}