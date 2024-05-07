package io.nology.blog.blogpost;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.blog.exceptions.NotFoundException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/posts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @PostMapping()
    public ResponseEntity<BlogPost> createPost(@Valid @RequestBody CreateBlogPostDTO data) {
        BlogPost createdPost = this.blogPostService.createPost(data);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<BlogPost>> findAllPosts() {
        List<BlogPost> allPosts = this.blogPostService.findAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> findPostById(@PathVariable Long id) throws NotFoundException {
        Optional<BlogPost> maybePost = this.blogPostService.findById(id);
        BlogPost foundPost = maybePost.orElseThrow(() -> new NotFoundException(BlogPost.class, id));
        return new ResponseEntity<>(foundPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePostById(@PathVariable Long id) throws NotFoundException {
        boolean isDeleted = this.blogPostService.deleteById(id);
        if (!isDeleted) {
            throw new NotFoundException(BlogPost.class, id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id,
            @RequestBody UpdateBlogPostDTO data)
            throws NotFoundException {
        BlogPost updatedPost = blogPostService.updatePost(id, data);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }
}
