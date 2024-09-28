import React, { useState, useEffect } from 'react'
import { Button, Card, Offcanvas } from 'react-bootstrap';
import QuantityField from './quantityField';
import sample from '../assets/images/sample.jpg'
function CanvasView(props) {
    const { view, handleCloseOffcanvas, items, setItems,setTotalPrice,totalPrice } = props;
    // const [totalPrice, setTotalPrice] = useState(0);
    useEffect(() => {
        getTotalPrice()
    }, [items])
    // Handler to update quantity for a specific item
    const handleQuantityChange = (id, newQuantity) => {
        setItems((prevItems) =>
            prevItems.map((item) =>
                item.id === id ? { ...item, quantity: newQuantity } : item
            )
        );
    };
    const getTotalPrice = () => {
        let price = 0;
        items.map((item) => {
            if (item?.quantity !== 0) {
                price += item.price * item.quantity
            }
        })
        setTotalPrice(price)
    }
    const handleImageChange = (id, file) => {
        const reader = new FileReader();
        reader.onloadend = () => {
            setItems(prevItems =>
                prevItems.map(item =>
                    item.id === id ? { ...item, itemImg: reader.result } : item
                )
            );
        };
        reader.readAsDataURL(file);
    };
    // Function to generate a UUID (for example purposes, using a simple mockup)
    function generateUUID(key) {
        if (key === 'foodtruckId') {
            return '1xx-2xxx-3y'.replace(/[xy]/g, function (c) {
                const r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                return v.toString(16);
            });
        }
        if (key === 'orderId') {
            return '0xx-2xxx-3y'.replace(/[xy]/g, function (c) {
                const r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                return v.toString(16);
            });
        }
        if (key === 'customerId') {
            return 'CUS2xxx-3y'.replace(/[xy]/g, function (c) {
                const r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                return v.toString(16);
            });
        }
    }
    // Filter items with quantity greater than 0
    const filteredItems = items.filter(item => item.quantity > 0);

    // Map to the desired format
    const resultItems = filteredItems.map(item => ({
        itemid: item.id,
        quantity: item.quantity,
        price: item.price
    }));

    const handleSubmitReq = (e) => {
        e.preventDefault();
        const payload = {
            foodtruckId: generateUUID('foodtruckId'),
            orderId: generateUUID('orderId'),
            customerId: generateUUID('customerId'),
            items: filteredItems.map(item => ({
                itemId: item.id,
                qty: item.quantity,
                itemPrice: item.price,
                itemImg: item.itemImg
            })),
            totalPrice: totalPrice
        };
        props.handleSubmitRequest(payload)
        // Construct the final payload
        // alert(JSON.stringify(payload));
        // setItems(initialItems);

    }
    return (
        <Offcanvas className='offcanvas-bgc' show={view} onHide={handleCloseOffcanvas} placement="end">
            <Offcanvas.Header closeButton>
                <Offcanvas.Title>Item Details</Offcanvas.Title>
            </Offcanvas.Header>
            <Offcanvas.Body>
                {
                    items.map((item) => (
                        <Card className='card-border' key={item?.id}>
                            <Card.Body>
                                <div className="row">
                                    <div className="col-md-4">
                                        <img src={sample} className="img-fluid" alt="Product Image" style={{ paddingBottom: '15px' }} />
                                        <input
                                            type="file"
                                            onChange={(e) => handleImageChange(item.id, e.target.files[0])}
                                        />
                                    </div>
                                    <div className="col-md-8">
                                        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                                            <h5 style={{ fontSize: 'larger', textWrap: 'balance' }}>{item?.name} - {`$${item?.price}`}</h5>
                                            <p>{`$${item?.price * item?.quantity}`}</p>
                                        </div>
                                        <div>
                                            {/* <label htmlFor="quantity">Quantity: </label> */}
                                            <QuantityField initialQuantity={0} min={0} max={10}
                                                onChange={(newQuantity) => handleQuantityChange(item.id, newQuantity)} />
                                        </div>
                                    </div>
                                </div>
                            </Card.Body>
                        </Card>
                    ))
                }
                <div className='price-details'>
                    <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                        <label className='price-details-label'>Total Price</label>
                        <label className='price-details-label'>{totalPrice}</label>
                    </div>
                </div>
            </Offcanvas.Body>
            <div className="offcanvas-footer p-3 border-top" style={{ cursor: `${totalPrice !== 0 ? 'pointer' : 'no-drop'}` }}>
                <Button variant="primary" disabled={totalPrice !== 0 ? false : true} onClick={handleSubmitReq}>
                    Place Order
                </Button>
            </div>
        </Offcanvas>
    )
}

export default CanvasView
