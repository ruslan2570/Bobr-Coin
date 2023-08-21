package ruslan2570.bobrcoin.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ruslan2570.bobrcoin.entity.BobrEntity;
import ruslan2570.bobrcoin.entity.UserEntity;

public interface BobrRepo extends CrudRepository<BobrEntity, Long> {

}
