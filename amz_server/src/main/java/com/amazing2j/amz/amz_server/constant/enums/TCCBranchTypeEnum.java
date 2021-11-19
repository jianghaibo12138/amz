package com.amazing2j.amz.amz_server.constant.enums;

import com.amazing2j.amz.amz_server.constant.consist.tcc.TCCBranchType;

public enum TCCBranchTypeEnum {
    TRY_BRANCH(TCCBranchType.TRY_BRANCH),
    CONFIRM_BRANCH(TCCBranchType.CONFIRM_BRANCH),
    CANCEL_BRANCH(TCCBranchType.CANCEL_BRANCH),
    ;

    private Integer type;

    TCCBranchTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
