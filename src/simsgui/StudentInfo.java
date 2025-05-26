/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simsgui;

import java.util.Objects;

/**
 *
 * @author marku
 */

/* 
    Contains a student's information, including ID, name, degree, and grade.
    Copied from Assignment 1
*/
public class StudentInfo {
    private int id;
    private String name;
    private String degree;
    private String grade;
    
    public StudentInfo(int id, String name, String degree, String grade) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Degree: " + degree + ", Grade: " + grade;
    }   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.degree);
        hash = 29 * hash + Objects.hashCode(this.grade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentInfo other = (StudentInfo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.degree, other.degree)) {
            return false;
        }
        return Objects.equals(this.grade, other.grade);
    }
    
    
}
