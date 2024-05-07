package io.nology.blog.blogpost;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import io.nology.blog.exceptions.NotFoundException;

@Service
@Transactional
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public BlogPost createPost(CreateBlogPostDTO data) {
        BlogPost newPost = new BlogPost();
        newPost.setTitle(data.getTitle().trim());
        newPost.setContent(data.getContent().trim());
        newPost.setCreatedAt(new Date());

        Set<Category> categories = new HashSet<>();
        for (String categoryName : data.getCategories()) {
            Category category = categoryRepository.findByName(categoryName.trim().toLowerCase())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryName.trim().toLowerCase());
                    return categoryRepository.save(newCategory);
                });
            categories.add(category);
        }
        newPost.setCategories(categories);

        return blogPostRepository.save(newPost);
    }

    public List<BlogPost> findAllPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> findById(Long id) {
        return blogPostRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<BlogPost> maybePost = findById(id);
        if (maybePost.isEmpty()) {
            return false;
        }
        blogPostRepository.delete(maybePost.get());
        return true;
    }

    public BlogPost updatePost(Long id, UpdateBlogPostDTO data) throws NotFoundException {
        Optional<BlogPost> maybePost = blogPostRepository.findById(id);

        BlogPost existingPost = maybePost.map(post -> {
            Optional.ofNullable(data.getTitle()).ifPresent(title -> post.setTitle(title.trim()));
            Optional.ofNullable(data.getContent()).ifPresent(content -> post.setContent(content.trim()));

            if (data.getCategories() != null) {
                Set<Category> updatedCategories = data.getCategories().stream()
                    .map(name -> categoryRepository.findByName(name.trim().toLowerCase())
                        .orElseGet(() -> {
                            Category newCategory = new Category();
                            newCategory.setName(name.trim().toLowerCase());
                            return categoryRepository.save(newCategory);
                        }))
                    .collect(Collectors.toSet());
                post.setCategories(updatedCategories);
            }

            return post;
        }).orElseThrow(() -> new NotFoundException(BlogPost.class, id));

        return blogPostRepository.save(existingPost);
    }
}
