package ruslan2570.bobrcoin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.BobrTypeEntity;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.AchievementRepo;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.UserRepo;

@Service
@EnableScheduling
@EnableAsync
public class ProcessingService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BobrRepo bobrRepo;

    @Autowired
    AchievementRepo achievementRepo;

    @Autowired
    AchievementService achievementService;


    @Scheduled(initialDelay = 30000, fixedRate = 60000)
    @Async
    public void processing(){
        ArrayList<BobrEntity> bobrsForDeleting = new ArrayList<>();


        Iterable<UserEntity> users = userRepo.findAll();

        for (UserEntity user : users) {
            // ArrayList<BobrEntity> bobrsForDeleting = new ArrayList<>();

            BigDecimal balance = user.getBcAmount();
            List<BobrEntity> bobrs = user.getBobrs();

            BigDecimal income = new BigDecimal("0.00");

            for (BobrEntity bobr : bobrs) {
                BobrTypeEntity bobrType = bobr.getBobrType();

                BigDecimal bobrIncome = bobrType.getBcPerMinute();
                bobr.setEarnedForLife(bobr.getEarnedForLife().add(bobrIncome));
                balance = balance.add(bobrIncome);
                income = income.add(bobrIncome);
                bobr.setLifetime(bobr.getLifetime() - 1);

                if(bobr.getLifetime() <= 0){
                    bobrsForDeleting.add(bobr);
                    if(bobrType.getBonus() != null && bobrType.getBonus().equals("heritage")){
                        balance = balance.add(new BigDecimal("50"));
                    }
                }

                bobrRepo.save(bobr);
            }

            user.setBcAmount(balance);
            user.setBcPerMinute(income);
            
        }

        bobrRepo.deleteAll(bobrsForDeleting);
        

    }
}
