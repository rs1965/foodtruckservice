import React from 'react'
import { Spinner } from 'react-bootstrap'
function SpinnerComponent({ spinnerstyle, contentstyle }) {
    return (
        <div className='sprinneroverlay d-flex flex-column align-items-center justify-content-center'>
            <Spinner style={spinnerstyle} animation='border' role='status'></Spinner>
            <p style={contentstyle}>Loading ...</p>
        </div>
    )
}

export default SpinnerComponent
