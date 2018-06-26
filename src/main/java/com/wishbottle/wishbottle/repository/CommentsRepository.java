package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
