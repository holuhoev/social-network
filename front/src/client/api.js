// import cookie from "react-cookies";

const backend_url = 'http://localhost:8080';

const buildHeaders = () => {

  return {
    Accept: 'application/json',
    'Content-Type': 'text/plain',
    'Authorization': extractToken(),
    'X-Requested-With': 'XMLHttpRequest'
  }
};

const extractToken = () => {
  return 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJ1c2VyX2lkIjoiYWNkODMzN2QtNjgxNS00NWMxLWJkZDYtMDI3ZGIzMmM5NDNmIiwiZmlyc3RfbmFtZSI6InN0cmluZyIsImxhc3RfbmFtZSI6InN0cmluZyIsImlhdCI6MTYxMTQyMTc2MSwiZXhwIjoxNjEyNDIxNzYxfQ.s9bTQZ147NYIK40wc4UsgRX44207j5oVvXnOV9GAaSg';
};

export const get = url => {
  return fetch(url, {
    method: 'GET',
    headers: buildHeaders()
  });
};

