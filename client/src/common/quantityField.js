import React, { useState, useEffect } from 'react';

const QuantityField = ({ initialQuantity, min, max = 100, onChange }) => {
  const [quantity, setQuantity] = useState(initialQuantity);

  useEffect(() => {
    setQuantity(initialQuantity);
  }, [initialQuantity]);

  const handleChange = (e) => {
    const value = parseInt(e.target.value, 10);
    if (!isNaN(value) && value >= min && value <= max) {
      setQuantity(value);
      if (onChange) {
        onChange(value);
      }
    }
  };

  const handleIncrease = () => {
    if (quantity < max) {
      const newQuantity = quantity + 1;
      setQuantity(newQuantity);
      if (onChange) {
        onChange(newQuantity);
      }
    }
  };

  const handleDecrease = () => {
    if (quantity > min) {
      const newQuantity = quantity - 1;
      setQuantity(newQuantity);
      if (onChange) {
        onChange(newQuantity);
      }
    }
  };

  return (
    <div className="quantity-field">
      <button type="button" onClick={handleDecrease} disabled={quantity <= min} className="quantity-field-btn">
        -
      </button>
      <input
        type="number"
        value={quantity}
        onChange={handleChange}
        min={min}
        max={max}
      />
      <button type="button" onClick={handleIncrease} disabled={quantity >= max} className="quantity-field-btn">
        +
      </button>
    </div>
  );
};

export default QuantityField;