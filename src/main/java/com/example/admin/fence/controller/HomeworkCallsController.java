package com.example.admin.fence.controller;

import com.example.admin.fence.Contact;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeworkCallsController {


    private ArrayList<Contact> data = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++){
            Contact c = new Contact(i,"name " + i,"phone " + i, "birthday " + i);
            data.add(c);
        }
    }

    @RequestMapping(value = "/getContacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getContacts(){
        return data;
    }


    @RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delContact(@PathVariable int id){
        data.remove(id);
    }



    @RequestMapping(value = "/updateContact/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void updateContact(@RequestBody String c, @PathVariable int id){
        Gson gson = new Gson();
       data.set(id,gson.fromJson(c,Contact.class));
    }


    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    @ResponseBody
    public void saveContact(@RequestBody String contact){
        Gson gson = new Gson();
        data.add(gson.fromJson(contact,Contact.class));
    }


}
