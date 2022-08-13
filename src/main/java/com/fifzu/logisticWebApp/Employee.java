package com.fifzu.logisticWebApp;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
    private String name;
    private String email;
    private String department;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
