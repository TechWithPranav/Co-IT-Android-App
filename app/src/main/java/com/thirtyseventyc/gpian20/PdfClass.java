package com.thirtyseventyc.gpian20;

public class PdfClass {
    public String name,url;

    PdfClass(){
    }
    PdfClass(String name, String url){
        this.name=name;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
