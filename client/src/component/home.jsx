import React, { useEffect, useState } from 'react'
import SearchBar from '../common/searchbar'
import { useDispatch, useSelector } from 'react-redux'
import { getLocationDetails, getLocationMetaDetails } from '../redux/actions/defaultAction';
import SpinnerComponent from '../common/spinner';
import CustomTable from '../common/customTable';
import CanvasView from '../common/canvas';
function Home() {
    const [location, setLocation] = useState([])
    const [text, setText] = useState('');
    const dispatch = useDispatch();
    const [showLoader, setShowLoader] = useState(false);
    const [tableData, setTableData] = useState([]);
    const [recdSelected, setRecdSelected] = useState([]);
    const [showOffcanvas, setShowOffcanvas] = useState(false);
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
        } else if (getLocationDetailsRes?.statusCode !== 200) {
            setShowLoader(false);
        }
    }, [getLocationDetailsRes])
    function replaceRepeatingCommas(text) {
        const inputString = text;
        const firstCommaIndex = inputString.indexOf(",");
        const outputString = inputString.slice(0, firstCommaIndex + 1) + inputString.slice(firstCommaIndex + 1).replaceAll(",", "");
        return outputString
    }

    const handleChange = (event) => {
        // Get the input value
        const value = event.target.value;
        // Replace repeating commas
        const cleanedValue = replaceRepeatingCommas(value);
        let firstChar = cleanedValue.charAt(0);
        let removeNum;
        if (!isNaN(firstChar) && firstChar >= '0' && firstChar <= '9') {
            setText(cleanedValue.replace(/[^0-9]/g, ''))
        } else {
            // Update the state with the cleaned value
            setText(cleanedValue);
        }
    }
    const getLocationByText = (e) => {
        e.preventDefault();
        if (text === '') {
            dispatch(getLocationDetails())
        } else if (text !== "") {
            if (['san francisco', 'sanfrancisco', 'san-francisco'].includes(text.split(',')[1].toLowerCase())) {
                dispatch(getLocationMetaDetails('sf'))
            }
            if (['new york', 'newyork', 'new-york'].includes(text.split(',')[1].toLowerCase())) {
                dispatch(getLocationMetaDetails('ny'))
            }
        }
        setShowLoader(true)
    }

    const columns = [
        {
            dataField: "applicant",
            text: "Applicant",
            // filter: textFilter(),
            // sort: true,
        },
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
            formatter: (cell, row) => (
                <a href={`${cell}`} target="_blank" rel="noopener noreferrer">
                    {cell}
                </a>
            )
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
    const handleRowClick = (row) => {
        setRecdSelected(row)
        { row && setShowOffcanvas(true) }
    }
    const handleCloseOffcanvas = () => {
        setRecdSelected([])
        setShowOffcanvas(false)
    }
    const testRecd = [
        {
            "camis": "50116556",
            "dba": "WENDY'S",
            "boro": "Queens",
            "building": "1",
            "street": "MAIN TERMINAL",
            "phone": "9173282791",
            "inspection_date": "1900-01-01T00:00:00.000",
            "critical_flag": "Not Applicable",
            "record_date": "2024-07-11T06:00:13.000",
            "latitude": "0",
            "longitude": "0",
            "bbl": "4"
        }]
    return (
        <>
            {showLoader && <SpinnerComponent />}
            <div className="main-content">
                <SearchBar handleSearch={getLocationByText}
                    handleInputChange={handleChange} text={text}
                    placeholder={'City,State or Zipcode'}
                    errorMsg={text && !isNaN(text.charAt(0)) ? '' :
                        (text && !text?.includes(',')) ? 'Please Enter City,State or Zipcode' :
                            (text && text?.split(',')[0]?.trim() === '') ? 'Please Enter City' :
                                (text && text?.split(',')[1]?.trim() === '') ? 'Please Enter State' : ''}
                />
            </div>
            <div className='tablecustom'>
                {/* <TableCustom columns={columns} nodes={tableData} /> */}
                <CustomTable columns={columns} tableData={tableData} styleTable={"table-bordered"}
                    tableHeight="400px" handleRowClick={handleRowClick} />
                {recdSelected &&
                    <>
                        <CanvasView view={showOffcanvas} data={recdSelected}
                            handleCloseOffcanvas={handleCloseOffcanvas} />
                    </>
                }
                <CanvasView view={true} data={testRecd}
                    handleCloseOffcanvas={handleCloseOffcanvas} />
            </div>
        </>
    )
}

export default Home
