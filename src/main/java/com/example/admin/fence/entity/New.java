package com.example.admin.fence.entity;


import javax.persistence.*;

@Entity
@Table(name = "fence")
public class New {

    @Id
    String name;

    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    String text;

    public New(String name,String text){
        this.name = name;
        this.text = text;
    }

    public New(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
