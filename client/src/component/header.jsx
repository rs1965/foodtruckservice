import React, { useState, useEffect } from 'react';
import { Navbar, Nav, Form, Button, ToastContainer, Toast } from 'react-bootstrap';
import Avatar from 'react-avatar'
import { GiFoodTruck } from "react-icons/gi";
import { useDispatch, useSelector } from 'react-redux'
import SearchBar from '../common/searchbar';
import { GoogleLoginProvider, FaceBookLoginProvider, LinkedInLogin } from '../common/loginTypes';
import { insertLoginUserSave, resetStatePart } from '../redux/actions/defaultAction';
import { Link } from 'react-router-dom';
import SpinnerComponent from '../common/spinner';
function Header() {
    const [userDetails, setUserDetails] = useState([])
    const [activeItem, setActiveItem] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [showLoader, setShowLoader] = useState(false);
    const [show, setShow] = useState(false);
    const [showMsg, setMsg] = useState('');
    const [isChecked, setIsChecked] = useState(false); // Initialize state
    const locationState = useSelector((state) => state.defaultReducer);
    const dispatch = useDispatch();
    let { insertLoginUserSaveRes } = locationState;
    useEffect(() => {
        checkExpiry()
    }, []);
    const checkExpiry = () => {
        // Retrieve token and expiration time from local storage (or wherever you store it)
        const tokenExp = localStorage.getItem('token_exp');
        if (tokenExp) {
            const currentTime = Math.floor(Date.now() / 1000);
            setIsLoggedIn(Number(currentTime) < Number(tokenExp));
        } else {
            setIsLoggedIn(false);
        }
    }
    const clearuserDetails = () => {
        setUserDetails([])
        localStorage.removeItem('token_exp');
        localStorage.removeItem('role');
        setIsChecked(false)
        checkExpiry()
    }
    useEffect(() => {
        if (insertLoginUserSaveRes?.statusCode === 200) {
            setShowLoader(false);
            setMsg(insertLoginUserSaveRes?.data?.message)
            localStorage.setItem('token_exp', userDetails?.expiryDateTime * 1000);
            localStorage.setItem('role', userDetails?.role);
            checkExpiry()
            setShow(true)
        } else if (insertLoginUserSaveRes?.statusCode !== 200) {
            setShowLoader(false);
            setMsg(insertLoginUserSaveRes?.data?.message)
            setShow(true);
        }
        dispatch(resetStatePart(insertLoginUserSaveRes))
    }, [insertLoginUserSaveRes])
    const handleClick = (id) => {
        setActiveItem(id);
    }
    const saveLoginDetails = (data) => {
        setShowLoader(true)
        setUserDetails(data)
        dispatch(insertLoginUserSave(data))
    }
    const swtichHandle = (event) => {
        setIsChecked(event.target.checked);
    }
    return (
        <>
            <div className="App">
                {showLoader && <SpinnerComponent />}
                {/* Navbar */}
                <Navbar bg="light" expand="lg" className="navbar">
                    <Navbar.Brand href="#" className="navbar-logo brand-title">
                        <GiFoodTruck size={50} />
                        <p>Food Truck</p>
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="ml-auto">
                            {!isLoggedIn && (
                                <>
                                    <div className="d-flex align-items-center">
                                        <span className="switch-label">Consumer</span>
                                        <Form.Check
                                            type="switch"
                                            id="custom-switch"
                                            checked={isChecked}
                                            onChange={swtichHandle}
                                            className="mx-2" // Add spacing between switch and labels
                                        />
                                        <span className="switch-label">Admin</span>
                                    </div>
                                    <div className={`login-button google-login-button ${isLoggedIn ? 'hidden' : 'visible'}`}>
                                        <GoogleLoginProvider userDetails={userDetails} setUserDetails={saveLoginDetails} isChecked={isChecked} />
                                    </div>
                                    <div className={`login-button facebook-login-button ${isLoggedIn ? 'hidden' : 'visible'}`}>
                                        <FaceBookLoginProvider userDetails={userDetails} setUserDetails={saveLoginDetails} isChecked={isChecked} />
                                    </div>
                                    {/* <div className={`login-button facebook-login-button ${isLoggedIn ? 'hidden' : 'visible'}`}>
                                    <LinkedInLogin userDetails={userDetails} setUserDetails={saveLoginDetails} isChecked={isChecked} />
                                </div> */}
                                </>
                            )}
                            {isLoggedIn && (
                                <div className={`login-button ${isLoggedIn ? 'visible' : 'hidden'}`}>
                                    <Button onClick={clearuserDetails}>LogOut</Button>
                                </div>
                            )}
                        </Nav>
                    </Navbar.Collapse>
                </Navbar>
                {/* Sidebar */}
                <div className="sidebar">
                    <ul>
                        {localStorage.getItem('role') === 'Admin' && isLoggedIn && <li className={activeItem === 'item' ? 'active' : ''} onClick={() => handleClick('item')}><Link to={'/addItems'}>Add Item</Link></li>}
                        <li className={activeItem === 'home' ? 'active' : ''} onClick={() => handleClick('home')}><Link to={'/'}>Edit</Link></li>
                        <li className={activeItem === 'about' ? 'active' : ''} onClick={() => handleClick('about')}><Link to={'/'}>About</Link></li>
                    </ul>
                </div>
            </div>
            {show && showMsg && <ToastContainer position="top-end" className="p-3">
                <Toast onClose={() => setShow(false)} show={show} delay={2500} autohide>
                    <Toast.Header>
                        <strong className="me-auto">Message</strong>
                        {/* <small>Just now</small> */}
                    </Toast.Header>
                    <Toast.Body>{showMsg}</Toast.Body>
                </Toast>
            </ToastContainer>}
        </>
    );
}

export default Header;
