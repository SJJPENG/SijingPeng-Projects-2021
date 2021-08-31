import React from 'react'
import './Header.css'
import { Avatar } from '@material-ui/core'
import { useStateValue } from './StateProvider';

const Header = () => {
    const [{user}]=useStateValue()

    return (
        <div className='header' >
            <div className="header__left">
                <Avatar className="header__avatar" src={user?.photoURL} alt={user?.displayName} />
            </div>

            <div className="header__search">
                <input placeholder="HoD Community" />
            </div>

            <div className="header__right">
            </div>
        </div>
    )
}

export default Header
