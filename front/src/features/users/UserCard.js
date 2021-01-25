import React from 'react';
import { Card } from 'antd';


export const UserCard = ({ user, children }) => (
  <Card bordered style={{ width: 300, float: 'left', margin: 10 }}>
    <hr/>
    Username: {user.username}
    <br/>
    FirstName: {user.firstName}
    <br/>
    LastName: {user.lastName}
    <br/>
    Age: {user.age}
    <br/>
    Interests: {user.interests}
    <br/>
    City: {user.city}
    <br/>
    {children}
  </Card>
);
