// LinkedInCallback.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import qs from 'qs';
const LINKEDIN_CLIENT_SECRET = '6iTDQMRBoQ7LQqq9';
const LINKEDIN_CLIENT_ID = '86tgjuy5776q1a';
const REDIRECT_URI = `${window.location.origin}/callback`;
const STATE = 'YOUR_RANDOM_STATE';
const LinkedInCallback = () => {
    const [accessToken, setAccessToken] = useState()
    const queryParams = new URLSearchParams(window.location.search);
    const code = queryParams.get('code');
    const state = queryParams.get('state');
    useEffect(() => {
        const fetchUserData = async () => {
            if (code && state === STATE) {
                if (window.opener) {
                    window.opener.postMessage({
                        type: 'linkedin-auth',
                        profile: code,
                        email: state,
                        secret: LINKEDIN_CLIENT_SECRET
                    }, window.location.origin);
                    window.close();
                }
            } else {
                console.error('Authorization code or state missing');
            }
        };

        fetchUserData();
    }, [code, state]);
    useEffect(() => {
        // console.log(accessToken)
    }, [accessToken])
    return <div>Loading...</div>;
};

export default LinkedInCallback;
