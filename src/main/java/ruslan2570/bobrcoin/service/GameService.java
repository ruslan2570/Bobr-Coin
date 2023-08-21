package ruslan2570.bobrcoin.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.BobrTypeEntity;
import ruslan2570.bobrcoin.entity.UserEntity;
import ruslan2570.bobrcoin.repo.BobrRepo;
import ruslan2570.bobrcoin.repo.BobrTypeRepo;
import ruslan2570.bobrcoin.repo.UserRepo;

@Service
public class GameService {
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    BobrTypeRepo bobrTypeRepo;

    @Autowired
    BobrRepo bobrRepo;

    @Autowired
    RandomNameService randomNameService;

    public ResponseEntity<?> buy(long bobrTypeId, Principal principal){
        UserEntity user;
        BobrTypeEntity bobrType;
        if(principal != null && principal.getName() != null){
            
            user = userRepo.findUserByLogin(principal.getName());

            if(user == null){
                return new ResponseEntity<>("Неавторизованный запрос", HttpStatus.UNAUTHORIZED);    
            }

            Optional<BobrTypeEntity> bobrTypeOpt = bobrTypeRepo.findById(bobrTypeId);

            if(!bobrTypeOpt.isPresent()){
                return new ResponseEntity<>("Бобр не найден", HttpStatus.NOT_FOUND);    
            }

            bobrType = bobrTypeOpt.get();

            BigDecimal balance = user.getBcAmount();

            if(balance.compareTo(bobrType.getPrice()) == -1){
                return new ResponseEntity<>("Недостаточно средств", HttpStatus.PAYMENT_REQUIRED);    
            }

            BobrEntity bobr = new BobrEntity(0, randomNameService.generateRandomFullName(), bobrType, bobrType.getLifetime(), new BigDecimal(0), user);

            bobrRepo.save(bobr);
            System.out.println(bobr.getId());
            balance = balance.subtract(bobrType.getPrice());
            user.setBcAmount(balance);
            userRepo.save(user);

            return new ResponseEntity<>(null, HttpStatus.CREATED);

        } else{
            return new ResponseEntity<>("Неавторизованный запрос", HttpStatus.UNAUTHORIZED);
        }
    }
}
