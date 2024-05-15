import {
    GET_DEFAULT_SUCCESS, GET_DEFAULT_FAIL,
    GET_LOCATION_DETAILS_SUCCESS, GET_LOCATION_DETAILS_FAIL
} from '../actions/defaultAction';

const initialObj = {
    data: [],
    statusCode: "",
}

const initialState = {
    getDefaultRes: initialObj,
    getLocationDetailsRes: initialObj
}

function defaultReducer(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {
        case GET_LOCATION_DETAILS_SUCCESS:
            return {
                ...state,
                getLocationDetailsRes: {
                    data: payload.data,
                    statusCode: payload.status
                }
            }
        case GET_LOCATION_DETAILS_FAIL:
            return {
                ...state,
                getLocationDetailsRes: {
                    data: payload?.data,
                    statusCode: payload?.status
                }
            }
        default:
            return state;
    }
}
export default defaultReducer;