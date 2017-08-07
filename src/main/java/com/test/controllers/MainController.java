package com.test.controllers;

import com.test.models.User;

import com.test.repositories.RoboResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Controller
public class MainController {


    @Autowired
    RoboResumeRepository roboResumeRepository;

    // Display the home page
    @GetMapping("/")
    public String showIndex(Model model)
    {
        String myMessage = "Welcome to the Robo Resume App";
        model.addAttribute("message", myMessage);
        return "index";
    }

    // Allow user to enter new user's information
    @GetMapping("/enteruser")
    public String addProduct(Model model)
    {
        model.addAttribute("newUser", new User());
        return "enteruser";
    }

    // Validate entered information and if it is valid display the result
    @PostMapping("/enteruser")
    public String postUser(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult)
    {


        if(bindingResult.hasErrors()){
           return "enteruser";
        }

        // Allow user to leave the end date empty (assume he/she is still employed)
        long employedDays;
        if(user.getEndDate() == null)
        {
            user.setEndDate(LocalDate.now());
            user.setEmployedDays(ChronoUnit.DAYS.between(user.getStartDate(), user.getEndDate()));
        }
        // If dates entered, do not accept the end date before the start date
        else if(user.getEndDate().compareTo(user.getStartDate())< 0)
        {
            return "enteruser";
        }


        // Calculate and assign the period (in days) for which the user has been employed
        user.setEmployedDays(ChronoUnit.DAYS.between(user.getStartDate(), user.getEndDate()));



        roboResumeRepository.save(user);
        return "resultuser";
    }

    // Display list of all users from a database
    @GetMapping("/displayallusers")
    public String showAllUsers(Model model)
    {
        Iterable <User> usersList = roboResumeRepository.findAll();
        model.addAttribute("users" , usersList);
        return "displayallusers";
    }

}
