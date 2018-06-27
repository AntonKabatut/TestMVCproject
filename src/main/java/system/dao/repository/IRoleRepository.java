package system.dao.repository;

import org.springframework.data.repository.CrudRepository;
import system.dao.entity.RoleEntity;

public interface IRoleRepository extends CrudRepository<RoleEntity,Integer> {
}
