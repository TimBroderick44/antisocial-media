package io.nology.blog.blogpost;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import io.nology.blog.exceptions.NotFoundException;

@Service
@Transactional
public class BlogPostService {
    @Autowired
    private BlogPostRepository repo;

    public BlogPost createPost(CreateBlogPostDTO data) {
        BlogPost newPost = new BlogPost();
        newPost.setTitle(data.getTitle().trim());
        newPost.setContent(data.getContent().trim());
        newPost.setCategory(data.getCategory().trim().toLowerCase());
        newPost.setCreatedAt(new Date());
        return this.repo.save(newPost);
    }

    public List<BlogPost> findAllPosts() {
        return this.repo.findAll();
    }

    public Optional<BlogPost> findById(Long id) {
        return this.repo.findById(id);
    }

    public boolean deleteById(Long id) {
        // TODO Auto-generated method stub
        Optional<BlogPost> maybePost = this.findById(id);
        if (maybePost.isEmpty()) {
            return false;
        }
        this.repo.delete(maybePost.get());
        return true;
    }

    public BlogPost updatePost(Long id, UpdateBlogPostDTO data) throws NotFoundException {
        Optional<BlogPost> maybePost = this.repo.findById(id);
    
        BlogPost existingPost = maybePost.map(post -> {
            Optional.ofNullable(data.getTitle()).ifPresent(title -> post.setTitle(title.trim()));
            Optional.ofNullable(data.getContent()).ifPresent(content -> post.setContent(content.trim()));
            Optional.ofNullable(data.getCategory()).ifPresent(category -> post.setCategory(category.trim().toLowerCase()));
            return post;
        }).orElseThrow(() -> new NotFoundException(BlogPost.class, id));
    
        return this.repo.save(existingPost);
    }
}
