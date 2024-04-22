import { GET_DEFAULT_SUCCESS, GET_DEFAULT_FAIL } from '../actions/defaultAction';

const initialObj = {
    data: [],
    message: '',
    isSuccess: false,
    statusCode: "",
    isLoading: false
}

const initialState = {
    getDefaultRes: initialObj
}

function defaultReducer(state = initialState, action) {
    const { type, payload } = action;
    switch (type) {
        case GET_DEFAULT_SUCCESS:
            return {
                ...state,
                getDefaultRes: {
                    data: payload.data,
                    message: payload.message,
                    isSuccess: payload.isSuccess,
                    statusCode: payload.statusCode
                }
            }
        case GET_DEFAULT_FAIL:
            return {
                ...state,
                getDefaultRes: {
                    data: payload.data,
                    message: payload.message,
                    isSuccess: payload.isSuccess,
                    statusCode: payload.statusCode
                }
            }
        default:
            return state;
    }
}
export default defaultReducer;