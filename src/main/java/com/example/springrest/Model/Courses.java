package com.example.springrest.Model;

public class Courses {
    private long course_id;
    private String course_name;
    private double course_credit;

    public Courses(){};

    public Courses(int course_id,String course_name,double course_credit){
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_credit = course_credit;
    }

    public long getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public double getCourse_credit() {
        return course_credit;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_credit(double course_credit) {
        this.course_credit = course_credit;
    }
}
