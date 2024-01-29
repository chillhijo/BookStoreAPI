package responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateTokenResponses {
    private String token;
    private String expires;
    private String status;
    private String result;

    public GenerateTokenResponses(String token, String expires, String status, String result) {
        this.token = token;
        this.expires = expires;
        this.status = status;
        this.result = result;
    }

}
