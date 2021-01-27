const commonHeaders = (withoutAuth = false) => {

  if (withoutAuth) {
    return {};
  }

  return {
    'Authorization':  extractToken()
  }
};

export const extractToken = () => {
  return localStorage.getItem('accessToken');
};

export const get = url => {
  return fetch(url, {
    method: 'GET',
    headers: commonHeaders()
  }).then((response) => response.json());
};

export const post = (url, body, withoutAuth = false) => {
  return fetch(url, {
    method: 'POST',
    headers: {
      ...commonHeaders(withoutAuth),
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  }).then((response) => response.json());
};