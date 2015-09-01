package net.woniper.spring.board.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by woniper on 15. 3. 25..
 */
@Controller
public class MainController {


    @RequestMapping("/")
    public @ResponseBody String main() {
        return "test";
    }

}
