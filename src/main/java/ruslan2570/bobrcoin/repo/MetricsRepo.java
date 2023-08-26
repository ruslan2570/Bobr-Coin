package ruslan2570.bobrcoin.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ruslan2570.bobrcoin.entity.MetricsEntity;

public interface MetricsRepo extends CrudRepository<MetricsEntity, Long> {
    
    List<MetricsEntity> findTop25ByOrderByMetricsDateAsc();

    void deleteRecordsNotInTop25ByOrderByMetricsDateAsc();
}
