package ruslan2570.bobrcoin.repo;

import org.springframework.data.repository.CrudRepository;
import ruslan2570.bobrcoin.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

    public List<UserEntity> findUserById(Long id);

    public UserEntity findUserByLogin(String login);

    public UserEntity findUserByEmail(String email);

    public UserEntity findUserByPasswordRestore(UUID code);

    public UserEntity findUserByEmailConfirmation(UUID code);

    public boolean existsByEmail(String email);

    public boolean existsByLogin(String login);
}
