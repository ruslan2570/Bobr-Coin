package ruslan2570.bobrcoin.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomNameService {
    
    private final List<String> surnames;
    private final List<String> names;
    private final Random random;

    @Autowired
    public RandomNameService(List<String> surnames, List<String> names) {
        this.surnames = surnames;
        this.names = names;
        this.random = new Random();
    }

    public String generateRandomFullName() {
        String randomSurname = surnames.get(random.nextInt(surnames.size()));
        String randomName = names.get(random.nextInt(names.size()));
        return randomName + " " + randomSurname;
    }
    
}
