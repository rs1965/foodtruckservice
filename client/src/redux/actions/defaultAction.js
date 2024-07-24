import Services from "../../common/services";


export const GET_LOCATION_DETAILS_SUCCESS = "GET_LOCATION_DETAILS_SUCCESS";
export const GET_LOCATION_DETAILS_FAIL = "GET_LOCATION_DETAILS_FAIL";

export const GET_ORDER_DETAILS_SAVE_SUCCESS = "GET_ORDER_DETAILS_SAVE_SUCCESS";
export const GET_ORDER_DETAILS_SAVE_FAIL = "GET_ORDER_DETAILS_SAVE_FAIL";
const CommonServices = Services

export const getLocationDetails = () => async (dispatch) => {

    await CommonServices.getApi('http://localhost:8080/getAllLocationDetails?status=false').then(function (res) {
        dispatch({ type: GET_LOCATION_DETAILS_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: GET_LOCATION_DETAILS_FAIL, payload: err.response });
    })
}

export const getLocationMetaDetails = (city, status) => async (dispatch) => {

    await CommonServices.getApi(`http://localhost:8080/getFoodTruckMeta?city=${city}&status=${false}`).then(function (res) {
        dispatch({ type: GET_LOCATION_DETAILS_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: GET_LOCATION_DETAILS_FAIL, payload: err.response });
    })
}

export const setOrderDetails = (payload) => async (dispatch) => {

    await CommonServices.postApi(`http://localhost:8080/api/orders/insertOrder`, payload).then(function (res) {
        dispatch({ type: GET_ORDER_DETAILS_SAVE_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: GET_ORDER_DETAILS_SAVE_FAIL, payload: err.response });
    })
}