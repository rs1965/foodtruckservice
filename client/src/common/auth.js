import { getURLWithQueryParams } from "../utils/generic"

export const LINKEDIN_STATE = 'random_string'
const LINKEDIN_SCOPE = 'r_liteprofile r_emailaddress'
const LINKEDIN_RIDERECT = window.location.hostname
console.log(LINKEDIN_RIDERECT);
const LINKEDIN_CLIENT_ID = '86tgjuy5776q1a'

const getURLWithQueryParams = (base, params) => {
    const query = Object
        .entries(params)
        .map(([key, value]) => `${key}=${encodeURIComponent(value)}`)
        .join('&')

    return `${base}?${query}`
}
export const LINKEDIN_URL = getURLWithQueryParams('https://www.linkedin.com/oauth/v2/authorization', {
    response_type: "code",
    client_id: LINKEDIN_CLIENT_ID,
    redirect_uri: LINKEDIN_RIDERECT,
    state: LINKEDIN_STATE,
    scope: LINKEDIN_SCOPE
})