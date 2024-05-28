import React, { useState, useEffect } from 'react'
import { Button, Card, Offcanvas } from 'react-bootstrap';
import QuantityField from './quantityField';
import sample from '../assets/images/sample.jpg'
function CanvasView(props) {
    const { view, data, handleCloseOffcanvas } = props;

    const initialItems = [
        { id: 1, price: 5, name: 'ice cream', quantity: 0 },
        { id: 2, price: 10, name: 'name of the iteam to be selected', quantity: 0 },
        { id: 3, price: 3, name: 'idly', quantity: 0 },
        { id: 4, price: 5, name: 'ice cream', quantity: 0 },
        { id: 5, price: 10, name: 'name of the iteam to be selected', quantity: 0 },
        { id: 6, price: 3, name: 'idly', quantity: 0 },
    ];
    const [items, setItems] = useState(initialItems);
    const [totalPrice, setTotalPrice] = useState(0);
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
                                        <img src={sample} className="img-fluid" alt="Product Image" />
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
                <Button variant="primary" disabled={totalPrice !== 0 ? false : true}>
                    Place Order
                </Button>
            </div>
        </Offcanvas>
    )
}

export default CanvasView
