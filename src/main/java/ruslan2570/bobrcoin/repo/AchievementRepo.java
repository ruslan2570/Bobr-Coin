package ruslan2570.bobrcoin.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ruslan2570.bobrcoin.entity.AchievementEntity;

public interface AchievementRepo extends CrudRepository<AchievementEntity, Long> {

    // ArrayList<AchievementEntity> findAll();

    AchievementEntity findByName(String name);

}
