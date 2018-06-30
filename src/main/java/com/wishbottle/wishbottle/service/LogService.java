//登录日志服务接口
package com.wishbottle.wishbottle.service;

import com.wishbottle.wishbottle.bean.Log;

import java.util.List;

public interface LogService {
    List<Log>  getAllLog();
    List<Log> search(String search);
    List<Log> search(Integer search);
    void save(Log log);
 }
