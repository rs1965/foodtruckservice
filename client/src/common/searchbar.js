import React, { useState } from 'react'

function SearchBar(props) {
    const { handleSearch, handleInputChange, text, placeholder, errorMsg } = props;


    // Event handler for detecting when the Enter key is pressed
    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            handleSearch();
        }
    };
    return (
        <>
            <div style={{ display: 'flex', flexDirection: 'column' }}>
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
                {errorMsg !== '' && <p className='text-error-display'>{errorMsg}</p>}
            </div>
        </>
    )
}

export default SearchBar
