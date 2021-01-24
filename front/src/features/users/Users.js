import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchUsers, selectAllUsers } from './usersSlice';

export function Users() {
  const users = useSelector(selectAllUsers);
  const usersStatus = useSelector(state => state.users.status);
  const errorMessage = useSelector(state => state.users.error);

  const dispatch = useDispatch();

  fetch('/users', {
    method: 'GET',
    headers: {
      Authorization: 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJ1c2VyX2lkIjoiYWNkODMzN2QtNjgxNS00NWMxLWJkZDYtMDI3ZGIzMmM5NDNmIiwiZmlyc3RfbmFtZSI6InN0cmluZyIsImxhc3RfbmFtZSI6InN0cmluZyIsImlhdCI6MTYxMTQyMTc2MSwiZXhwIjoxNjEyNDIxNzYxfQ.s9bTQZ147NYIK40wc4UsgRX44207j5oVvXnOV9GAaSg'
    }
  }).then(res => console.log(res));

  // useEffect(() => {
  //   if (usersStatus === 'idle') {
  //     dispatch(fetchUsers())
  //   }
  // }, [usersStatus, users]);

  return (
    <div>
      {errorMessage && (<div>Error: {errorMessage}</div>)}
      {
        users.map(user => (
          <div>Name: {user.username}</div>
        ))
      }
    </div>
  );
}
