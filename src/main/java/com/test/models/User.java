package com.test.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.org.glassfish.gmbal.NameValue;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    @Size(min=1, max=50)
    @NameValue
    private String name;


    @NotNull
    @Size(min=1, max=50, message = "Must enter your email address.")
    @Email
    private String emailAddress;


    @NotNull
    @Size(min=1, max=50)
    private String organisation;


    // Start date (employment with the organisation)
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    // End date (employment with the organisation)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;


    // Variable for calculation the period (in days) for which the user has been employed.
    private long employedDays;




    // setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }






    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }





    public long getEmployedDays()
    {
        return employedDays;
    }

    public void setEmployedDays(long employedDays)
    {
        this.employedDays = employedDays;
    }




}
