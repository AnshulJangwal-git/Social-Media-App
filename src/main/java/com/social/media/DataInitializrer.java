package com.social.media;

import com.social.media.models.Post;
import com.social.media.models.SocialGroup;
import com.social.media.models.SocialProfile;
import com.social.media.models.SocialUser;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.SocialGroupRepository;
import com.social.media.repositories.SocialProfileRepository;
import com.social.media.repositories.SocialUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializrer {
    private final SocialUserRepository userRepository;
    private final SocialGroupRepository groupRepository;
    private final SocialProfileRepository socialProfileRepository;
    private PostRepository postRepository;

    public DataInitializrer(SocialUserRepository userRepository, SocialGroupRepository groupRepository,
                            SocialProfileRepository socialProfileRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.socialProfileRepository = socialProfileRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public CommandLineRunner initalizeData(){
        return args -> {
            SocialUser user1 = new SocialUser();
            SocialUser user2 = new SocialUser();
            SocialUser user3 = new SocialUser();

            // Save users to the database
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            //create some groups
            SocialGroup group1 = new SocialGroup();
            SocialGroup group2 = new SocialGroup();

            //Add users to the groups
            group1.getSocialUsers().add(user1);
            group2.getSocialUsers().add(user2);

            group2.getSocialUsers().add(user1);
            group2.getSocialUsers().add(user3);

            //save groups to the database
            groupRepository.save(group1);
            groupRepository.save(group2);

            //Add groups to the users
            user1.getGroups().add(group1);
            user2.getGroups().add(group1);
            user2.getGroups().add(group2);
            user3.getGroups().add(group2);

            //save users back to the database to update associations
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            //create some Posts
            Post post1 = new Post();
            Post post2 = new Post();
            Post post3 = new Post();

            //set users in the posts

            post1.setSocialUser(user1);
            post2.setSocialUser(user2);
            post3.setSocialUser(user3);

            // Save posts to the database (assuming you have a PostRepository)
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);

            // Create some social profiles
            SocialProfile profile1 = new SocialProfile();
            SocialProfile profile2 = new SocialProfile();
            SocialProfile profile3 = new SocialProfile();

            // Associate profiles with users
            profile1.setUser(user1);
            profile2.setUser(user2);
            profile3.setUser(user3);

            // Save profiles to the database (assuming you have a SocialProfileRepository)
            socialProfileRepository.save(profile1);
            socialProfileRepository.save(profile2);
            socialProfileRepository.save(profile3);

            //FETCH TYPES
            System.out.println("FETCHING SOCIAL USER");
            userRepository.findById(1L);

        };
    }
}
