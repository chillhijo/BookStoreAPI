package payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestBody {
    private String userName;
    private String password;

    public AccountRequestBody(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String createAuthorizedUserRequestBody() {
        return  "{\"userName\": \"" + userName + "\", \"password\": \"" + password + "\"}";
    }



}
