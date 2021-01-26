import React from 'react';
import { useDispatch } from 'react-redux';
import { Button, Typography, Form, Input } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import './LoginForm.css'
import { login } from './authSlice';

const { Title } = Typography;

export function LoginForm() {

  const dispatch = useDispatch();
  const onFinish = values => {
    const { username, password } = values;
    dispatch(login({ username: username, password: password }));
  };

  return (
    <div className="login-container">
      <Title className="login-title" level={1}>Social network for OTUS
        Homework</Title>
      <Form
        className="login-form"
        name="normal_login"
        initialValues={{
          remember: true
        }}
        onFinish={onFinish}
      >
        <Form.Item
          name="username"
          rules={[
            {
              required: true,
              message: 'Please input your Username!'
            }
          ]}
        >
          <Input
            prefix={<UserOutlined className="site-form-item-icon"/>}
            placeholder="Username"
          />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: 'Please input your Password!'
            }
          ]}
        >
          <Input
            prefix={<LockOutlined className="site-form-item-icon"/>}
            type="password"
            placeholder="Password"
          />
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            className="login-form-button"
          >
            Log in
          </Button>
          Or <a href="">register now!</a>
        </Form.Item>
      </Form>
    </div>
  );
};