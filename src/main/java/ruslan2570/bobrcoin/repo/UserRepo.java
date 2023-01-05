package ruslan2570.bobrcoin.repo;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import ruslan2570.bobrcoin.entity.UserEntity;

import java.util.List;

public interface UserRepo extends CrudRepository<Long, UserEntity> {

    public List<UserEntity> findUserById(Long id);

    public UserEntity findUserByLogin(String login);
}
