import {
    GET_LOCATION_DETAILS_SUCCESS, GET_LOCATION_DETAILS_FAIL,
    GET_ORDER_DETAILS_SAVE_SUCCESS, GET_ORDER_DETAILS_SAVE_FAIL,
    GET_USER_DETAILS_SAVE_RES_SUCCESS, GET_USER_DETAILS_SAVE_RES_FAIL,
    GET_USER_DETAILS_RES_SUCCESS, GET_USER_DETAILS_RES_FAIL
} from '../actions/defaultAction';

const initialObj = {
    data: [],
    statusCode: "",
}

const initialState = {
    getDefaultRes: initialObj,
    getLocationDetailsRes: initialObj,
    setOrderDetailsRes: initialObj,
    insertLoginUserSaveRes: initialObj,
    getUserDetailsRes: initialObj
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
        case GET_ORDER_DETAILS_SAVE_SUCCESS:
            return {
                ...state,
                setOrderDetailsRes: {
                    data: payload.data,
                    statusCode: payload.status
                }
            }
        case GET_ORDER_DETAILS_SAVE_FAIL:
            return {
                ...state,
                setOrderDetailsRes: {
                    data: payload?.data,
                    statusCode: payload?.status
                }
            }
        case GET_USER_DETAILS_SAVE_RES_SUCCESS:
            return {
                ...state,
                insertLoginUserSaveRes: {
                    data: payload.data,
                    statusCode: payload.status
                }
            }
        case GET_USER_DETAILS_SAVE_RES_FAIL:
            return {
                ...state,
                insertLoginUserSaveRes: {
                    data: payload?.data,
                    statusCode: payload?.status
                }
            }
        case GET_USER_DETAILS_RES_SUCCESS:
            return {
                ...state,
                getUserDetailsRes: {
                    data: payload.data,
                    statusCode: payload.status
                }
            }
        case GET_USER_DETAILS_RES_SUCCESS:
            return {
                ...state,
                getUserDetailsRes: {
                    data: payload?.data,
                    statusCode: payload?.status
                }
            }
        default:
            return state;
    }
}
export default defaultReducer;