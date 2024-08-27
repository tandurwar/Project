import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AdminProperty = () => {
    const [properties, setProperties] = useState([]);
    const [activeSection, setActiveSection] = useState('rent');

    useEffect(() => {
        axios.get('http://localhost:9999/properties/all')
            .then(response => {
                setProperties(response.data);
            })
            .catch(error => {
                console.error('Error fetching properties:', error);
            });
    }, []);

    const rentProperties = properties.filter(property => property.type === 'rent');
    const buyProperties = properties.filter(property => property.type === 'buy');

    return (
        <div className="adminProperty-container">
            <nav className="adminProperty-navbar navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid justify-content-center">
                    <div className="collapse navbar-collapse justify-content-center">
                        <ul className="navbar-nav mb-2 mb-lg-0">
                            <li className="nav-item text-center">
                                <button 
                                    className={`nav-link btn adminProperty-nav-link ${activeSection === 'rent' ? 'active' : ''}`} 
                                    onClick={() => setActiveSection('rent')}
                                >
                                    Rent
                                </button>
                            </li>
                            <li className="nav-item text-center">
                                <button 
                                    className={`nav-link btn adminProperty-nav-link ${activeSection === 'buy' ? 'active' : ''}`} 
                                    onClick={() => setActiveSection('buy')}
                                >
                                    Buy
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div className="adminProperty-section">
                {activeSection === 'rent' && (
                    <div className="adminProperty-list">
                        <h2>Rent Properties</h2>
                        {rentProperties.map(property => (
                            <div key={property.propertyId} className="adminProperty-card card mb-3">
                                <div className="card-body">
                                    <h5 className="card-title">{property.propertyType}</h5>
                                    <p className="card-text">Address: {property.address}</p>
                                    <p className="card-text">Price: ${property.price}</p>
                                    <p className="card-text">City: {property.city}</p>
                                    <p className="card-text">State: {property.state}</p>
                                    <p className="card-text">Size: {property.size} sqft</p>
                                    <p className="card-text">Year Built: {property.yearBuilt}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                )}

                {activeSection === 'buy' && (
                    <div className="adminProperty-list">
                        <h2>Buy Properties</h2>
                        {buyProperties.map(property => (
                            <div key={property.propertyId} className="adminProperty-card card mb-3">
                                <div className="card-body">
                                    <h5 className="card-title">{property.propertyType}</h5>
                                    <p className="card-text">Address: {property.address}</p>
                                    <p className="card-text">Price: ${property.price}</p>
                                    <p className="card-text">City: {property.city}</p>
                                    <p className="card-text">State: {property.state}</p>
                                    <p className="card-text">Size: {property.size} sqft</p>
                                    <p className="card-text">Year Built: {property.yearBuilt}</p>
                                </div>
                            </div>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
};

export default AdminProperty;
