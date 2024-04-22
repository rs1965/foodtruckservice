import React, { useState } from 'react'

function SearchBar() {
    const [searchText, setSearchText] = useState('');

    // Function to handle search actions
    const handleSearch = () => {
        // Log the search text to the console
        console.log(`Search text: ${searchText}`);
    };

    // Event handler for updating the search input state
    const handleInputChange = (event) => {
        setSearchText(event.target.value);
    };

    // Event handler for detecting when the Enter key is pressed
    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            handleSearch();
        }
    };
    return (
        <div className="search-container">
            {/* Search input field */}
            <input
                type="text"
                className="search-input"
                placeholder="City/State or Zipcode..."
                value={searchText}
                onChange={handleInputChange}
                onKeyDown={handleKeyDown}
            />
            {/* Search button */}
            <button
                className="search-button"
                onClick={handleSearch}
            >
                <span className="material-symbols-outlined">
                    location_searching
                </span>
            </button>
        </div>
    )
}

export default SearchBar
