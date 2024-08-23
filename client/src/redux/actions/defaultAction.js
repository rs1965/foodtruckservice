import Services from "../../common/services";


export const GET_LOCATION_DETAILS_SUCCESS = "GET_LOCATION_DETAILS_SUCCESS";
export const GET_LOCATION_DETAILS_FAIL = "GET_LOCATION_DETAILS_FAIL";

export const GET_ORDER_DETAILS_SAVE_SUCCESS = "GET_ORDER_DETAILS_SAVE_SUCCESS";
export const GET_ORDER_DETAILS_SAVE_FAIL = "GET_ORDER_DETAILS_SAVE_FAIL";
export const GET_USER_DETAILS_SAVE_RES_SUCCESS = "GET_USER_DETAILS_SAVE_RES_SUCCESS";
export const GET_USER_DETAILS_SAVE_RES_FAIL = "GET_USER_DETAILS_SAVE_RES_FAIL";
export const GET_USER_DETAILS_RES_SUCCESS = "GET_USER_DETAILS_RES_SUCCESS";
export const GET_USER_DETAILS_RES_FAIL = "GET_USER_DETAILS_RES_FAIL";
export const ITEM_SAVE_RES_SUCCESS = "ITEM_SAVE_RES_SUCCESS";
export const ITEM_SAVE_RES_FAIL = "ITEM_SAVE_RES_FAIL";

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

export const insertLoginUserSave = (payload) => async (dispatch) => {

    await CommonServices.postApi(`http://localhost:8080/user/addUser`, payload).then(function (res) {
        dispatch({ type: GET_USER_DETAILS_SAVE_RES_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: GET_USER_DETAILS_SAVE_RES_FAIL, payload: err.response });
    })
}

export const getUserDetails = (id) => async (dispatch) => {

    await CommonServices.getApi(`http://localhost:8080/user/getUser/${id}`).then(function (res) {
        dispatch({ type: GET_USER_DETAILS_RES_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: GET_USER_DETAILS_RES_FAIL, payload: err.response });
    })
}

export const insertItemSave = (payload) => async (dispatch) => {

    await CommonServices.postApi(`http://localhost:8080/item/addItem`, payload).then(function (res) {
        dispatch({ type: ITEM_SAVE_RES_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: ITEM_SAVE_RES_FAIL, payload: err.response });
    })
}