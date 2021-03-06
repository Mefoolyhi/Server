package com.example.admin.fence.controller;

import com.example.admin.fence.entity.New;
import com.example.admin.fence.parser.NewsParcer;
import com.example.admin.fence.repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<New> getNews(){
        return repository.findAll();


    }

    @RequestMapping(value = "/new/{name}", method = RequestMethod.GET)
    public New getNew(@PathVariable("name") String name){

            if (repository.existsById(name)){
                return repository.findById(name).get();
            }
            else
            {
                NewsParcer np = new NewsParcer("http://www.justmedia.ru/news/culture/" + name);
                repository.saveAndFlush(new New(name, np.getBody()));
                return new New(name, np.getBody());
            }


    }



    @Autowired
    private NewRepository repository;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public New getTestNew(){
        return new New("Test", "BLABLABLABLABLA");
    }


}
