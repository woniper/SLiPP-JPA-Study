package net.woniper.spring.board.mybatis.service;

import net.woniper.spring.board.mybatis.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by woniper on 15. 4. 30..
 */
@Service
public class MainServiceImpl implements MainService {

    @Autowired private Mapper mapper;

    @Override
    public String getTime() {
        return mapper.getTime();
    }
}
