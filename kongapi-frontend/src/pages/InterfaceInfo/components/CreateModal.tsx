import { ProColumns, ProTable } from '@ant-design/pro-components';
import '@umijs/max';
import { Modal } from 'antd';
import React from 'react';

export type props = {
  columns: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfo) => Promise<void>;
  visible: boolean;
};
const CreateModal: React.FC<props> = (props) => {
  const { columns, visible, onSubmit, onCancel } = props;
  return (
    <Modal footer={null} visible={visible} onCancel={() => onCancel?.()}>
      <ProTable type="form" columns={columns} onSubmit={ async (values) => {
        onSubmit?.(values);
      }}/>
    </Modal>
  );
};
export default CreateModal;
