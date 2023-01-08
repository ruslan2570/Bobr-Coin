package ruslan2570.bobrcoin.repo;

import org.springframework.data.repository.CrudRepository;
import ruslan2570.bobrcoin.entity.UserEntity;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

    public List<UserEntity> findUserById(Long id);

    public UserEntity findUserByLogin(String login);

    public UserEntity findUserByEmail(String email);

    public boolean existsByEmail(String email);

    public boolean existsByLogin(String login);
}
