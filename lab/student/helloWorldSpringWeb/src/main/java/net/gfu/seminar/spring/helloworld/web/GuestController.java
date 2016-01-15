package net.gfu.seminar.spring.helloworld.web;

import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by user1 on 14.01.2016.
 */

@Controller
@RequestMapping("/guest")
@Scope("request")
public class GuestController {

   @Autowired
    private GuestDao dao;

    public GuestController(GuestDao dao) {
        System.out.println("constructor called" + dao.toString());
        this.dao = dao;
    }

    @RequestMapping(value = "health", method = RequestMethod.GET)
    public @ResponseBody String status(){
        System.out.println("HuHu");
        return (dao!=null?"Ok":"INVALID");
    }


    /**
     *  http://localhost:8080/helloWorldSpringWeb/rest/guest/1
     *  @param id
     *  @return
     */
    @RequestMapping(value = "{id}", method= RequestMethod.GET, produces = { MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Guest get(@PathVariable("id") Long id){
        return dao.findById(id);
    }

    @RequestMapping( method= RequestMethod.PUT,
            produces = { MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = { MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody String put(@RequestBody Guest guest){
        return dao.create(guest).toString();
    }

}
