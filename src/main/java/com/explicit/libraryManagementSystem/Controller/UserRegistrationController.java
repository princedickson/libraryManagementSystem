package com.explicit.libraryManagementSystem.Controller;

import com.explicit.libraryManagementSystem.Entity.PasswordReset;
import com.explicit.libraryManagementSystem.Entity.User;
import com.explicit.libraryManagementSystem.Service.UserService;
import com.explicit.libraryManagementSystem.Web.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@Slf4j
@RequestMapping("/registration")
public class UserRegistrationController {

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping
    public String registrationUserAccount(@ModelAttribute("user")
                                          UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return "redirect:/registration?success";

    }
}
