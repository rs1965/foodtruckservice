import { createSlice, applyMiddleware, compose, createStore } from '@reduxjs/toolkit'
import { thunk } from 'redux-thunk'
import appReducer from '../reducers';

const store = createStore(
    appReducer,
    compose(applyMiddleware(thunk)),
)
export default store;