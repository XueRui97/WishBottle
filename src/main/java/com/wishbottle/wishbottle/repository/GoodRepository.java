package com.wishbottle.wishbottle.repository;

import com.wishbottle.wishbottle.bean.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository  extends JpaRepository<Good,Integer> {
}
