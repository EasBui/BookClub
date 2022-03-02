package Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        try {
            CustomUserDetails user = userAuthDao.getUserByAccount(account); /* UserName이 보통 Id 임*/
            if(user==null) {
                throw new UsernameNotFoundException(account);
            }
            return user;
        } catch(RuntimeException e) {
            throw new UsernameNotFoundException(account);
        }
    }
}
