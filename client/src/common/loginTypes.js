import React from 'react';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import { jwtDecode } from "jwt-decode";
import { useLinkedIn } from "react-linkedin-login-oauth2";
import linkedin from "react-linkedin-login-oauth2/assets/linkedin.png";
import FacebookLogin from 'react-facebook-login/dist/facebook-login-render-props'
// import { FacebookLoginButton } from 'react-social-login-buttons';
// import { LoginSocialFacebook } from 'reactjs-social-login';
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

    const responseFacebook = (response) => {
        console.log(response);
        // Handle the response (e.g., store user data)
    };

    return (
        // <LoginSocialFacebook
        //     appId="452995574222468"
        //     onResolve={handleLogin}
        //     onReject={handleError}
        // >
        //     <FacebookLoginButton />
        // </LoginSocialFacebook>
        <FacebookLogin
            appId="498310359268039"
            autoLoad={true}
            fields="name,email,picture"
            callback={responseFacebook}
            render={renderProps => (
                <button onClick={renderProps.onClick}>
                    Login with Facebook
                </button>
            )}
        />
    );
    // };
}



function LinkedInLoginProvider() {
    const { linkedInLogin } = useLinkedIn({
        clientId: '86wj5ppsvt1dzo',
        redirectUri: 'http://localhost:3000/linkedin',
        onSuccess: (code) => {
            // Change from `data.code` to `code`
            console.log(code);
        },
        // Change from `onFailure` to `onError`
        onError: (error) => {
            console.log(error);
        },
    });

    return (
        <>
            <img
                src={linkedin}
                alt="Log in with Linked In"
                style={{ maxWidth: '180px' }}
                // Pass `linkedInLogin` to `onClick` handler
                onClick={linkedInLogin}
            />
        </>
    );
}

export { GoogleLoginProvider, FaceBookLoginProvider, LinkedInLoginProvider }