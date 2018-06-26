package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish,Integer> {
}
