import { listTopInvokeInterfaceInfoUsingGet } from '@/services/kongapi-backend/analysisController';
import { PageContainer } from '@ant-design/pro-components';
import '@umijs/max';
import ReactEcharts from 'echarts-for-react';
import React, { useEffect } from 'react';

const InterfaceAnalysis: React.FC = () => {
  const [data, setData] = React.useState<API.InterfaceInfoVO[]>([]);
  const [loading, setLoading] = React.useState(true);

  useEffect(() => {
    try {
      listTopInvokeInterfaceInfoUsingGet()
        .then((res) => {
          setData(res.data);
          setLoading(false);
        })
        .catch((err) => {
          console.log(err);
          setLoading(false);
        });
    } catch (e) {
      console.log(e);
      setLoading(false);
    }
  }, []);

  const chartData = data.map((item) => {
    return {
      value: item.totalNum,
      name: item.name,
    };
  });

  const options = {
    title: {
      text: '接口总调用次数统计TOP3',
      left: 'center',
    },
    tooltip: {
      trigger: 'item',
    },
    legend: {
      orient: 'vertical',
      left: 'left',
    },
    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: '50%',
        data: chartData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)',
          },
        },
      },
    ],
  };

  return (
    <PageContainer>
      <ReactEcharts showLoading={loading} option={options} />
    </PageContainer>
  );
};
export default InterfaceAnalysis;
