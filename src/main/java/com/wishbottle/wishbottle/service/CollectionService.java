//收藏管理服务接口
package com.wishbottle.wishbottle.service;

import com.wishbottle.wishbottle.bean.Collection;
import java.util.List;
import java.util.Optional;

public interface CollectionService {
    List<Collection>  getAllCollection();
    //void deleteCollectionByWishID(Integer id);
    void deleteCollection(Collection collection);
    List<Collection> search(String search);
    List<Collection> queryMyCollection(Integer accountID);
    Optional<Collection> findByID(Integer id);
}
