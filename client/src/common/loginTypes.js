import React, { useCallback, useEffect, useState } from 'react';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { jwtDecode } from "jwt-decode";
import FacebookLogin from 'react-facebook-login';
import { useLinkedIn } from 'react-linkedin-login-oauth2';
import linkedin from 'react-linkedin-login-oauth2/assets/linkedin.png';
import { FaFacebook, FaLinkedin } from 'react-icons/fa';

const getExpiryDateTime = (dt) => {
    const expTime = dt * 1000; // seconds to milliseconds convert
    localStorage.setItem('token_exp', expTime);
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

function LinkedInLogin() {
    const redirectUri = `${window.location.origin}/linkedin/callback`
    const { linkedInLogin } = useLinkedIn({
        clientId: '86tgjuy5776q1a',
        redirectUri: redirectUri,
        onSuccess: (code) => console.log(code),
        onError: (error) => console.log(error),
    });

    return (
        <FaLinkedin
            onClick={linkedInLogin}
            // src={FaLinkedin}
            className="custom-linkedin-icon"
        />
    );
}


export { GoogleLoginProvider, FaceBookLoginProvider, LinkedInLogin }