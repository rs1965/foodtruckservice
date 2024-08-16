import React, { useState } from 'react'
import { Button, Col, Form, Row } from 'react-bootstrap'
import validateFields from '../common/formValidations'
import CurrencyInput from 'react-currency-input-field';
function Additems() {
    const initialState = {
        foodItem: '',
        price: '',
        image: '',
        description: ''
    }
    const [data, setData] = useState(initialState);
    const [errors, setErrors] = useState({});
    const handleCurrency = (value) => {
        const event = {
            target: {
                name: 'price',
                value: value
            }
        }
        changeHandle(event)
    }
    const changeHandle = (e) => {
        const { name, value, type, checked } = e.target;
        setData({
            ...data,
            [name]: value
        })
        const fieldErrors = validateFields([
            { name, value, required: true }
        ], 'onchange')

        setErrors({
            ...errors,
            [name]: fieldErrors[name]
        })
    }
    const handleSave = (e) => {
        e.preventDefault();
        const fieldsToValidate = [
            { name: 'foodItem', value: data.foodItem, required: true },
            { name: 'price', value: data.price, required: true },
            { name: 'image', value: data.image, required: true },
            { name: 'description', value: data.description, required: true },
        ];

        const validationErrors = validateFields(fieldsToValidate, 'onSave');
        console.log(validationErrors)
        setErrors(validationErrors);

        // Check if there are no errors before submitting
        const hasErrors = Object.values(validationErrors).some(error => error !== "");
        if (!hasErrors) {
            // console.log('Form Submitted', formValues);
            // Handle the form submission logic here
        }
    };
    const handleReset = () => {
        const fieldsToValidate = [
            { name: 'foodItem', value: '', required: true },
            { name: 'price', value: '', required: true },
            { name: 'image', value: '', required: false },
            { name: 'description', value: '', required: true },
        ];

        const validationErrors = validateFields(fieldsToValidate, 'reset');
        setErrors(validationErrors);
        setData(initialState);
    };
    return (
        <div className='item-main'>
            <div>

            </div>
            <Row className='form-group'>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}><label>Food Item ID:<span className="required-asterisk">*</span></label></Col>
                    <Col xs={2} md={2} sm={6} lg={2}>
                        <input className='customTextField' type='text' disabled value={'example'} placeholder="name@example.com" />
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}>
                        <label>Food Item :<span className="required-asterisk">*</span></label>
                    </Col>
                    <Col xs={2} md={2} sm={6} lg={2}>
                        <input className='customTextField' type='text' placeholder="Enter Item Name" name='foodItem' value={data.foodItem}
                            onChange={(e) => changeHandle(e)} />
                        {errors.foodItem !== '' && <div className='text-error-display'>{errors.foodItem}</div>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}>
                        <label>Price :<span className="required-asterisk">*</span></label>
                    </Col>
                    <Col xs={2} md={2} sm={6} lg={2}>
                        {/* <input className='customTextField' type='number' placeholder="Enter Price" name='price' value={data.price}
                            onChange={(e => changeHandle(e))} /> */}
                        <CurrencyInput
                            id="price"
                            name="price"
                            placeholder="Enter Price"
                            className='customTextField'
                            prefix='$'
                            defaultValue={data.price}
                            decimalsLimit={2}
                            onValueChange={(value) => handleCurrency(value)}
                        />
                        {errors.price !== '' && <div className='text-error-display'>{errors.price}</div>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={11} md={4} sm={6} lg={3}>
                        <label>Description :<span className="required-asterisk">*</span></label>
                    </Col>
                    <Col xs={2} md={2} sm={6} lg={2}>
                        <textarea className='customTextField textareawidth' rows={5} name='description' value={data.description}
                            placeholder="Enter Description" onChange={(e) => changeHandle(e)} />
                        {errors.description !== '' && <div className='text-error-display'>{errors.description}</div>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col xs={6} md={4} sm={6} lg={3}>
                        <label>Image :<span className="required-asterisk">*</span></label>
                    </Col>
                    <Col xs={2} md={2} sm={6} lg={2}>
                        <input className='customTextField' type='file' accept='image/*' name='image' value={data.image}
                            onChange={(e) => changeHandle(e)} />
                        {errors.image !== '' && <div className='text-error-display'>{errors.image}</div>}
                    </Col>
                </Row>
                <Row className='item-Fields'>
                    <Col></Col>
                    <Col>
                        <div className='itemButtons'>
                            <Button variant="secondary" onClick={handleReset}>Clear</Button>
                            <Button variant='success' onClick={handleSave}>Save</Button>
                        </div>
                    </Col>
                </Row>
            </Row>

        </div>
    )
}

export default Additems
