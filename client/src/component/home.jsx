import React, { useEffect, useState } from 'react'
import SearchBar from '../common/searchbar'
import { useDispatch, useSelector } from 'react-redux'
import { getLocationDetails } from '../redux/actions/defaultAction';
import TableCustom from '../common/tablecustom';
function Home() {
    const [location, setLocation] = useState([])
    const [text, setText] = useState('');
    const dispatch = useDispatch();
    const useLocation = (state) => useSelector(state.getLocationDetailsRes);
    const { getLocationDetailsRes } = useLocation
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
        console.log(getLocationDetailsRes, "getLocationDetailsRes")
    }, [getLocationDetailsRes])
    const handleChange = (event) => {
        setText(event.target.value)
    }
    const getLocationByText = () => {
        console.log('clicked....')
        dispatch(getLocationDetails())
    }
    const data = [
        {
            id: '0',
            name: 'Shopping List',
            deadline: new Date(2020, 1, 15),
            type: 'TASK',
            isComplete: true,
            nodes: 3,
        },
    ];

    const columns = [
        { label: 'Facility Type', renderCell: (item) => item.name },
        {
            label: 'Address',
            renderCell: (item) =>
                item.deadline.toLocaleDateString('en-US', {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit',
                }),
        },
        { label: 'Schedule', renderCell: (item) => item.type },
        {
            label: 'Location Description',
            renderCell: (item) => item.isComplete.toString(),
        },
        { label: 'Food Items', renderCell: (item) => item.nodes },
    ];
    return (
        <>
            <div className="main-content">
                <SearchBar handleSearch={getLocationByText}
                    handleInputChange={handleChange} text={text}
                    placeholder={'City,State or Zipcode'} />
            </div>
            <div className='tablecustom'>
                <TableCustom columns={columns} nodes={data} />
            </div>
        </>
    )
}

export default Home
