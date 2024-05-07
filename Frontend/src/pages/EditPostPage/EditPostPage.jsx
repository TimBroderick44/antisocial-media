import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import BlogPostForm from '../../components/BlogPostForm/BlogPostForm.jsx';

const EditPostPage = () => {
    const { id } = useParams();
    const [initialData, setInitialData] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`http://localhost:8080/posts/${id}`)
            .then(response => response.json())
            .then(data => {
                setInitialData({
                    title: data.title,
                    content: data.content,
                    categories: data.categories.map(cat => cat.name)
                });
            })
            .catch(error => console.error('Error fetching post:', error));
    }, [id]);

    const handleUpdatePost = (postData) => {
        fetch(`http://localhost:8080/posts/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(postData)
        })
            .then(response => response.json())
            .then(() => navigate(`/posts/${id}`))
            .catch(error => console.error('Error updating post:', error));
    };

    if (!initialData) return <div>Loading...</div>;

    return (
        <BlogPostForm
            initialData={initialData}
            onSubmit={handleUpdatePost}
            formTitle="Edit Post"
        />
    );
};

export default EditPostPage;
