import React, { useState } from 'react';
import { Container, ButtonGroup, ToggleButton } from 'react-bootstrap';

const Header = () => {
    const [value, setValue] = useState('');

    const toggleButtons = [
        { name: 'Option 1', value: '1' },
        { name: 'Option 2', value: '2' },
        { name: 'Option 3', value: '3' }
    ];

    return (
        <header className="header">
            <Container>
                <h1>My Header</h1>
                <ButtonGroup>
                    {toggleButtons.map((btn, idx) => (
                        <ToggleButton
                            key={idx}
                            type="radio"
                            variant="outline-primary"
                            name="toggle"
                            value={btn.value}
                            checked={value === btn.value}
                            onChange={(e) => setValue(e.currentTarget.value)}
                        >
                            {btn.name}
                        </ToggleButton>
                    ))}
                </ButtonGroup>
            </Container>
        </header>
    );
};

export default Header;
