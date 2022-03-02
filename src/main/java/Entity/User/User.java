package Entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {
    int ID;
    String account;
    String password;
    String name;
    String profile;
    Timestamp registerDate;
    String role;
    Boolean enable;
}
