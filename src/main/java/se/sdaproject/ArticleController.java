package se.sdaproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class ArticleController {
    ArticleRepository articleRepository;
    @Autowired
    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }
    @GetMapping("/article")
    public List<Article> listAllArticles(){
        List<Article> articles= articleRepository.findAll();
        return articles;
    }
    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable long id){
        Article article = articleRepository.findById(id).orElseThrow(ResoursNotFoundException::new);
        return ResponseEntity.ok(article);
    }
    @PutMapping("article/{id}")
    public ResponseEntity<Article> updatePerson(@PathVariable long id, @RequestBody Article updatedArticle) {
        articleRepository.findById(id).orElseThrow(ResoursNotFoundException::new);
        updatedArticle.setId(id);
        Article article =articleRepository.save(updatedArticle);
        return  ResponseEntity.ok(article);
    }
    @PostMapping("/article")
    public ResponseEntity<Article> createPerson(@RequestBody Article article){
        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }
    @DeleteMapping("/article/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable long id){
        Article article=articleRepository.findById(id).orElseThrow(ResoursNotFoundException::new);
        articleRepository.delete(article);
        return ResponseEntity.ok(article);
    }
}