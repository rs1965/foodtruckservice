import { Route, Routes } from 'react-router-dom';
import SpinnerComponent from './common/spinner';
import Header from './component/header';
import logo from './logo.svg';
import Home from './component/home';
import { LinkedInCallback } from "react-linkedin-login-oauth2";
function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route path='/' element={<Home />} />
        <Route exact path="/linkedin" component={LinkedInCallback} />
      </Routes>
    </>
  );
}

export default App;
