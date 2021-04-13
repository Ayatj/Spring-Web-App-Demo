package se.sdaproject;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String authorName;
    private String Body;
    public Article(String title, String authorName, String body) {
        this.title = title;
        this.authorName = authorName;
        Body = body;
    }
    public Article(){
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getBody() {
        return Body;
    }
    public void setBody(String body) {
        Body = body;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
