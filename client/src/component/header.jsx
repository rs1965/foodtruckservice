import React from 'react';
import { Navbar, Nav, Dropdown } from 'react-bootstrap';
import Avatar from 'react-avatar'
import { GiFoodTruck } from "react-icons/gi";
import SearchBar from '../common/searchbar';
import { GoogleLoginProvider, FaceBookLoginProvider, LinkedInLoginProvider } from '../common/loginTypes';
function Header() {
    return (
        <div className="App">
            {/* Navbar */}
            <Navbar bg="light" expand="lg" className="navbar">
                <Navbar.Brand href="#" className="navbar-logo brand-title">
                    {/* Website Logo */}
                    {/* <img src="logo.png" alt="Food Truck" className="logo" /> */}
                    <GiFoodTruck size={50} />
                    <p>Food Truck</p>
                </Navbar.Brand>
                <Nav className="ml-auto">
                    <GoogleLoginProvider />
                    <FaceBookLoginProvider />
                    <LinkedInLoginProvider />
                </Nav>
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
