package ruslan2570.bobrcoin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.UserRepo;
import ruslan2570.model.UserTop;

@Service
public class TopService {

    @Autowired
    UserRepo userRepo;

    public void top(Model model) {

        List<UserEntity> userEntitiesTopBobrs = StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .sorted((o1, o2) -> o2.getBobrs().size() - o1.getBobrs().size())
                .limit(10L)
                .toList();

        List<UserEntity> userEntitiesTopBalance = userRepo.findTop10ByOrderByBcAmountDesc();

        List<UserEntity> userEntitiesTopIncomes = userRepo.findTop10ByOrderByBcPerMinuteDesc();

        List<UserTop> usersTopBobrs = new ArrayList<>();
        List<UserTop> usersTopBalance = new ArrayList<>();
        List<UserTop> usersTopIncomes = new ArrayList<>();
        
        for (UserEntity user : userEntitiesTopBobrs) {
            UserTop userTop = new UserTop();
            userTop.setLogin(user.getLogin());
            userTop.setValue(Integer.toString(user.getBobrs().size()));
            usersTopBobrs.add(userTop);
        }

        for (UserEntity user : userEntitiesTopBalance) {
            UserTop userTop = new UserTop();
            userTop.setLogin(user.getLogin());
            userTop.setValue(user.getBcAmount().toString());
            usersTopBalance.add(userTop);
        }

        for (UserEntity user : userEntitiesTopIncomes) {
            UserTop userTop = new UserTop();
            userTop.setLogin(user.getLogin());
            userTop.setValue(user.getBcPerMinute().toString());
            usersTopIncomes.add(userTop);
        }

        model.addAttribute("usersTopBobrs", usersTopBobrs);
        model.addAttribute("usersTopBalance", usersTopBalance);
        model.addAttribute("usersTopIncomes", usersTopIncomes);
    }
}
