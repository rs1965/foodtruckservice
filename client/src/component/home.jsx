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
    return (
        <>
            <div className="main-content">
                <SearchBar handleSearch={getLocationByText}
                    handleInputChange={handleChange} text={text}
                    placeholder={'City/State or Zipcode...'} />
            </div>
            <div className='tablecustom'>
                <TableCustom />
            </div>
        </>
    )
}

export default Home
