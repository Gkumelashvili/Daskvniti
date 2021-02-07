package com.example.bookapp;

public class book {

    String title;
    String answer;

    public book() {
    }
    public book(String title, String answer){
        this.title = title;
        this.answer = answer;

    }

//    public static Class<Book> Class() {
//        return null;
//    }


    public String getTitle(){
        return title;
    }
    public  void setTitle(String title){
        this.title = title;
    }
    public String getAnswer(String answer){
        return answer;

    }
    public  void  setAnswer(String answer){
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}



