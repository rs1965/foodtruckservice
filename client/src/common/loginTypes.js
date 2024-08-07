import React, { useCallback, useEffect, useState } from 'react';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { jwtDecode } from "jwt-decode";
import { LoginSocialFacebook } from 'reactjs-social-login';
import { FacebookLoginButton } from 'react-social-login-buttons';
import FacebookLogin from 'react-facebook-login';
const GoogleLoginProvider = () => {

    const handleGoogleSuccess = (response) => {
        console.log(jwtDecode(response?.credential));
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
                />
            </GoogleOAuthProvider>

        </>
    )
}


const FaceBookLoginProvider = () => {

    const fetchUserData = async (accessToken) => {
        try {
            const res = await fetch(`https://graph.facebook.com/me?fields=name,picture&access_token=${accessToken}`);
            const data = await res.json();
            console.log(data)
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
                fieldsProfile={
                    'id,first_name,last_name,middle_name,name,name_format,picture,short_name,email,gender'
                }
                callback={responseFacebook}
                icon="fa-facebook"
            />
        </>
    );
}



export { GoogleLoginProvider, FaceBookLoginProvider }