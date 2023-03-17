package com.example.studentgradeproject;

public class Grade {
    private int student_Id;
    private String student_Lname;
    private String student_Fname;
    private int s_grade_Assignment1;
    private int s_grade_Assignment2;
    private int s_grade_Assignment3;
    private int s_grade_Mid_Term;
    private int s_grade_Final_Term;

    //Default constructor
    public Grade() {
    }

    public Grade(int student_Id, String student_Lname, String student_Fname, int s_grade_Assignment1, int s_grade_Assignment2, int s_grade_Assignment3, int s_grade_Mid_Term, int s_grade_Final_Term) {
        this.student_Id = student_Id;
        this.student_Lname = student_Lname;
        this.student_Fname = student_Fname;
        this.s_grade_Assignment1 = s_grade_Assignment1;
        this.s_grade_Assignment2 = s_grade_Assignment2;
        this.s_grade_Assignment3 = s_grade_Assignment3;
        this.s_grade_Mid_Term = s_grade_Mid_Term;
        this.s_grade_Final_Term = s_grade_Final_Term;
    }

    //Getters and Setters
    public int getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(int student_Id) {
        this.student_Id = student_Id;
    }

    public String getStudent_Lname() {
        return student_Lname;
    }

    public void setStudent_Lname(String student_Lname) {
        this.student_Lname = student_Lname;
    }

    public String getStudent_Fname() {
        return student_Fname;
    }

    public void setStudent_Fname(String student_Fname) {
        this.student_Fname = student_Fname;
    }

    public int getS_grade_Assignment1() {
        return s_grade_Assignment1;
    }

    public void setS_grade_Assignment1(int s_grade_Assignment1) {
        this.s_grade_Assignment1 = s_grade_Assignment1;
    }

    public int getS_grade_Assignment2() {
        return s_grade_Assignment2;
    }

    public void setS_grade_Assignment2(int s_grade_Assignment2) {
        this.s_grade_Assignment2 = s_grade_Assignment2;
    }

    public int getS_grade_Assignment3() {
        return s_grade_Assignment3;
    }

    public void setS_grade_Assignment3(int s_grade_Assignment3) {
        this.s_grade_Assignment3 = s_grade_Assignment3;
    }

    public int getS_grade_Mid_Term() {
        return s_grade_Mid_Term;
    }

    public void setS_grade_Mid_Term(int s_grade_Mid_Term) {
        this.s_grade_Mid_Term = s_grade_Mid_Term;
    }

    public int getS_grade_Final_Term() {
        return s_grade_Final_Term;
    }

    public void setS_grade_Final_Term(int s_grade_Final_Term) {
        this.s_grade_Final_Term = s_grade_Final_Term;
    }

    public double Calculate_GradeAverage(){
        return ((s_grade_Assignment1 + s_grade_Assignment2 + s_grade_Assignment3) / 3.0) * 0.4 +
                (s_grade_Mid_Term * 0.3) + (s_grade_Final_Term * 0.3);

    }

    @Override
    public String toString() {
        return "Grade{" +
                "student_Id=" + student_Id +
                ", student_Lname='" + student_Lname + '\'' +
                ", student_Fname='" + student_Fname + '\'' +
                ", s_grade_Assignment1=" + s_grade_Assignment1 +
                ", s_grade_Assignment2=" + s_grade_Assignment2 +
                ", s_grade_Assignment3=" + s_grade_Assignment3 +
                ", s_grade_Mid_Term=" + s_grade_Mid_Term +
                ", s_grade_Final_Term=" + s_grade_Final_Term +
                '}';
    }
}
