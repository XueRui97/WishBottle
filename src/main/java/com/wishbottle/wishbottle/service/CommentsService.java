//评论管理服务接口
package com.wishbottle.wishbottle.service;

import com.wishbottle.wishbottle.bean.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsService {
    List<Comments> getAllComments();
    Optional<Comments> findByID(Integer id);
    void deleteComment(Comments comments);
}
