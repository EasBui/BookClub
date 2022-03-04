package Dto.User;

import Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserProfileRes {
    private String name;
    private String profile;
    private LocalDateTime registerDate;

    public UserProfileRes(User user) {
        this.name = user.getName();
        this.profile = user.getProfile();
        this.registerDate = user.getRegisterDate().toLocalDateTime();
    }
}
