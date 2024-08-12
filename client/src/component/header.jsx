import React, { useState } from 'react';
import { Navbar, Nav, Dropdown, Button } from 'react-bootstrap';
import Avatar from 'react-avatar'
import { GiFoodTruck } from "react-icons/gi";
import SearchBar from '../common/searchbar';
import { GoogleLoginProvider, FaceBookLoginProvider, LinkedInLogin } from '../common/loginTypes';
function Header() {
    const [userDetails, setUserDetails] = useState([])

    const clearuserDetails = () => {
        setUserDetails([])
    }
    const isLoggedIn = userDetails.length !== 0;
    return (
        <div className="App">
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
                                <div className={`login-button google-login-button ${isLoggedIn ? 'hidden' : 'visible'}`}>
                                    <GoogleLoginProvider userDetails={userDetails} setUserDetails={setUserDetails} />
                                </div>
                                <div className={`login-button facebook-login-button ${isLoggedIn ? 'hidden' : 'visible'}`}>
                                    <FaceBookLoginProvider userDetails={userDetails} setUserDetails={setUserDetails} />
                                </div>
                                <div className={`login-button facebook-login-button ${isLoggedIn ? 'hidden' : 'visible'}`}>
                                    <LinkedInLogin userDetails={userDetails} setUserDetails={setUserDetails} />
                                </div>
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
                    <li><a href="#">View</a></li>
                    <li><a href="#">Edit</a></li>
                    <li><a href="#">About</a></li>
                </ul>
            </div>
        </div>
    );
}

export default Header;
