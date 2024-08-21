import React, { useCallback, useEffect, useState } from 'react';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { jwtDecode } from "jwt-decode";
import FacebookLogin from 'react-facebook-login';
import { useLinkedIn } from 'react-linkedin-login-oauth2';
import linkedin from 'react-linkedin-login-oauth2/assets/linkedin.png';
import { FaFacebook, FaLinkedin } from 'react-icons/fa';
const GoogleLoginProvider = (props) => {
    const { setUserDetails } = props
    const handleGoogleSuccess = (response) => {
        //console.log(jwtDecode(response?.credential));
        setUserDetails(jwtDecode(response?.credential))

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
    const { setUserDetails } = props
    const fetchUserData = async (accessToken) => {
        try {
            const res = await fetch(`https://graph.facebook.com/me?fields=name,picture&access_token=${accessToken}`);
            const data = await res.json();
            // console.log(data)
            setUserDetails(data)
        } catch (error) {
            console.error('Error fetching user data:', error);
        }
    };

    const responseFacebook = (response) => {
        if (response.accessToken) {
            fetchUserData(response.accessToken);
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
                fieldsProfile={
                    'id,first_name,last_name,middle_name,name,name_format,picture,short_name,email,gender'
                }
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