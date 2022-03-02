package Dto.User;

import Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
public class UserUpdateReq {
    private int ID;
    private String password;
    private String name;
    private String profile;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                this.ID,
                null,
                "{bcrypt}"+passwordEncoder.encode(this.password),
                name,
                profile,
                null,
                null,
                null
        );
    }
}
