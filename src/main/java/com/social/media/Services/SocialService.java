package com.social.media.Services;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialUser;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SocialService {

    @Autowired
    private SocialUserRepository socialUserRepository;

    public List<SocialUser> getAllUsers(){
        List<SocialUser> savedUsers = socialUserRepository.findAll();
        return savedUsers;
    }

    public SocialUser saveUser(SocialUser socialUser){
        SocialUser createdUser = socialUserRepository.save(socialUser);
        return createdUser;
    }

    public SocialUser deleteUser(Long id) {
        SocialUser socialUser = socialUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
        socialUserRepository.delete(socialUser);
        return socialUser;
    }
}
