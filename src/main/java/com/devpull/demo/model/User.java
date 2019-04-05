package com.devpull.demo.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="email")
    private String email;
    
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    @Column(name="cv")
    private File userCv;
    @Column(name="about_me")
    private String aboutMe;
    @Column(name="job_description")
    private String jobDescr;   
//    
//    @OneToMany
//    @JoinColumn(name="user_role_id")
//    private User role;
// 
    public User() {
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public File getUserCv() {
        return userCv;
    }

    public void setUserCv(File userCv) {
        this.userCv = userCv;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getJobDescr() {
        return jobDescr;
    }

    public void setJobDescr(String jobDescr) {
        this.jobDescr = jobDescr;
    }

//    public User getRole() {
//        return role;
//    }
//
//    public void setRole(User role) {
//        this.role = role;
//    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username=" + username + ", password=" + password + ", userCv=" + userCv + ", aboutMe=" + aboutMe + ", jobDescr=" + jobDescr + '}';
    }

    
    
    
}

