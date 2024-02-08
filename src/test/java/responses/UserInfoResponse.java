package responses;

import lombok.Getter;
import lombok.Setter;
import payloads.Book;

import java.util.List;

@Getter
@Setter
public class UserInfoResponse {
    private String userId;
    private String username;
    private List<Book> books;

    public UserInfoResponse(String userId, String username, List<Book> books) {
        this.userId = userId;
        this.username = username;
        this.books = books;
    }
}
