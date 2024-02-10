package com.tkzc00.kongapibackend.model.vo;

import com.tkzc00.kongapicommon.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoVO extends InterfaceInfo {
    private static final long serialVersionUID = 3234227491231164080L;

    private Integer totalNum;
}
