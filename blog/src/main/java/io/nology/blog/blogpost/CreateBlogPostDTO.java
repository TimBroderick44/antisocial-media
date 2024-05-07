package io.nology.blog.blogpost;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class CreateBlogPostDTO {
    @NotBlank
    private String content;
    @NotBlank
    private String title;
    private List<String> categories;  
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "CreateBlogPostDTO [content=" + content + ", title=" + title + ", categories=" + categories + "]";
    }
}
