package ruslan2570.bobrcoin.service;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
            return;
        }

        UserEntity user = userRepo.findUserByLogin(principal.getName());

        if(user == null){
            return;
        }

        Iterable<AchievementEntity> achievements = achievementRepo.findAll();

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

        if(userAchievements.contains(achievementEntity)){
            return;
        }

        userAchievements.add(achievementEntity);
        user.setAchievements(userAchievements);
        userRepo.save(user);
    }
}
