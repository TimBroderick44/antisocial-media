import React, { useState } from 'react';
import style from './BlogPostForm.module.scss';

const BlogPostForm = ({ initialData = {}, onSubmit, formTitle }) => {
    const [title, setTitle] = useState(initialData.title || '');
    const [content, setContent] = useState(initialData.content || '');
    const [categories, setCategories] = useState(initialData.categories ? initialData.categories.join(', ') : '');

    const handleSubmit = (event) => {
        event.preventDefault();
        const postData = {
            title,
            content,
            categories: categories.split(',').map(cat => cat.trim()).filter(cat => cat)
        };
        onSubmit(postData); 
    };

    return (
        <form className={style.container} onSubmit={handleSubmit}>
            <h1>{formTitle}</h1>
            <div className={style.form}>
                <label>
                    Title:
                    <input
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Content:
                    <textarea
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Categories:
                    <input
                        type="text"
                        value={categories}
                        onChange={(e) => setCategories(e.target.value)}
                        required
                        placeholder="Enter categories separated by commas"
                    />
                </label>
                <button className={style.submit} type="submit">Submit</button>
            </div>
        </form>
    );
};

export default BlogPostForm;
