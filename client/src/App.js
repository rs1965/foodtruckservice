import { Route, Routes } from 'react-router-dom';
import SpinnerComponent from './common/spinner';
import Header from './component/header';
import logo from './logo.svg';
import Home from './component/home';
function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route path='/' element={<Home />} />
      </Routes>
    </>
  );
}

export default App;
