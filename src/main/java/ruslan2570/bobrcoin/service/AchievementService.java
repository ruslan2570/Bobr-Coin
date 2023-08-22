package ruslan2570.bobrcoin.service;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import ruslan2570.bobrcoin.entity.AchievementEntity;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.AchievementRepo;
import ruslan2570.bobrcoin.repo.UserRepo;

@Service
public class AchievementService {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Autowired
    UserRepo userRepo;

    @Autowired
    AchievementRepo achievementRepo;

    public void achievements(Principal principal, Model model){

        if(principal == null || principal.getName() == null){
            new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        UserEntity user = userRepo.findUserByLogin(principal.getName());

        if(user == null){
            new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<AchievementEntity> achievements = achievementRepo.findAll();

        for (AchievementEntity achievement : achievements) {
            if(!achievement.getUsers().contains(user)){
                achievement.setImagePath(null);
                String description = achievement.getDescription();

                String newDescription = description.replaceAll("[a-zA-Zа-яА-Я0-9ё]", "?");

                achievement.setDescription(newDescription);
            }
        }

        model.addAttribute("achievements", achievements);
    }

    public void grant(UserEntity user, AchievementEntity achievementEntity){

        if(user == null || achievementEntity == null){
            throw new NullPointerException();
        }

        List<AchievementEntity> userAchievements = user.getAchievements();

        LOG.info("grant entry");
        

        if(userAchievements.contains(achievementEntity)){
            LOG.info("achivka contains already");
            return;
        }

        LOG.info("granted");
        userAchievements.add(achievementEntity);
        user.setAchievements(userAchievements);
        userRepo.save(user);
    }
}
