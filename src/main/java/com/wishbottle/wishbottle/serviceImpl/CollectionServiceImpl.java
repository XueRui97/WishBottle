//收藏管理服务类
package com.wishbottle.wishbottle.serviceImpl;

import com.wishbottle.wishbottle.bean.Collection;
import com.wishbottle.wishbottle.repository.CollectionRepository;
import com.wishbottle.wishbottle.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;
    @Override//查询全部收藏
    public  List<Collection>  getAllCollection() {
        return collectionRepository.findAll();
    }

    /*@Override
    public void deleteCollectionByWishID(Integer id) {
        collectionRepository.deleteCollectionByID(id);
    }*/
    @Override//删除收藏
    public void deleteCollection(Collection collection){
        collectionRepository.delete(collection);
    }
    @Override
    public List<Collection> search(String search) {
        return  collectionRepository.queryBySearch(search);
    }
}
