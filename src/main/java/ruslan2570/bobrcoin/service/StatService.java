package ruslan2570.bobrcoin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import ruslan2570.bobrcoin.repo.MetricsRepo;

@Service
public class StatService {

    @Autowired
    MetricsRepo metricsRepo;

    public void stat(Model model){
        var metrics = metricsRepo.findAll();
        
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{}";

        try{
            json = "'" + objectMapper.writeValueAsString(metrics) + "'";
        } catch(Exception e){
            e.printStackTrace();
        }

        model.addAttribute("json", json);
    }

}
