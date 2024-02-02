package responses;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import payloads.Book;
import java.util.List;

@Getter
@Setter
public class CreateUserResponse {

    private String userID;
    private String username;
    private List<Book> books;

    public CreateUserResponse(String username, List<Book> books) {
        this.username = username;
        this.books = books;
    }

    public void assertBookListEmpty() {
        Assert.assertTrue(books == null || books.isEmpty(), "The book list is not empty as expected");
    }

    public void assertUsernameSameAs(String testUsername) {
        Assert.assertTrue(username != null && username.equals(testUsername),
                "The username in the response is not the same as the expected username");
    }
}
