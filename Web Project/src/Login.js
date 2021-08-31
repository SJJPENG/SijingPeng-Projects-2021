import React from 'react'
import "./Login.css";
import { Button } from "@material-ui/core";
import { auth, provider } from "./firebase";
import { useStateValue } from "./StateProvider";
import { actionTypes } from "./reducer";


const Login = () => {
    const [state, dispatch] = useStateValue();

    const signIn = () => {
        auth.signInWithPopup(provider)
            .then((result) => {
                dispatch({
                    type: actionTypes.SET_USER,
                    user: result.user,
                });
            })
            .catch((error) => {
                alert(error.message);
            });
    };

    return (
        <div className="login" >
            <div className="login__container">
                <img
                    src="https://apollostore.blob.core.windows.net/multifamilybiz/News/Axle_Apts.jpg"
                    alt=""
                />

                <h1>Sign in to HOD Community Chat Room</h1>
                <p>HOD.Chat.com</p>
                <Button onClick={signIn}>Sign in with Google</Button>
            </div>
        </div >
    )
}

export default Login
