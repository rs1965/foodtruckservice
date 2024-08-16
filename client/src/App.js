import { Route, Routes } from 'react-router-dom';
import SpinnerComponent from './common/spinner';
import Header from './component/header';
import logo from './logo.svg';
import Home from './component/home';
import Additems from './component/additems';
function App() {
  return (
    <>
      <Header />

      <Routes>
        <Route exact path='/' element={<Home />} />
        <Route exact path='/addItems' element={<Additems />} />
      </Routes>
    </>
  );
}

export default App;
