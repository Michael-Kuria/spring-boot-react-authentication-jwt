package com.michael.springbootreactauthenticationjwt.model;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
//@Table(name ="students")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstname;
    private String lastname;
    @Column(name ="ear")
    private int year;


    public Student(String firstname, String lastname, int year){
        this.firstname = firstname;
        this.lastname = lastname;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
