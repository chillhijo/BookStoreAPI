package payloads;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BooksBody {
    private String userId;
    private List<String> collectionOfIsbn;

    public BooksBody(){}
    public BooksBody(String userId, List<String> collectionOfIsbn) {
        this.userId = userId;
        this.collectionOfIsbn = collectionOfIsbn;
    }

    public String createBooksRequestBody() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
