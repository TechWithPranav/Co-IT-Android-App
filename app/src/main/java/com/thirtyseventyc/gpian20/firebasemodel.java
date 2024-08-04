package com.thirtyseventyc.gpian20;

public class firebasemodel {
    private  String Title;
    private String SubTitle;
    private String Url;
    private String Notes;


    public firebasemodel(){

    }

    public firebasemodel(String Title,String SubTitle,String Url,String Notes){

        this.Title = Title;
        this.SubTitle = SubTitle;
        this.Url=Url;
        this.Notes = Notes;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public  String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

}

