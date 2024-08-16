import React from 'react'
import { Button, Col, Form, Row } from 'react-bootstrap'

function Additems() {
    return (
        <div className='item-main'>
            <div>
                
            </div>
            <Row className='form-group'>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}><label>Food Item ID:<span className="required-asterisk">*</span></label></Col>
                    <Col xs={2} md={2} sm={6} lg={2}><input className='customTextField' type='text' disabled value={'example'} placeholder="name@example.com" />
                        {false && <p className='text-error-display'>Hii</p>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}><label>Food Item :<span className="required-asterisk">*</span></label></Col>
                    <Col xs={2} md={2} sm={6} lg={2}><input className='customTextField' type='text' placeholder="name@example.com" />
                        {false && <p className='text-error-display'>Hii</p>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}><label>Price :<span className="required-asterisk">*</span></label></Col>
                    <Col xs={2} md={2} sm={6} lg={2}><input className='customTextField' type='number' placeholder="name@example.com" />
                        {false && <p className='text-error-display'>Hii</p>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}><label>Image :<span className="required-asterisk">*</span></label></Col>
                    <Col xs={2} md={2} sm={6} lg={2}><input className='customTextField' type='file' accept='image/*' placeholder="name@example.com" />
                        {false && <p className='text-error-display'>Hii</p>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={11} md={4} sm={6} lg={3}><label>Description :<span className="required-asterisk">*</span></label></Col>
                    <Col xs={2} md={2} sm={6} lg={2}><textarea className='customTextField textareawidth' rows={5} placeholder="name@example.com" />
                        {false && <p className='text-error-display'>Hii</p>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <div className='itemButtons'>
                        <Button variant="secondary">Clear</Button>
                        <Button>Save</Button>
                    </div>
                </Row>
            </Row>

        </div>
    )
}

export default Additems
