package system.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import system.dao.entity.ContactEntity;
import system.dao.entity.UsersEntity;
import system.dao.repository.IContactRepository;

import java.util.List;
import java.util.Set;

@Service
public class ContactLogService {

    private final UserService userService;

    private final IContactRepository contactRepository;

    @Autowired
    public ContactLogService(UserService userService, IContactRepository contactRepository) {
        this.userService = userService;
        this.contactRepository = contactRepository;
    }

    @Transactional
    public void saveContact(ContactEntity contact){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsersEntity usersEntity=userService.getUserByName(user.getUsername());
        contactRepository.save(contact);
        usersEntity.getContacts().add(contact);
        userService.saveUser(usersEntity);
    }

    public List<ContactEntity> getAllContacts(){
        return (List<ContactEntity>) contactRepository.findAll();
    }

    @Transactional
    public List<ContactEntity> getUsersContact(){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsersEntity usersEntity=userService.getUserByName(user.getUsername());
        return usersEntity.getContacts();
    }

}
