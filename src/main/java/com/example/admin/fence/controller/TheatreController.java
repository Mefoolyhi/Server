package com.example.admin.fence.controller;


import com.example.admin.fence.entity.Theatre;
import com.example.admin.fence.parser.ConcertsParser;
import com.example.admin.fence.parser.TheatreParser;
import com.example.admin.fence.repository.TheatreRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TheatreController {





    @RequestMapping(value = "/theatres", method = RequestMethod.GET)
    public String getNews(){

        new TheatreParser().parse(repository);
        new ConcertsParser().parse(repository);
        List<Theatre> list = repository.findAll();

        return new Gson().toJson(list);



    }






    @Autowired
    TheatreRepository repository;
}
