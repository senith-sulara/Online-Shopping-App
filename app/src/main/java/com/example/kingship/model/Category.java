package com.example.kingship.model;

public class Category {
    int id;
    String  cat;

    public Category( int id, String cat ) {
        this.id = id;
        this.cat = cat;
    }
    public Category(  String cat ) {

        this.cat = cat;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getCat() {
        return cat;
    }

    public void setCat( String cat ) {
        this.cat = cat;
    }
}
