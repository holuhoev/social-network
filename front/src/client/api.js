export const extractToken = () => {
  return localStorage.getItem('accessToken');
};

const authorizationHeader = (withoutAuth) => {
  if (withoutAuth) {
    return {}
  }

  return { 'Authorization': extractToken() }
};

export async function client(endpoint, { method, body, withoutAuth } = {}) {
  const headers = {
    'Content-Type': 'application/json',
    ...authorizationHeader(withoutAuth)
  };

  const config = {
    method: method,
    headers: headers
  };

  if (body) {
    config.body = JSON.stringify(body)
  }

  let data;
  try {
    const response = await window.fetch('/services/rest' + endpoint, config);
    data = await response.json();
    if (data.success) {
      return data;
    }
    throw new Error("Error")
  } catch (err) {
    return Promise.reject(data.error ? data.error : err.message)
  }
}

client.get = function (endpoint) {
  return client(endpoint, { method: 'GET' })
};

client.post = function (endpoint, body = {}, withoutAuth = false) {
  return client(endpoint, { method: 'POST', body, withoutAuth })
};

client.delete = function (endpoint, body = {}) {
  return client(endpoint, { method: 'DELETE', body })
};

client.put = function (endpoint, body = {}) {
  return client(endpoint, { method: 'PUT', body })
};