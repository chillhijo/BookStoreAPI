package payloads;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BooksBody {
    private String userId;
    private List<CollectionOfIsbns> collectionOfIsbn;
    private String singleIsbn;

    public BooksBody(String userId, List<CollectionOfIsbns> collectionOfIsbn) {
        this.userId = userId;
        this.collectionOfIsbn = collectionOfIsbn;
    }

//    public BooksBody(String userId, String singleIsbn) {
//        this.userId = userId;
//        this.singleIsbn = singleIsbn;
//    }

    public String createBooksRequestBody() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
