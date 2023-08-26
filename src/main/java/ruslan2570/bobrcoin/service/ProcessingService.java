package ruslan2570.bobrcoin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import ruslan2570.bobrcoin.entity.AchievementEntity;
import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.BobrTypeEntity;
import ruslan2570.bobrcoin.entity.MetricsEntity;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.AchievementRepo;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.MetricsRepo;
import ruslan2570.bobrcoin.repo.UserRepo;

@Service
@EnableScheduling
@EnableAsync
@Transactional
@Scope
public class ProcessingService {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    public final Lock lock = new ReentrantLock();

    @Autowired
    UserRepo userRepo;

    @Autowired
    BobrRepo bobrRepo;

    @Autowired
    AchievementRepo achievementRepo;

    @Autowired
    AchievementService achievementService;

    @Autowired
    MetricsRepo metricsRepo;

    @Scheduled(initialDelay = 3000, fixedRate = 60000)
    @Async
    public void processing() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ArrayList<BobrEntity> bobrsForDeleting = new ArrayList<>();

        BigDecimal totalAmount = new BigDecimal("0.00");
        BigDecimal totalIncome = new BigDecimal("0.00");

        Iterable<UserEntity> users = userRepo.findAll();

        for (UserEntity user : users) {

            if (user.getBcAmount().compareTo(new BigDecimal("0.00")) <= 0 && user.getBobrs().isEmpty()) {
                AchievementEntity achievement = achievementRepo.findByName("Underbobr");
                achievementService.grant(user, achievement);
                return;
            }

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

                if (bobr.getLifetime() <= 0) {
                    bobrsForDeleting.add(bobr);
                    if (bobrType.getBonus() != null && bobrType.getBonus().equals("heritage")) {
                        balance = balance.add(new BigDecimal("50"));
                    }
                } else {
                    bobrRepo.save(bobr);
                }
            }

            user.setBcAmount(balance);
            user.setBcPerMinute(income);
            userRepo.save(user);

            totalAmount = totalAmount.add(balance);
            totalIncome = totalIncome.add(income);
        }

        bobrRepo.deleteAll(bobrsForDeleting);

        MetricsEntity metrics = new MetricsEntity();
        metrics.setAmount(totalAmount);
        metrics.setIncomes(totalIncome);
        metrics.setNumBobrs(bobrRepo.count());
        metrics.setMetricsDate(new Date());

        stopWatch.stop();
        metrics.setProcessingTime(stopWatch.getTotalTimeSeconds());

        metricsRepo.save(metrics);

        // metricsRepo.deleteRecordsNotInTop25ByOrderByMetricsDateAsc();

        LOG.info("The processing have been completed");
    }
}
