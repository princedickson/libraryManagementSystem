package com.explicit.libraryManagementSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePage {

    @GetMapping("/")
    public String HomePage(){
        return "HomePage";
    }
}
