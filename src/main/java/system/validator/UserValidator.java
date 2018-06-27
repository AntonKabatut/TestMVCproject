package system.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import system.dao.entity.UsersEntity;
import system.dao.service.UserService;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsersEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UsersEntity user = (UsersEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (user.getLogin().length() < 4 || user.getLogin().length() > 32) {
            errors.rejectValue("login","Size.userForm.username");
        }

        if (userService.getUserByName(user.getLogin()) != null) {
            errors.rejectValue("login", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}