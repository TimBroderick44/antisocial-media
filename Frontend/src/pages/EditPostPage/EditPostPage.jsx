import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import style from './EditPostPage.module.scss';

const EditPostPage = () => {
    const { id } = useParams();
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [category, setCategory] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`http://localhost:8080/posts/${id}`)
            .then(response => response.json())
            .then(post => {
                setTitle(post.title);
                setContent(post.content);
                setCategory(post.category);
            })
            .catch(error => console.error('Error fetching post:', error));
    }, [id]);

    const handleSubmit = (event) => {
        event.preventDefault();
        const postData = { title, content, category };

        fetch(`http://localhost:8080/posts/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(postData)
        })
            .then(response => response.json())
            .then(() => {
                navigate(`/posts/${id}`);
            })
            .catch(error => console.error('Error updating post:', error));
    };

    return (
        <form className={style.container} onSubmit={handleSubmit}>
            <h1>Edit Post</h1>
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
            <button className={style.submit} type="submit">Update Post</button>
        </form>
    );
};

export default EditPostPage;