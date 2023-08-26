package ruslan2570.bobrcoin.service;


import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ruslan2570.bobrcoin.entity.AchievementEntity;
import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.AchievementRepo;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.UserRepo;

@Service
public class ProfileService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BobrRepo bobrRepo;

    @Autowired
    AchievementRepo achievementRepo;

    @Autowired
    AchievementService achievementService;

    public void profile(Principal principal, Model model) {
        UserEntity user = userRepo.findUserByLogin(principal.getName());

        model.addAttribute("user", user);

    }

    public void reset(Principal principal, RedirectAttributes redirectAttributes) {
        if (principal == null || principal.getName() == null) {
            redirectAttributes.addAttribute("message", "Неавторизованный запрос");
            return;
        }

        UserEntity user = userRepo.findUserByLogin(principal.getName());

        if (user == null) {
            redirectAttributes.addAttribute("message", "Пользователь не найден");
            return;
        }

        List<BobrEntity> bobrs = user.getBobrs();

        if (bobrs != null && !bobrs.isEmpty()) {
            bobrRepo.deleteAll(bobrs);
            user.setBobrs(null);
        }

        user.setBcAmount(new BigDecimal("0.02"));
        user.setBcPerMinute(new BigDecimal("0.00"));

        ArrayList<AchievementEntity> userAchievements = new ArrayList<>(user.getAchievements());

        ArrayList<AchievementEntity> newUserAchievements = new ArrayList<>(userAchievements.stream()
                .filter(x -> x.getName().equals("Это что за покемон!?") ||
                        x.getName().equals("Underbobr") ||
                        x.getName().equals("Мечтают ли андроиды об электробобриках?"))
                .toList());

        AchievementEntity achievement = achievementRepo.findByName("Предлагаю обнулить");
        newUserAchievements.add(achievement);

        user.setAchievements(newUserAchievements);
        userRepo.save(user);

        redirectAttributes.addAttribute("message", "Прогресс успешно сброшен");
    }
}
