package system.dao.repository;

import org.springframework.data.repository.CrudRepository;
import system.dao.entity.ContactEntity;

public interface IContactRepository extends CrudRepository<ContactEntity,Integer> {

}
