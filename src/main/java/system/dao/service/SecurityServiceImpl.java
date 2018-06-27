package system.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements ISecurityService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public SecurityServiceImpl(@Qualifier("myManager") AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailService) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
    }

    @Override
    public String findLoggedInUserName() {

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String login, String password) {

        UserDetails userDetails=userDetailService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
}
