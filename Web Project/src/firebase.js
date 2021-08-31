import firebase from 'firebase';

// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyC1WEtAfSwQmMHlO3pCRuJkSE31PqxmSKs",
    authDomain: "hod-final-project.firebaseapp.com",
    databaseURL: "https://hod-final-project-default-rtdb.firebaseio.com",
    projectId: "hod-final-project",
    storageBucket: "hod-final-project.appspot.com",
    messagingSenderId: "363570840704",
    appId: "1:363570840704:web:b3486958963b07091d9265",
    measurementId: "G-FW2T3YFCTE"
  };

const firebaseApp = firebase.initializeApp(firebaseConfig)

const db = firebaseApp.firestore()
const auth = firebase.auth()
const provider = new firebase.auth.GoogleAuthProvider()

export { auth, provider }
export default db