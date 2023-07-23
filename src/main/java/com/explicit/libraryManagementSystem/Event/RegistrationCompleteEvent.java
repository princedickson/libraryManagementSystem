package com.explicit.libraryManagementSystem.Event;

import com.explicit.libraryManagementSystem.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User registration;
    private String applicationUrl;

    public RegistrationCompleteEvent(User registration, String applicationUrl) {
        super(registration);
        this.registration = registration;
        this.applicationUrl = applicationUrl;
    }

}
