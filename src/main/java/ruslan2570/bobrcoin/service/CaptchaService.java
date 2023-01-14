package ruslan2570.bobrcoin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {


    private final RestTemplate restTemplate;

    public CaptchaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Value("${yandex.smart-captcha.url}")
    private String url;
    @Value("${yandex.smart-captcha.secret}")
    private String secret;

    public boolean validate(String smartToken, String ipAddr, Model model){

        StringBuilder urlSb = new StringBuilder(url);
        urlSb.append("?secret=" + secret);
        urlSb.append("&token=" + smartToken);
        urlSb.append("&ip=" + ipAddr);

        JsonNode status = null;
        JsonNode message = null;
        try{
            ResponseEntity<String> response
                    = restTemplate.getForEntity(urlSb.toString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            status = root.path("status");
            message = root.path("message");
        } catch (RestClientException | JsonProcessingException e){
            e.printStackTrace();
            model.addAttribute("message", "ошибка при обработке ответа от сервиса капчи");
            return false;
        }

        if(status == null || message == null){
            model.addAttribute("message", "ошибка при обработке ответа от сервиса капчи");
        }

        if(status.textValue().equals("ok")){
            return true;
        }
        else if(status.textValue().equals("failed")) {
            model.addAttribute("message", message.textValue());
            return false;
        } else{
            model.addAttribute("message", "Неизвестно");
            return false;
        }
    }

}
