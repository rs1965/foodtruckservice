import React, { useEffect, useState } from 'react'
import SearchBar from '../common/searchbar'
import { useDispatch, useSelector } from 'react-redux'
import { getLocationDetails } from '../redux/actions/defaultAction';
import SpinnerComponent from '../common/spinner';
import CustomTable from '../common/customTable';
function Home() {
    const [location, setLocation] = useState([])
    const [text, setText] = useState('');
    const dispatch = useDispatch();
    const [showLoader, setShowLoader] = useState(false);
    const [tableData, setTableData] = useState([]);

    const locationState = useSelector((state) => state.defaultReducer);
    const { getLocationDetailsRes } = locationState
    useEffect(() => {
        if (navigator.geolocation && location.length === 0) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    setLocation({
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude,
                    });
                },
            );
        } else {
            console.log('Geolocation is not supported by this browser.');
        }
    }, [])
    useEffect(() => {
        if (getLocationDetailsRes?.statusCode === 200 && getLocationDetailsRes?.data !== null) {
            setShowLoader(false);
            setTableData(getLocationDetailsRes?.data)
        }
    }, [getLocationDetailsRes])
    const handleChange = (event) => {
        setText(event.target.value)
    }
    const getLocationByText = () => {
        dispatch(getLocationDetails())
        setShowLoader(true)
    }

    const columns = [
        {
            dataField: "facilitytype",
            text: "Facility Type",
            // filter: textFilter(),
            // sort: true,
        },
        {
            dataField: "address",
            text: "Address",
            // filter: textFilter(),
            // sort: true,
        },
        {
            dataField: "schedule",
            text: "Schedule",
            // filter: textFilter(),
            // sort: true,
            style: {
                whiteSpace: 'normal', // Prevent text wrapping
                wordWrap: "break-word",
                overflow: 'hidden', // Hide overflow text
                textOverflow: 'ellipsis', // Use ellipsis for overflow text

            },
        },
        {
            dataField: "locationdescription",
            text: "Location Description",
            // filter: textFilter(),
            // sort: true,
        },
        {
            dataField: "fooditems",
            text: "Food Items",
            // filter: textFilter(),
            // sort: true,
        },
        // Add more columns as needed
    ];
    return (
        <>
            {showLoader && <SpinnerComponent />}
            <div className="main-content">
                <SearchBar handleSearch={getLocationByText}
                    handleInputChange={handleChange} text={text}
                    placeholder={'City,State or Zipcode'} />
            </div>
            <div className='tablecustom'>
                {/* <TableCustom columns={columns} nodes={tableData} /> */}
                <CustomTable columns={columns} tableData={tableData} styleTable={"table-bordered"} />
            </div>
        </>
    )
}

export default Home
