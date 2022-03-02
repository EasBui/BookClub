package Dto.User;

import Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserProfileReq {
    private String name;
    private String profile;
    private LocalDateTime registerDate;

    public UserProfileReq(User user) {
        this.name = user.getName();
        this.profile = user.getProfile();
        this.registerDate = user.getRegisterDate().toLocalDateTime();
    }
}
