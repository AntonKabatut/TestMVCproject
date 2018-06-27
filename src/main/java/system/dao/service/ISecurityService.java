package system.dao.service;

public interface ISecurityService {

    String findLoggedInUserName();
    void autoLogin(String login, String password);

}
