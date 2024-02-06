import {ProColumns, ProFormInstance, ProTable} from '@ant-design/pro-components';
import '@umijs/max';
import { Modal } from 'antd';
import React, { useEffect, useRef } from 'react';

export type props = {
  values: API.InterfaceInfo;
  columns: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfo) => Promise<void>;
  visible: boolean;
};
const UpdateModal: React.FC<props> = (props) => {
  const { columns, visible, onSubmit, onCancel, values } = props;
  const formRef = useRef<ProFormInstance>();
  useEffect(() => {
    formRef?.current?.setFieldsValue(values);
  }, [values]);
  return (
    <Modal footer={null} visible={visible} onCancel={() => onCancel?.()}>
      <ProTable
        type="form"
        columns={columns}
        formRef={formRef}
        onSubmit={async (values) => {
          onSubmit?.(values);
        }}
      />
    </Modal>
  );
};
export default UpdateModal;
