package com.example.admin.fence.controller;

import com.example.admin.fence.entity.New;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public New getNews(){
        return createMockNew();


    }

    private New createMockNew(){
        New n = new New();
        n.setName("Test");
        n.setText("BLABLABLABLABLA");
        return n;
    }


}
