package responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class BooksListResponse {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private Date publish_date;
    private String publisher;
    private int pages;
    private String description;
    private String website;
}
