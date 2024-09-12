import React, { useState, useEffect } from "react";
import { Card, Button, Pagination, Container, Row, Col, Tooltip, OverlayTrigger } from "react-bootstrap";
import sample from '../assets/images/sample.jpg'

const TruckCanvas = (props) => {
    const { currentCards, handleCardClick } = props;
    return (
        <Container>
            <Row>
                {currentCards?.map((card, index) => (
                    <Col md={6} key={index}>
                        <Card className="mb-3 card-main" onClick={() => handleCardClick(card)}>
                            <OverlayTrigger
                                placement="top"
                                overlay={
                                    <Tooltip id={`tooltip-${index}`}>
                                        {card?.applicant}
                                    </Tooltip>
                                }
                            >
                                <span className="card-applicant applicantStyle">{card?.applicant}</span>
                            </OverlayTrigger>
                            {/* <span className="card-applicant">{card?.applicant}</span> */}
                            <div className="card-main-containt">
                                <Card.Img className="card-img-main" variant="top" src={sample} />
                                <Card.Body className="card-body-main">
                                    {/* <Card.Title>{card?.applicant}</Card.Title> */}
                                    <Card.Text className="facilityStyle applicantStyle">{card?.facilitytype}</Card.Text>
                                    <Card.Text className="addressStyle">{card?.address}</Card.Text>
                                    <Card.Text>{card?.schedule}</Card.Text>
                                    <Card.Text className="locationdescriptionStyle">{card?.locationdescription}</Card.Text>
                                    {/* <Button variant="primary">Go somewhere</Button> */}
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
