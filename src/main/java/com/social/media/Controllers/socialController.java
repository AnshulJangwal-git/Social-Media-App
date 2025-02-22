package com.social.media.Controllers;

import com.social.media.Services.SocialService;
import com.social.media.models.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/media")
@RestController
public class socialController{
    @Autowired
    private SocialService socialService;

    @GetMapping("/social/users")
    public ResponseEntity<List<SocialUser>> getUsers(){
        return new ResponseEntity<>(socialService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/social/users")
    public ResponseEntity<SocialUser> createUser(@RequestBody SocialUser socialUser){
        return new ResponseEntity<>(socialService.saveUser(socialUser), HttpStatus.OK);
    }

}
