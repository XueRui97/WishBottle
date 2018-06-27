package com.wishbottle.wishbottle.service;

import com.wishbottle.wishbottle.bean.Collection;
import java.util.List;

public interface CollectionService {
    List<Collection>  getAllCollection();
    //void deleteCollectionByWishID(Integer id);
    void deleteCollection(Collection collection);
}
