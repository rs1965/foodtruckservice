import React, { useState, useRef, useEffect } from 'react'
import { Button, Col, Form, Row, Toast, ToastContainer } from 'react-bootstrap'
import validateFields from '../common/formValidations'
import CurrencyInput from 'react-currency-input-field';
import { useDispatch, useSelector } from 'react-redux'
import { insertItemSave } from '../redux/actions/defaultAction';
import SpinnerComponent from '../common/spinner';
function Additems() {
    const initialState = {
        foodItem: '',
        price: '',
        image: '',
        description: ''
    }
    const [data, setData] = useState(initialState);
    const fileInputRef = useRef(null);
    const [errors, setErrors] = useState({});
    const locationState = useSelector((state) => state.defaultReducer);
    const dispatch = useDispatch();
    const [show, setShow] = useState(false);
    const [showMsg, setMsg] = useState('');
    const [showLoader, setShowLoader] = useState(false);
    const { insertItemSaveRes } = locationState;
    useEffect(() => {
        if (insertItemSaveRes?.statusCode === 200) {
            setShowLoader(false);
            setMsg(insertItemSaveRes?.data?.message)
            setShow(true)
        } else if (insertItemSaveRes?.statusCode !== 200) {
            setShowLoader(false);
            setMsg(insertItemSaveRes?.data?.message)
            setShow(true);
        }
    }, [insertItemSaveRes])
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
    const handleImageChange = (name, file) => {
        const reader = new FileReader();
        reader.onloadend = () => {
            setData({
                ...data,
                [name]: reader.result
            })
        };
        const fieldErrors = validateFields([
            { name, value: [reader.result], required: true }
        ], 'onchange')

        setErrors({
            ...errors,
            [name]: fieldErrors[name]
        })
        { file !== undefined && reader.readAsDataURL(file); }
    };
    const handleSave = (e) => {
        e.preventDefault();
        const fieldsToValidate = [
            { name: 'foodItem', value: data.foodItem, required: true },
            { name: 'price', value: data.price, required: true },
            { name: 'image', value: data.image, required: true },
            { name: 'description', value: data.description, required: true },
        ];

        const validationErrors = validateFields(fieldsToValidate, 'onSave');
        setErrors(validationErrors);

        // Check if there are no errors before submitting
        const hasErrors = Object.values(validationErrors).some(error => error !== "");
        if (!hasErrors) {
            const foodTruckId = () => {
                return '111xx2xxx3y'.replace(/[xy]/g, function (c) {
                    const r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                    return v.toString(16);
                });
            }
            const payload = {
                foodTruckId: foodTruckId().toUpperCase(),
                itemPrice: data.price,
                itemName: data.foodItem,
                itemDescription: data.description,
                itemImages: data.image
            }
            dispatch(insertItemSave(payload))
            setShowLoader(true)
        }
    };
    const handleReset = () => {
        const fieldsToValidate = [
            { name: 'foodItem', value: '', required: true },
            { name: 'price', value: '', required: true },
            { name: 'image', value: '', required: true },
            { name: 'description', value: '', required: true },
        ];

        const validationErrors = validateFields(fieldsToValidate, 'reset');
        setErrors(validationErrors);
        setData(initialState);
        if (fileInputRef.current) {
            fileInputRef.current.value = ''; // Reset the file input
        }
    };
    return (
        <>
            {showLoader && <SpinnerComponent />}
            <div className='item-main'>
                <Row className='form-group'>
                    {false && <Row className='item-Fields'>
                        <Col xs={6} md={4} sm={6} lg={3}><label>Food Item ID:<span className="required-asterisk">*</span></label></Col>
                        <Col xs={2} md={2} sm={6} lg={2}>
                            <input className='customTextField' type='text' disabled value={'example'} placeholder="name@example.com" />
                        </Col>
                    </Row>}
                    <Row className='item-Fields'>
                        <Col xs={6} md={4} sm={6} lg={3}>
                            <label>Food Item :<span className="required-asterisk">*</span></label>
                        </Col>
                        <Col xs={2} md={2} sm={6} lg={2}>
                            <input className='customTextField' type='text' autoComplete='off' placeholder="Enter Item Name" name='foodItem' value={data.foodItem}
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
                                autoComplete='off'
                                prefix='$'
                                value={data.price}
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
                                placeholder="Enter Description" autoComplete='off' onChange={(e) => changeHandle(e)} />
                            {errors.description !== '' && <div className='text-error-display'>{errors.description}</div>}
                        </Col>
                    </Row>
                    <Row className='item-Fields'>
                        <Col xs={6} md={4} sm={6} lg={3}>
                            <label>Image :<span className="required-asterisk">*</span></label>
                        </Col>
                        <Col xs={2} md={2} sm={6} lg={2}>
                            <input className='customTextField' type='file' accept='image/*' name='image' ref={fileInputRef}
                                onChange={(e) => handleImageChange(e.target.name, e.target.files[0])} />
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
            {show && showMsg && <ToastContainer position="top-end" className="p-3">
                <Toast onClose={() => setShow(false)} show={show} delay={2500} autohide>
                    <Toast.Header>
                        <strong className="me-auto">Message</strong>
                        {/* <small>Just now</small> */}
                    </Toast.Header>
                    <Toast.Body>{showMsg}</Toast.Body>
                </Toast>
            </ToastContainer>}
        </>
    )
}

export default Additems
