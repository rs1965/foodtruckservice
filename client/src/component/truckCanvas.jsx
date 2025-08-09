import React, { useState, useEffect } from "react";
import { Card, Button, Pagination, Container, Row, Col, Tooltip, OverlayTrigger } from "react-bootstrap";
import sample from '../assets/images/sample.jpg'

const TruckCanvas = (props) => {
    const { currentCards, handleCardClick } = props;
    
    // Function to extract zip code from address
    const extractZipCode = (address) => {
        if (!address) return 'N/A';
        const zipMatch = address.match(/\b\d{5}(-\d{4})?\b/);
        return zipMatch ? zipMatch[0] : 'N/A';
    };
    
    // Function to format address for display
    const formatAddress = (address) => {
        if (!address) return 'Address not available';
        // Limit address length for better display
        return address.length > 50 ? address.substring(0, 50) + '...' : address;
    };
    
    // Function to format schedule link
    const formatSchedule = (schedule) => {
        if (!schedule) return 'Schedule not available';
        if (schedule.startsWith('http')) {
            return (
                <a href={schedule} target="_blank" rel="noopener noreferrer" className="schedule-link">
                    View Schedule
                </a>
            );
        }
        return schedule;
    };
    
    return (
        <Container>
            <Row>
                {currentCards?.map((card, index) => (
                    <Col md={6} lg={4} key={index}>
                        <Card className="mb-3 card-main" onClick={() => handleCardClick(card)}>
                            <OverlayTrigger
                                placement="top"
                                overlay={
                                    <Tooltip id={`tooltip-${index}`}>
                                        {card?.applicant || 'Food Truck'}
                                    </Tooltip>
                                }
                            >
                                <span className="card-applicant applicantStyle">
                                    {card?.applicant || 'Food Truck'}
                                </span>
                            </OverlayTrigger>
                            
                            <div className="card-main-containt">
                                <Card.Img className="card-img-main" variant="top" src={sample} />
                                <Card.Body className="card-body-main">
                                    <div className="card-details">
                                        <Card.Text className="facilityStyle">
                                            <strong>Type:</strong> {card?.facilitytype || 'Food Truck'}
                                        </Card.Text>
                                        
                                        <Card.Text className="addressStyle">
                                            <strong>📍 Address:</strong> {formatAddress(card?.address)}
                                        </Card.Text>
                                        
                                        <Card.Text className="zipStyle">
                                            <strong>📮 Zip Code:</strong> {extractZipCode(card?.address)}
                                        </Card.Text>
                                        
                                        <Card.Text className="scheduleStyle">
                                            <strong>📅 Schedule:</strong> {formatSchedule(card?.schedule)}
                                        </Card.Text>
                                        
                                        <Card.Text className="locationdescriptionStyle">
                                            <strong>📝 Description:</strong> {card?.locationdescription || 'No description available'}
                                        </Card.Text>
                                        
                                        {card?.fooditems && (
                                            <Card.Text className="foodItemsStyle">
                                                <strong>🍔 Food Items:</strong> {card.fooditems.length > 50 ? card.fooditems.substring(0, 50) + '...' : card.fooditems}
                                            </Card.Text>
                                        )}
                                    </div>
                                </Card.Body>
                            </div>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
};

export default TruckCanvas;
