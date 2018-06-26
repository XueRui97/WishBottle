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
    @Override
    public  List<Collection>  getAllCollection() {
        return collectionRepository.findAll();
    }
}
