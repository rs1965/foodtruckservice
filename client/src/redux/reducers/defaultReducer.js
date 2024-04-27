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
        case GET_DEFAULT_SUCCESS:
            return {
                ...state,
                getDefaultRes: {
                    data: payload.data,
                    statusCode: payload.statusCode
                }
            }
        case GET_DEFAULT_FAIL:
            return {
                ...state,
                getDefaultRes: {
                    data: payload.data,
                    statusCode: payload.statusCode
                }
            }
        case GET_LOCATION_DETAILS_SUCCESS:
            return {
                ...state,
                getLocationDetailsRes: {
                    data: payload.data,
                    statusCode: payload.statusCode
                }
            }
        // case GET_LOCATION_DETAILS_FAIL:
        //     return {
        //         ...state,
        //         getLocationDetailsRes: {
        //             data: payload.data,
        //             statusCode: payload.statusCode
        //         }
        //     }
        default:
            return state;
    }
}
export default defaultReducer;