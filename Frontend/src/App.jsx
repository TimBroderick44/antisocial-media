import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./pages/LandingPage/LandingPage.jsx";
import AllPostsPage from "./pages/AllPostsPage/AllPostsPage.jsx";
import PostPage from "./pages/PostPage/PostPage.jsx";
import NewPostPage from "./pages/NewPostPage/NewPostPage.jsx";
import EditPostPage from "./pages/EditPostPage/EditPostPage.jsx";
import NavBar from "./components/Navbar/Navbar.jsx";
import StarsCanvas from "./components/Stars/Stars.jsx";
import Flexbox from "./containers/Flexbox/Flexbox.jsx";

function App() {
  return (
    <BrowserRouter>
    <StarsCanvas />
    <NavBar />
    <Flexbox>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/posts" element={<AllPostsPage />} />
        <Route path="/posts/:id" element={<PostPage />} />
        <Route path="/posts/new" element={<NewPostPage />} />
        <Route path="/posts/edit/:id" element={<EditPostPage />} />
      </Routes>
    </Flexbox>
    </BrowserRouter>
  ); 
}

export default App
