package com.wishbottle.wishbottle.serviceImpl;
import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.repository.WishRepository;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {
    @Autowired
    private WishRepository WishRepository;
    @Override
    public List<Wish> getAllWish() { return WishRepository.findAll(); }
}