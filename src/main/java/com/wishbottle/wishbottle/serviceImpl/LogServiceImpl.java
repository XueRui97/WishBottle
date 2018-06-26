package com.wishbottle.wishbottle.serviceImpl;

import com.wishbottle.wishbottle.bean.Log;
import com.wishbottle.wishbottle.repository.LogRepository;
import com.wishbottle.wishbottle.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public  List<Log>  getAllLog() {
        return logRepository.findAll();
    }

}
