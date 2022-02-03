package com.example.kingship.model;

import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String  cat;
    String Bname;
    String pname;
    String price;
    String descrip;
    byte[] image;

    public  Product(int id,String  cat,String Bname,String pname, String price,String descrip){
        this.id= id;
        this.cat = cat;
        this.Bname= Bname;
        this.pname=pname;
        this.price= price;
        this.descrip=descrip;

    }
    public  Product(String  cat,String Bname,String pname, String price,String descrip){

        this.cat = cat;
        this.Bname= Bname;
        this.pname=pname;
        this.price= price;
        this.descrip=descrip;

    }


    public byte[] getImage() {
        return image;
    }

    public void setImage( byte[] image ) {
        this.image = image;
    }

    public  Product( int id, String  cat, String Bname, String pname, String price, String descrip, byte[] image){
        this.id= id;
        this.cat = cat;
        this.Bname= Bname;
        this.pname=pname;
        this.price= price;
        this.descrip=descrip;
        this.image=image;

    }
    public  Product(String  cat,String Bname,String pname, String price,String descrip, byte[] image){

        this.cat = cat;
        this.Bname= Bname;
        this.pname=pname;
        this.price= price;
        this.descrip=descrip;
        this.image=image;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getBname() {
        return Bname;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }


}
