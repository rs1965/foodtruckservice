import React, { useCallback, useEffect, useState } from 'react';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { jwtDecode } from "jwt-decode";
import FacebookLogin from 'react-facebook-login';
import { useLinkedIn } from 'react-linkedin-login-oauth2';
import linkedin from 'react-linkedin-login-oauth2/assets/linkedin.png';
import { FaFacebook, FaLinkedin } from 'react-icons/fa';

import axios from 'axios';
import { useDispatch } from 'react-redux';
import { getLinkedInDetails } from '../redux/actions/defaultAction';

export const getExpiryDateTime = (dt) => {
    const expTime = dt * 1000; // seconds to milliseconds convert
    // localStorage.setItem('token_exp', expTime);
    return new Date(expTime)
}
const GoogleLoginProvider = (props) => {
    const { setUserDetails, isChecked } = props
    const handleGoogleSuccess = (response) => {
        const data = jwtDecode(response.credential)
        const payload = {
            userId: data?.sub,
            type: "google",
            role: isChecked ? 'Admin' : 'Consumer',
            emailId: data?.email,
            name: `${data?.given_name, data?.family_name}`,
            expiryDateTime: getExpiryDateTime(data?.exp)
        }
        setUserDetails(payload)

    };
    const handleGoogleFailure = (error) => {
        console.error(error);

    };
    return (
        <>
            <GoogleOAuthProvider clientId='1062604604364-mq6gdlkvqss554agatk08ram2b2ugume.apps.googleusercontent.com'>
                <GoogleLogin
                    onSuccess={handleGoogleSuccess}
                    onFailure={handleGoogleFailure}
                    type='icon'
                    theme='filled_blue'
                    size='large'
                />
            </GoogleOAuthProvider>

        </>
    )
}


const FaceBookLoginProvider = (props) => {
    const { setUserDetails, isChecked } = props
    // const fetchUserData = async (accessToken) => {
    //     try {
    //         const res = await fetch(`https://graph.facebook.com/me?fields=name,picture,email&access_token=${accessToken}`);
    //         const data = await res.json();
    //         const payload = {
    //             userId: "092",
    //             type: "Google",
    //             role: "Consumer",
    //             expiryDateTime: "2024-12-31T23:59:59"
    //         }
    //         setUserDetails(data)
    //     } catch (error) {
    //         console.error('Error fetching user data:', error);
    //     }
    // };

    const responseFacebook = (response) => {
        if (response.accessToken) {
            // fetchUserData(response.accessToken);
            const payload = {
                userId: response?.id,
                type: "facebook",
                role: isChecked ? 'Admin' : 'Consumer',
                emailId: response?.email,
                name: response?.name,
                expiryDateTime: getExpiryDateTime(response?.data_access_expiration_time)
            }
            setUserDetails(payload)

        }
        // Handle the response from Facebook here (e.g., send the token to your backend)
    };
    //498310359268039
    return (
        <>
            <FacebookLogin
                appId={498310359268039}
                autoLoad={false}
                textButton=''
                fields={
                    'id,name,email'
                }
                scope="public_profile,email"
                cssClass='custom-facebook-icon'
                callback={responseFacebook}
                icon={<FaFacebook className="custom-facebook-icon" />}
            />
        </>
    );
}

function LinkedInLogin(props) {
    const { setUserDetails, isChecked } = props
    const [user, setUser] = useState();
    const dispatch = useDispatch();
    const LINKEDIN_CLIENT_ID = '86tgjuy5776q1a';
    const REDIRECT_URI = `${window.location.origin}/callback`;
    const STATE = 'YOUR_RANDOM_STATE';
    const SCOPE = 'email profile openid w_member_social';
    const POPUP_WIDTH = 600;
    const POPUP_HEIGHT = 700;
    const handleLogin = () => {
        const left = (window.innerWidth - POPUP_WIDTH) / 2;
        const top = (window.innerHeight - POPUP_HEIGHT) / 2;

        const popup = window.open(
            `https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=${LINKEDIN_CLIENT_ID}&redirect_uri=${encodeURIComponent(REDIRECT_URI)}&state=${STATE}&scope=${SCOPE}`,
            'linkedin-login',
            `width=${POPUP_WIDTH},height=${POPUP_HEIGHT},top=${top},left=${left}`
        );

        if (popup) {
            const timer = setInterval(() => {
                if (popup.closed) {
                    clearInterval(timer);
                }
            }, 1000);
        }
    };
    const processToken = async (data) => {
        try {
            const result = dispatch(getLinkedInDetails(data));

        } catch (error) {
            console.error('Error fetching LinkedIn user data:', error);
        }
    }
    useEffect(() => {
        const handleMessage = (event) => {
            if (event.origin !== window.location.origin) {
                return; // Ignore messages from different origins
            }

            if (event.data.type === 'linkedin-auth') {
                processToken(event?.data?.profile);
            }
        };

        window.addEventListener('message', handleMessage);

        return () => {
            window.removeEventListener('message', handleMessage);
        };
    }, []);
    return (
        <FaLinkedin
            onClick={handleLogin}
            // src={FaLinkedin}
            className="custom-linkedin-icon"
        />
        // <button onClick={handleLogin}>Login with LinkedIn</button>
    );
}


export { GoogleLoginProvider, FaceBookLoginProvider, LinkedInLogin }