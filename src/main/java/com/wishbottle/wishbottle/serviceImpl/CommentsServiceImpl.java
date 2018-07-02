//评论管理服务类
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
    @Override//查询全部评论
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    @Override//根据评论ID查询评论
    public Optional<Comments> findByID(Integer id) {
        return commentsRepository.findById(id);
    }

    @Override//删除评论
    public void deleteComment(Comments comments) {
        commentsRepository.delete(comments);
    }

    //

    @Override
    public List<Comments> search(String search) {
        return  commentsRepository.queryBySearch(search);
    }

    //
    @Override
    public List<Comments> search(Integer search) {
        return  commentsRepository.queryBySearch(search);
    }

    @Override
    public List<Comments> queryByAccountID(Integer accountID) {
        return commentsRepository.queryByAccountID(accountID);
    }

    @Override
    public List<Comments> queryOtherComment(Integer accountID) {
        return commentsRepository.queryOtherComment(accountID);
    }


}