package ruslan2570.bobrcoin.service;

import lombok.val;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.BobrTypeEntity;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.BobrTypeRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColdStartService implements InitializingBean {

    @Autowired
    BobrTypeRepo bobrTypeRepo;

    @Override
    public void afterPropertiesSet(){
        if(!checkBobrs())
            setBobrs();
    }

    public boolean checkBobrs(){
        if(bobrTypeRepo.count() == 6)
            return true;
        else
            return false;
    }

    public void setBobrs(){
        List<BobrTypeEntity> bobrs = new ArrayList<>();

        bobrs.add(new BobrTypeEntity(
                1,
                "Обыкновенный бобр",
                "Бобр и точка",
                new BigDecimal("0.02"),
                new BigDecimal("0.01"),
                30,
                "",
                "",
                ""));

        bobrs.add(new BobrTypeEntity(
                2,
                "бобр строитель",
                "Плотины под ключ",
                new BigDecimal("0.5"),
                new BigDecimal("0.05"),
                25,
                "",
                "",
                ""));

        bobrs.add(new BobrTypeEntity(
                3,
                "Бобр полицейский",
                "Граждане могут спать спокойно (или не могут?)",
                new BigDecimal("1"),
                new BigDecimal("0.1"),
                20,
                "police",
                "+5% к продолжительности жизни за наличие данного вида бобров",
                ""));

        bobrs.add(new BobrTypeEntity(
                4,
                "Бобр инвестор",
                "Бобр с Бобр-Стрит",
                new BigDecimal("10"),
                new BigDecimal("1"),
                45,
                "",
                "",
                null));

        bobrs.add(new BobrTypeEntity(
                5,
                "Бобр юрист",
                "",
                new BigDecimal("30"),
                new BigDecimal("5"),
                45,
                "",
                "",
                null));

        bobrs.add(new BobrTypeEntity(
                6,
                "Бобр программист",
                "Hello, Bobr!",
                new BigDecimal("100"),
                new BigDecimal("10"),
                45,
                "",
                "",
                null));

        bobrTypeRepo.saveAll(bobrs);
    }
}
