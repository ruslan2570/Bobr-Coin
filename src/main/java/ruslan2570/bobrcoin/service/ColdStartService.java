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
                                60,
                                null,
                                null,
                                "builder.png"));

                bobrs.add(new BobrTypeEntity(
                                3,
                                "Бобр полицейский",
                                "Граждане могут спать спокойно (или не могут?)",
                                new BigDecimal("5"),
                                new BigDecimal("0.1"),
                                90,
                                "police",
                                "+n минут к продолжительности жизни новых бобров за наличие n бобров данного вида",
                                "policeman.png"));

                bobrs.add(new BobrTypeEntity(
                                4,
                                "Бобр инвестор",
                                "Бобр с Бобр-Стрит",
                                new BigDecimal("50"),
                                new BigDecimal("1"),
                                180,
                                "",
                                "",
                                "investor.png"));

                bobrs.add(new BobrTypeEntity(
                                5,
                                "Бобр юрист",
                                "",
                                new BigDecimal("80"),
                                new BigDecimal("5"),
                                250,
                                "heritage",
                                "Навык \"Наследство\": данный бобр оставляет после себя наследство 50 BC",
                                "lawyer.png"));

                bobrs.add(new BobrTypeEntity(
                                6,
                                "Бобр программист",
                                "Hello, Bobr!",
                                new BigDecimal("100"),
                                new BigDecimal("10"),
                                200,
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
                                                "Мечтают ли андроиды об электробобриках?",
                                                "Купить бобра полицейского, имея 100 бобров программистов",
                                                "achievements/bladerunner.png",
                                                new ArrayList<UserEntity>()));

                achievementRepo.saveAll(achievements);
        }
}