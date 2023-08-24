package ruslan2570.bobrcoin.repo;

import org.springframework.data.repository.CrudRepository;

import ruslan2570.bobrcoin.entity.MetricsEntity;

public interface MetricsRepo extends CrudRepository<MetricsEntity, Long> {
    
    
}
