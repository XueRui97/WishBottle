package com.wishbottle.wishbottle.serviceImpl;
import com.wishbottle.wishbottle.bean.Comments;
import com.wishbottle.wishbottle.repository.CommentsRepository;
import com.wishbottle.wishbottle.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    @Override
    public Optional<Comments> findByID(Integer id) {
        return commentsRepository.findById(id);
    }

    @Override
    public void deleteComment(Comments comments) {
        commentsRepository.delete(comments);
    }
}