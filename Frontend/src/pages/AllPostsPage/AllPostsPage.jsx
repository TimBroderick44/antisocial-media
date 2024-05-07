import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import style from './AllPostsPage.module.scss';

const AllPostsPage = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/posts')  
            .then(response => response.json())
            .then(data => setPosts(data))
            .catch(error => console.error('Error fetching posts:', error));
    }, []);

    return (
      <>
      <h1 className={style.heading}>All Posts</h1>
        <div className={style.container}> 
            {posts.map(post => (
                <div className={style.post} key={post.id}>
                    <h3>{post.title}</h3>
                    <p>{post.content}</p>
                    <div className={style.links}>
                    <Link className={style.view} to={`/posts/${post.id}`}>View</Link>
                    <Link className={style.edit} to={`/posts/edit/${post.id}`}>Edit</Link>
                    <button className={style.delete} onClick={() => deletePost(post.id)}>Delete</button>
                </div>
                </div>
            ))}
        </div>
        </>
    );

    function deletePost(id) {
        fetch(`http://localhost:8080/posts/${id}`, { method: 'DELETE' })  
            .then(() => {
                setPosts(posts.filter(post => post.id !== id));
                alert('Post deleted successfully');
            })
            .catch(error => console.error('Error deleting post:', error));
    }
};

export default AllPostsPage;