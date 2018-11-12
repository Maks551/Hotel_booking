package com.example.hotel_booking;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

import static com.example.hotel_booking.util.UserTestData.USER;

public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(new AuthorizedUser(USER), null, USER.getRoles()));

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        }
    }
}
