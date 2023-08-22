package ruslan2570.bobrcoin.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ruslan2570.bobrcoin.repo.AchievementRepo;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.UserRepo;

@Controller
@RequestMapping("/db")
public class DBController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AchievementRepo achievementRepo;

    @Autowired
    BobrRepo bobrRepo;

    @GetMapping("/users")
    public ResponseEntity<?> users() {
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/achvs")
    public ResponseEntity<?> achvs() {
        return new ResponseEntity<>(achievementRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/bobrs")
    public ResponseEntity<?> bobrs() {
        return new ResponseEntity<>(bobrRepo.findAll(), HttpStatus.OK);
    }
}
