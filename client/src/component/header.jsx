import React from 'react';
import { Navbar, Nav, Dropdown } from 'react-bootstrap';
import Avatar from 'react-avatar'
import { GiFoodTruck } from "react-icons/gi";
import SearchBar from '../common/searchbar';
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
                    <Dropdown>
                        <Dropdown.Toggle as={Nav.Link} id="dropdown-basic" className="user-dropdown">
                            {/* User Avatar and Name */}
                            {/* <img src="avatar.png" alt="User Avatar" className="avatar" />
                            <span className="user-name">John Doe</span>
                             */}
                            <Avatar name='Rudra' size='40' round={true} className='avatar' />
                            <p>Rudra</p>
                        </Dropdown.Toggle>
                        <Dropdown.Menu className='drop-down-menu'>
                            <Dropdown.Item href="#">Settings</Dropdown.Item>
                            <Dropdown.Item href="#">Logout</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
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

            {/* Main Content */}
            <div className="main-content">
                {/* Your main content goes here */}
                <h1>Welcome to the website!</h1>
            </div>
        </div>
    );
}

export default Header;
