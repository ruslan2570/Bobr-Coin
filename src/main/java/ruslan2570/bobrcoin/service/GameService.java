package ruslan2570.bobrcoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.UserRepo;

@Service
public class GameService {

    @Autowired
    UserRepo userRepo;

    public void loadUserInfo(Model model) {
        UserEntity user = userRepo.findUserByLogin((String) model.getAttribute("username"));

        model.addAttribute("user", user);
        model.addAttribute("numberOfBobrs", 12);


    }
}
