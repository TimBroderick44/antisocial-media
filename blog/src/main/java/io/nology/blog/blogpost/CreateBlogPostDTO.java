package io.nology.blog.blogpost;

import jakarta.validation.constraints.NotBlank;

public class CreateBlogPostDTO {
    @NotBlank
    private String content;
    @NotBlank
    private String title;
    @NotBlank
    private String category;
    
    public String getContent() {
        return content;
    }
    public String getTitle() {
        return title;
    }
    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        return "CreateBlogPostDTO [content=" + content + ", title=" + title + ", category=" + category + "]";
    }
}
