import { Route, Routes } from 'react-router-dom';
import SpinnerComponent from './common/spinner';
import Header from './component/header';
import logo from './logo.svg';
import Home from './component/home';
import Additems from './component/additems';
import LinkedInCallback from './config/LinkedInCallback';
function App() {
  return (
    <div className="app-container">
      <Header />
      
      <div className="main-content-wrapper">
        <Routes>
          <Route exact path='/' element={<Home />} />
          <Route exact path='/addItems' element={<Additems />} />
          <Route exact path='/callback' element={<LinkedInCallback />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
