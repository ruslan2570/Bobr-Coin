package ruslan2570.bobrcoin.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ruslan2570.bobrcoin.entity.AchievementEntity;
import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.BobrTypeEntity;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.AchievementRepo;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.BobrTypeRepo;
import ruslan2570.bobrcoin.repo.UserRepo;

@Service
public class GameService {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    @Autowired
    UserRepo userRepo;

    @Autowired
    BobrTypeRepo bobrTypeRepo;

    @Autowired
    BobrRepo bobrRepo;

    @Autowired
    RandomNameService randomNameService;

    @Autowired
    AchievementService achievementService;

    @Autowired
    AchievementRepo achievementRepo;

    public ResponseEntity<?> buy(long bobrTypeId, Principal principal) {
        UserEntity user;
        BobrTypeEntity bobrType;
        if (principal != null && principal.getName() != null) {

            user = userRepo.findUserByLogin(principal.getName());

            if (user == null) {
                return new ResponseEntity<>("Неавторизованный запрос", HttpStatus.UNAUTHORIZED);
            }

            Optional<BobrTypeEntity> bobrTypeOpt = bobrTypeRepo.findById(bobrTypeId);

            if (!bobrTypeOpt.isPresent()) {
                return new ResponseEntity<>("Бобр не найден", HttpStatus.NOT_FOUND);
            }

            bobrType = bobrTypeOpt.get();

            BigDecimal balance = user.getBcAmount();

            if (balance.compareTo(bobrType.getPrice()) == -1) {
                return new ResponseEntity<>("Недостаточно средств", HttpStatus.PAYMENT_REQUIRED);
            }

            BobrEntity bobr = new BobrEntity(0, randomNameService.generateRandomFullName(), bobrType,
                    bobrType.getLifetime(), new BigDecimal(0), user);

            bobrRepo.save(bobr);
            System.out.println(bobr.getId());
            balance = balance.subtract(bobrType.getPrice());
            user.setBcAmount(balance);
            userRepo.save(user);

            if (user.getBobrs().size() == 1) {
                LOG.info("Первый нах");
                AchievementEntity achievement = achievementRepo.findByName("Novus Bobr Seclorum");
                achievementService.grant(user, achievement);
            }

            int numMcdonaldsWorkers = 0;
            int numPoliceWorkers = 0;
            int numCoderWorders = 0;
            List<BobrEntity> bobrs = user.getBobrs();
            for (BobrEntity iBobr : bobrs) {
                String iBobrTypeName = iBobr.getBobrType().getName();

                LOG.info("his name is " + iBobrTypeName);

                switch (iBobrTypeName) {
                    case "Обыкновенный бобр":
                        numMcdonaldsWorkers++;
                        break;

                    case "Бобр полицейский":
                        numPoliceWorkers++;
                        break;

                    case "Бобр программист":
                        numCoderWorders++;
                        break;
                }
            }

            if (numMcdonaldsWorkers >= 500) {
                AchievementEntity achievement = achievementRepo.findByName("Свободная касса!");
                achievementService.grant(user, achievement);
            }

            if (numPoliceWorkers >= 1984) {
                AchievementEntity achievement = achievementRepo.findByName("Большой Брат следит за тобой");
                achievementService.grant(user, achievement);
            }

            if (numCoderWorders >= 10 && bobrType.getName().equals("Бобр полицейский")) {
                LOG.info("Ачивку в студию!");
                AchievementEntity achievement = achievementRepo.findByName("Мечтают ли андроиды об электробобриках?");
                achievementService.grant(user, achievement);
            }

            return new ResponseEntity<>(null, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Неавторизованный запрос", HttpStatus.UNAUTHORIZED);
        }
    }
}
