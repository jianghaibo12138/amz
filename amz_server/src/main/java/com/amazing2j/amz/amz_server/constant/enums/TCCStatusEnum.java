package com.amazing2j.amz.amz_server.constant.enums;

import com.amazing2j.amz.amz_server.constant.consist.TCCStatus;

public enum TCCStatusEnum {
    INITIAL(TCCStatus.INITIAL),
    PREPARED(TCCStatus.PREPARED),
    SUBMITTED(TCCStatus.SUBMITTED),
    FINISHED(TCCStatus.FINISHED),
    ROLLBACK(TCCStatus.ROLLBACK);

    private int status;

    TCCStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TCCStatusEnum nextTCCStatus(int currentStatus) {
        switch (currentStatus) {
            case TCCStatus.INITIAL:
                return TCCStatusEnum.PREPARED;
            case TCCStatus.PREPARED:
                return TCCStatusEnum.SUBMITTED;
            case TCCStatus.SUBMITTED:
            case TCCStatus.ROLLBACK:
                return TCCStatusEnum.FINISHED;
        }
        return null;
    }
}
