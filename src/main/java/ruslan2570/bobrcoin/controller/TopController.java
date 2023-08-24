package ruslan2570.bobrcoin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.UserRepo;
import ruslan2570.bobrcoin.service.TopService;

@Controller
@RequestMapping("/game/top")
public class TopController {
    
    @Autowired
    TopService topService;
    
    @GetMapping
    public String top(Model model){
        topService.top(model);
        return "top";
    }
}