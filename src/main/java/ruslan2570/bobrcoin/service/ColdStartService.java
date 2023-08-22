package ruslan2570.bobrcoin.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruslan2570.bobrcoin.entity.AchievementEntity;
import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.BobrTypeEntity;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.AchievementRepo;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.BobrTypeRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColdStartService implements InitializingBean {

        @Autowired
        BobrTypeRepo bobrTypeRepo;

        @Autowired
        AchievementRepo achievementRepo;

        @Override
        public void afterPropertiesSet() {
                if (!checkBobrs())
                        setBobrs();
                if (!checkAchievements())
                        setAchievements();
        }

        public boolean checkBobrs() {
                if (bobrTypeRepo.count() == 6)
                        return true;
                else
                        return false;
        }

        public boolean checkAchievements() {
                if (achievementRepo.count() == 1)
                        return true;
                else
                        return false;
        }

        public void setBobrs() {
                List<BobrTypeEntity> bobrs = new ArrayList<>();

                bobrs.add(new BobrTypeEntity(
                                1,
                                "Обыкновенный бобр",
                                "Бобр и точка",
                                new BigDecimal("0.02"),
                                new BigDecimal("0.01"),
                                30,
                                null,
                                null,
                                "mcdonalds_worker.png"));

                bobrs.add(new BobrTypeEntity(
                                2,
                                "Бобр строитель",
                                "Плотины под ключ",
                                new BigDecimal("0.5"),
                                new BigDecimal("0.05"),
                                25,
                                null,
                                null,
                                "builder.png"));

                bobrs.add(new BobrTypeEntity(
                                3,
                                "Бобр полицейский",
                                "Граждане могут спать спокойно (или не могут?)",
                                new BigDecimal("1"),
                                new BigDecimal("0.1"),
                                20,
                                "police",
                                "+5 лет к продолжительности жизни за наличие 10 бобров данного вида",
                                "policeman.png"));

                bobrs.add(new BobrTypeEntity(
                                4,
                                "Бобр инвестор",
                                "Бобр с Бобр-Стрит",
                                new BigDecimal("10"),
                                new BigDecimal("1"),
                                45,
                                "invest",
                                "+0.05 BC к производительности каждого бобра",
                                "investor.png"));

                bobrs.add(new BobrTypeEntity(
                                5,
                                "Бобр юрист",
                                "",
                                new BigDecimal("30"),
                                new BigDecimal("5"),
                                45,
                                "legacy",
                                "Навык \"Наследие\": данный бобр оставляет после себя наследство 50 BC",
                                "lawyer.png"));

                bobrs.add(new BobrTypeEntity(
                                6,
                                "Бобр программист",
                                "Hello, Bobr!",
                                new BigDecimal("100"),
                                new BigDecimal("10"),
                                45,
                                null,
                                null,
                                "coder.png"));

                bobrTypeRepo.saveAll(bobrs);
        }

        public void setAchievements() {
                ArrayList<AchievementEntity> achievements = new ArrayList<>();

                achievements.add(
                                new AchievementEntity(
                                                1L,
                                                "Novus Bobr Seclorum",
                                                "Купить своего первого бобра",
                                                "achievements/ordo.png",
                                                new ArrayList<UserEntity>()));

                achievements.add(
                                new AchievementEntity(
                                                2L,
                                                "Свободная касса!",
                                                "Купить 500 обыкновенных бобров",
                                                "bobr/mcdonalds_worker.png",
                                                new ArrayList<UserEntity>()));

                achievements.add(
                                new AchievementEntity(
                                                3L,
                                                "Это что за покемон!?",
                                                "В логине содержится слово \"capybara\"",
                                                "achievements/capybara.png",
                                                new ArrayList<UserEntity>()));

                achievements.add(
                                new AchievementEntity(
                                                4L,
                                                "Большой Бобр следит за тобой",
                                                "Количество полицейский превысило 1984",
                                                "achievements/1984.png",
                                                new ArrayList<UserEntity>()));

                achievements.add(
                                new AchievementEntity(
                                                5L,
                                                "Предлагаю обнулить",
                                                "Начать игру заного",
                                                "achievements/obnulit.png",
                                                new ArrayList<UserEntity>()));

                achievements.add(
                                new AchievementEntity(
                                                6L,
                                                "Underbobr",
                                                "Потерять всё",
                                                "achievements/underbobr.png",
                                                new ArrayList<UserEntity>()));

                achievements.add(
                                new AchievementEntity(
                                                7L,
                                                "Вам посылка!!!",
                                                "Подтвердить электронную почту",
                                                "achievements/scream.png",
                                                new ArrayList<UserEntity>()));

                achievements.add(
                                new AchievementEntity(
                                                8L,
                                                "Бегущий по плотине",
                                                "Купить бобра-полицейского, имея 10 бобров-программистов",
                                                "achievements/bladerunner.png",
                                                new ArrayList<UserEntity>()));

                achievementRepo.saveAll(achievements);
        }
}
