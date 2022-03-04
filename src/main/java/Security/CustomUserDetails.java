package Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* 게터 세터 생성자 꼭 만들어 줍시다.*/

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    @Getter
    private int ID;
    private String account;
    private String password;
    @Getter
    private String name;
    private String role;
    private List<UserClubRole> userClubRoles;
    private boolean enable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role));
        for(UserClubRole ucr : userClubRoles) {
            auth.add(new SimpleGrantedAuthority(
                    ucr.isHost() ?
                            "HOST_" + ucr.getClubName() :
                            "MEMBER_" + ucr.getClubName()
            ));
        }
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public int roleCheckOn(String clubName) {
        for(GrantedAuthority ga : this.getAuthorities()) {
            String auth = ga.getAuthority();
            if(auth.equals("MEMBER_" + clubName)) {
                return 1; // MEMBER
            }
            if(auth.equals("HOST_" + clubName)) {
                return 2; // HOST
            }
        }

        return 0; //OUTSIDER
    }

}
