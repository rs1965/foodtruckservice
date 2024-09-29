import {
    GET_LOCATION_DETAILS_SUCCESS, GET_LOCATION_DETAILS_FAIL,
    GET_ORDER_DETAILS_SAVE_SUCCESS, GET_ORDER_DETAILS_SAVE_FAIL,
    GET_USER_DETAILS_SAVE_RES_SUCCESS, GET_USER_DETAILS_SAVE_RES_FAIL,
    GET_USER_DETAILS_RES_SUCCESS, GET_USER_DETAILS_RES_FAIL,
    ITEM_SAVE_RES_SUCCESS, ITEM_SAVE_RES_FAIL, RESET_STATE_PART,
    GET_TOKEN_RES_SUCCESS,GET_TOKEN_RES_FAIL,GET_CREATE_QUOTE_RES_SUCCESS,
    GET_CREATE_QUOTE_RES_FAIL
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
    getUserDetailsRes: initialObj,
    insertItemSaveRes: initialObj,
    getTokenJWTRes: initialObj,
    getCreateQuoteRes: initialObj
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
        case GET_USER_DETAILS_RES_FAIL:
            return {
                ...state,
                getUserDetailsRes: {
                    data: payload?.data,
                    statusCode: payload?.status
                }
            }
        case ITEM_SAVE_RES_SUCCESS:
            return {
                ...state,
                insertItemSaveRes: {
                    data: payload.data,
                    statusCode: payload.status
                }
            }
        case ITEM_SAVE_RES_FAIL:
            return {
                ...state,
                insertItemSaveRes: {
                    data: payload?.data,
                    statusCode: payload?.status
                }
            }
            case GET_TOKEN_RES_SUCCESS:
                return {
                    ...state,
                    getTokenJWTRes: {
                        data: payload.data,
                        statusCode: payload.status
                    }
                }
            case GET_TOKEN_RES_FAIL:
                return {
                    ...state,
                    getTokenJWTRes: {
                        data: payload?.data,
                        statusCode: payload?.status
                    }
                }
                case GET_CREATE_QUOTE_RES_SUCCESS:
                    return {
                        ...state,
                        getCreateQuoteRes: {
                            data: payload.data,
                            statusCode: payload.status
                        }
                    }
                case GET_CREATE_QUOTE_RES_FAIL:
                    return {
                        ...state,
                        getCreateQuoteRes: {
                            data: payload?.data,
                            statusCode: payload?.status
                        }
                    }
        case RESET_STATE_PART:
            return {
                ...state,
                [payload]: initialObj
            };
        default:
            return state;
    }
}
export default defaultReducer;