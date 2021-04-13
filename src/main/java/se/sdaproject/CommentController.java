package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Comments> createComment(@PathVariable Long articleId, @RequestBody Comments comments){
        Article article = articleRepository.findById(articleId).orElseThrow(ResoursNotFoundException::new);
        comments.setCommentOwner(article);
        commentRepository.save(comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comments> deleteComment(@PathVariable long id){
        Comments comments=commentRepository.findById(id).orElseThrow(ResoursNotFoundException::new);
        commentRepository.delete(comments);
        return ResponseEntity.ok(comments);
    }


    @PutMapping("comments/{id}")
    public ResponseEntity<Comments> updateComment(@PathVariable long id, @RequestBody Comments updatedComment) {
        Comments comments=commentRepository
                .findById(id)
                .orElseThrow(ResoursNotFoundException::new);
        updatedComment.setId(id);
        commentRepository.save(updatedComment);
        return  ResponseEntity.ok(updatedComment);


    }
}
