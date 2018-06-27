package system.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.dao.entity.RoleEntity;
import system.dao.entity.UsersEntity;
import system.dao.repository.IRoleRepository;
import system.dao.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final IUserRepository iUserRepository;

    private final IRoleRepository iRoleRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository, IRoleRepository iRoleRepository) {
        this.iUserRepository = iUserRepository;
        this.iRoleRepository = iRoleRepository;
    }


    public List<UsersEntity> getAllUsersEntity(){
        return (List<UsersEntity>) iUserRepository.findAll();
    }

    public UsersEntity getUserByName(String name){
        return iUserRepository.findByLogin(name);
    }

    @Transactional
    public void saveUser(UsersEntity user){
        List<RoleEntity> list=new ArrayList<>();
        list.add(iRoleRepository.findOne(1));
        user.setRoles(list);
        iUserRepository.save(user);
    }

}
