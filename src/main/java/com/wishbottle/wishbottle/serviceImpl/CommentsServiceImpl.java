package com.wishbottle.wishbottle.serviceImpl;
import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.repository.CommentsRepository;
import com.wishbottle.wishbottle.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsRepository CommentsRepository;
   @Override
    public List<Comments> getAllComments() {
        return CommentsRepository.findAll();
    }
}