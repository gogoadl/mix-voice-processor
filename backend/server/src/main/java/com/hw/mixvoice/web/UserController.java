package com.hw.mixvoice.web;

import com.hw.mixvoice.domain.user.User;
import com.hw.mixvoice.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipal;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;


}
