import Services from "../../common/services";

export const GET_DEFAULT_SUCCESS = "GET_DEFAULT_SUCCESS";
export const GET_DEFAULT_FAIL = "GET_DEFAULT_FAIL";

export const GET_LOCATION_DETAILS_SUCCESS = "GET_LOCATION_DETAILS_SUCCESS";
export const GET_LOCATION_DETAILS_FAIL = "GET_LOCATION_DETAILS_FAIL";
const CommonServices = Services
export const getDefault = () => async (dispatch) => {

    await CommonServices.getApi().then(function (res) {
        dispatch({ type: GET_DEFAULT_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: GET_DEFAULT_FAIL, payload: err.response });
    })
}

export const getLocationDetails = () => async (dispatch) => {

    await CommonServices.getApi('http://localhost:8080/foodtrucks').then(function (res) {
        dispatch({ type: GET_LOCATION_DETAILS_SUCCESS, payload: res });
    }).catch(err => {
        dispatch({ type: GET_LOCATION_DETAILS_FAIL, payload: err.response });
    })
}