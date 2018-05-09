package com.example.admin.fence.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.persistence.Lob;

public class NewsParcer {
    String body;

    public NewsParcer(String link){
        try {

            body = "";
            Document doc = Jsoup.connect(link).get();
            Element tit = doc.selectFirst(".l-col31_1__in-inner").selectFirst(".b-content-header");
            body += "\n" + tit.text();
            tit = doc.selectFirst(".b-content-wrapper").selectFirst(".b-news__dop-title");
            body += "\n" + tit.text();
            Element e = doc.selectFirst(".b-content-wrapper").selectFirst(".content");
            body += "\n" + e.text();



        } catch (Exception e) {

            throw new IllegalArgumentException("parser is broken",e);


        }


    }

    @Lob
    public String getBody() {
        return body;
    }
}
