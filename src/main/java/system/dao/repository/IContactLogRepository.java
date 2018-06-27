package system.dao.repository;

import org.springframework.data.repository.CrudRepository;
import system.dao.entity.ContactLogEntity;

public interface IContactLogRepository extends CrudRepository<ContactLogEntity,Integer>{

}
