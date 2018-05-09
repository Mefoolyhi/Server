package com.example.admin.fence.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.persistence.Lob;

public class NewsParcer {
    String body;

    public NewsParcer(String link){
        try {


            Document doc = Jsoup.connect(link).get();
            Element e = doc.selectFirst(".b-content-wrapper").selectFirst(".content");
            body = " \n " + e.text();



        } catch (Exception e) {

            throw new IllegalArgumentException("parser is broken",e);


        }


    }

    @Lob
    public String getBody() {
        return body;
    }
}
