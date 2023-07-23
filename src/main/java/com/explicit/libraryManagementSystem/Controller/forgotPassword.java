
package com.explicit.libraryManagementSystem.Controller;
import com.explicit.libraryManagementSystem.Entity.PasswordReset;
import com.explicit.libraryManagementSystem.Entity.User;
import com.explicit.libraryManagementSystem.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@Slf4j
public class forgotPassword {

    private UserService userService;
    public forgotPassword(UserService userService) {
        this.userService = userService;
    }
//----forgot password model---//

    @GetMapping("/forgotPassword")
    public String showForgetPassword(){
        return "forgotPassword";

    }

    @PostMapping("/resetPassword")
    public String forgotPassword(@RequestBody PasswordReset passwordReset,
                                 HttpServletRequest request){


        // check if user exit in db
        User user = userService.fingByEmail(passwordReset.getEmail());

        if(user != null){

            // Generate reset token

            String token = UUID.randomUUID().toString();
            userService.createPasswordResetToken(user, token);

            //create  url to reset the token
            String url = "";

            url = sendResetPasswordEmail(user, applicationUrl(request), token);

        }
        return "redirect:/url";
    }

    private String sendResetPasswordEmail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/savePassword" + token;

        log.info("click this link to reset your password: {}", url);
        return url;
    }

    // save password
    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                               @RequestBody PasswordReset passwordReset){
        String result = userService.validatePasswordReset(token);

        if(!result.equalsIgnoreCase("valid")){
            return "invalid token";
        }
        Optional<User> user = userService.getUserByPasswordToken(token);
        if(user.isPresent()){
            userService.changePassword(user.get(), passwordReset.getNewPassword());
            return "password change successfully ";
        }else {
            return "invalid";
        }

    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"
                + request.getServerName ()
                +":"
                +request.getServerPort ()
                +request.getContextPath ();
    }
}
