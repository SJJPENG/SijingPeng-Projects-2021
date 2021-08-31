import React, { useEffect, useState } from 'react';
import './Sidebar.css';
import CreateIcon from '@material-ui/icons/Create';
import SidebarOption from "./SidebarOption"
import InsertCommentIcon from '@material-ui/icons/InsertComment';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import AddIcon from '@material-ui/icons/Add';
import db from './firebase'

const Sidebar = () => {
    const [channels, setChannels] = useState([]);

    useEffect(() => {
        db.collection('rooms').onSnapshot(snapshot => {
            setChannels(
                snapshot.docs.map(doc => ({
                    id: doc.id,
                    name: doc.data().name
                })))
        })
    }, [])

    return (
        <div className='sidebar' >
            <div className="sidebar__header">
                <div className="sidebar__info">
                    <h2>HOD community</h2>
                    <h3>
                        Welcome!
                    </h3>
                </div>
            </div>
            <SidebarOption Icon={AddIcon} addChannelOption title="Add Channel" />

            {
                channels.map(channel => (
                    <SidebarOption title={channel.name} id={channel.id} />
                )
                )
            }

        </div>
    )
}

export default Sidebar
