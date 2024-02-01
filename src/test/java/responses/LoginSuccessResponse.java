package responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuccessResponse {
    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private boolean isActive;


}
