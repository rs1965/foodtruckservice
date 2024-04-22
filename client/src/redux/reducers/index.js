import { combineReducers } from 'redux'
import defaultReducer from './defaultReducer'

const appReducer = combineReducers({
    defaultReducer: defaultReducer
})

export default appReducer