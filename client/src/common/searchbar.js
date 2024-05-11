import React, { useState } from 'react'

function SearchBar(props) {
    const { handleSearch, handleInputChange, text, placeholder } = props;
    const [searchText, setSearchText] = useState('');


    // Event handler for detecting when the Enter key is pressed
    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            handleSearch();
        }
    };
    return (
        <>
            <div className="search-container">
                {/* Search input field */}
                <input
                    type="text"
                    className="search-input"
                    placeholder={placeholder}
                    value={text}
                    onChange={(event) => handleInputChange(event)}
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
        </>
    )
}

export default SearchBar
