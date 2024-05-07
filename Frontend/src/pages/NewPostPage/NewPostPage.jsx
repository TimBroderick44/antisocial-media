import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import style from './NewPostPage.module.scss';

const NewPostPage = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [category, setCategory] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        const postData = { title, content, category };

        fetch('http://localhost:8080/posts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(postData)
        })
            .then(response => response.json())
            .then(() => {
                navigate('/posts');
            })
            .catch(error => console.error('Error creating post:', error));
    };

    return (
        <form className={style.container} onSubmit={handleSubmit}>
            <h1>Create a New Post</h1>
            <div className={style.form}>
            <label>
                Title:
                <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required />
            </label>
            <label>
                Content:
                <textarea value={content} onChange={(e) => setContent(e.target.value)} required />
            </label>
            <label>
                Category:
                <input type="text" value={category} onChange={(e) => setCategory(e.target.value)} required />
            </label>
            </div>
            <button className={style.submit} type="submit">Submit</button>
        </form>
    );
};

export default NewPostPage;