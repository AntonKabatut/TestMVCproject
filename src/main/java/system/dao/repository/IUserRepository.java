package system.dao.repository;

import org.springframework.data.repository.CrudRepository;
import system.dao.entity.UsersEntity;

public interface IUserRepository extends CrudRepository<UsersEntity,Integer> {

    UsersEntity findByLogin(String login);

}
