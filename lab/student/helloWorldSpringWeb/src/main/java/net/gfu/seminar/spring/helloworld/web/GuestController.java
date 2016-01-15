package net.gfu.seminar.spring.helloworld.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user1 on 14.01.2016.
 */

@Controller
@RequestMapping("/guest")
@Scope("request")
public class GuestController {

    @RequestMapping(value = "health", method = RequestMethod.GET)
    public @ResponseBody String status(){
        return "Ok Ok";
    }
}
