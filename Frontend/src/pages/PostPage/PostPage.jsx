import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import style from './PostPage.module.scss';

const PostPage = () => {
    const { id } = useParams();
    const [post, setPost] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/posts/${id}`)  
            .then(response => response.json())
            .then(data => setPost(data))
            .catch(error => console.error('Error fetching post:', error));
    }, [id]);

    if (!post) return <div>Loading...</div>;

    return (
        <div className={style.container}>
            <h1 className={style.heading}>{post.title}</h1>
            <p className={style.p}>{post.content}</p>
            <strong className={style.category}>Category:</strong> {post.category}
            <p className={style.time}><strong>Created At:</strong> {new Date(post.createdAt).toLocaleDateString()}</p>
        </div>
    );
};

export default PostPage