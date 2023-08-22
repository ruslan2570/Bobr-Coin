package ruslan2570.bobrcoin.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

        model.addAttribute("achievements", achievements);
    }

    public void grant(UserEntity user, AchievementEntity achievementEntity){

        if(user == null || achievementEntity == null){
            throw new NullPointerException();
        }

        List<AchievementEntity> userAchievements = user.getAchievements();

        if(userAchievements == null || userAchievements.isEmpty()){
            throw new  NullPointerException("userAchievements is null");
        }

        if(userAchievements.contains(achievementEntity)){
            return;
        }

        userAchievements.add(achievementEntity);
        userRepo.save(user);
    }
}
