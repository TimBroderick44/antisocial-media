import React from 'react';
import { useNavigate } from 'react-router-dom';
import BlogPostForm from '../../components/BlogPostForm/BlogPostForm.jsx';

const NewPostPage = () => {
    const navigate = useNavigate();

    const handleCreatePost = (postData) => {
        fetch('http://localhost:8080/posts', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(postData)
        })
            .then(response => response.json())
            .then(() => navigate('/posts'))
            .catch(error => console.error('Error creating post:', error));
    };

    return (
        <BlogPostForm 
            initialData={{}}
            onSubmit={handleCreatePost}
            formTitle="Create a New Post"
        />
    );
};

export default NewPostPage;
