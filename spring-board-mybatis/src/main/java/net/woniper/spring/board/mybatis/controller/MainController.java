package net.woniper.spring.board.mybatis.controller;

import net.woniper.spring.board.mybatis.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by woniper on 15. 3. 25..
 */
@Controller
public class MainController {

    @Autowired private MainService service;

    @RequestMapping("/")
    public @ResponseBody String main() {
        return service.getTime();
    }

}
