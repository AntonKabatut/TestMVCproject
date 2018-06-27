package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import system.dao.entity.ContactEntity;
import system.dao.service.ContactLogService;

import java.util.List;

@Controller
public class ContactController {

    private final ContactLogService contactLogService;

    @Autowired
    public ContactController(ContactLogService contactLogService) {
        this.contactLogService = contactLogService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public boolean addContact(Model model){
        contactLogService.saveContact(new ContactEntity("test","12232323"));
        return true;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<ContactEntity> getAll(Model model){
        return contactLogService.getUsersContact();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addNewUser(Model model) {
        model.addAttribute("newContact", new ContactEntity());

        return "contact";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute("newContact") ContactEntity newContact,
                               BindingResult bindingResult, Model model) {
        contactLogService.saveContact(newContact);
        return "redirect:/new";
    }

}
