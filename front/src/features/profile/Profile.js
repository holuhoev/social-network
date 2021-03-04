import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  fetchProfile,
  selectProfile,
  selectProfileStatus, selectProfileUpdateStatus, updateProfile
} from './profileSlice';
import { Button, Form, Input, InputNumber, Select } from 'antd';


const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 }
};
const tailLayout = {
  wrapperCol: { offset: 8, span: 16 }
};

export const Profile = () => {
  const dispatch = useDispatch();

  const profileStatus = useSelector(selectProfileStatus);
  const profileUpdateStatus = useSelector(selectProfileUpdateStatus);

  useEffect(() => {
    if (profileStatus === 'idle') {
      dispatch(fetchProfile())
    }
  }, [profileStatus]);

  const profile = useSelector(selectProfile);
  const [form] = Form.useForm();

  if (!profile) {
    return null;
  }

  const onFinish = (values) => {
    dispatch(updateProfile(values))
  };

  return (
    <Form
      {...layout}
      form={form}
      name="control-hooks"
      onFinish={onFinish}
      disabled={profileUpdateStatus === 'loading'}
      initialValues={{
        'firstName': profile.firstName,
        'lastName': profile.lastName,
        'age': profile.age,
        'interests': profile.interests,
        'city': profile.city
      }}
    >
      <Form.Item name="firstName" label="First name"
                 rules={[{ required: true }]}>
        <Input disabled={profileUpdateStatus === 'loading'}/>
      </Form.Item>
      <Form.Item name="lastName" label="Last name"
                 rules={[{ required: true }]}>
        <Input disabled={profileUpdateStatus === 'loading'}/>
      </Form.Item>
      <Form.Item name="age" label="Age"
                 rules={[{ required: true }]}>
        <InputNumber min={8} max={120}
                     disabled={profileUpdateStatus === 'loading'}/>
      </Form.Item>
      <Form.Item name="city" label="City"
                 rules={[{ required: true }]}>
        <Input disabled={profileUpdateStatus === 'loading'}/>
      </Form.Item>
      <Form.Item
        name="interests"
        label="Interests"
        rules={[{ required: true }]}
      >
        <Input.TextArea maxLength={200}
                        disabled={profileUpdateStatus === 'loading'}/>
      </Form.Item>
      <Form.Item {...tailLayout}>
        <Button loading={profileUpdateStatus === 'loading'} type="primary"
                htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};
