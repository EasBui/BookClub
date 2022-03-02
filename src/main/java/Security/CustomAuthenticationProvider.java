package Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String account = (String) authentication.getPrincipal();
        String pw = passwordEncoder.encode((String)authentication.getCredentials());

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(account);

        if(!passwordEncoder.matches(pw, user.getPassword())) {
            throw new BadCredentialsException(account);
        }

        if(!user.isEnabled()) {
            throw new BadCredentialsException(account);
        }

        return new UsernamePasswordAuthenticationToken(account, pw, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
