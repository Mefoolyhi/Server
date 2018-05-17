package com.example.admin.fence;


import javax.persistence.Entity;

@Entity
public class Contact {

    private String name;

    private String phone;
    private String birthday;


    private int id;

    public Contact(int id, String name, String phone, String birthday) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
    }



    public Contact(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    @Override
    public String toString() {
        return "Contact{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                '}'+"\n";
    }
}

