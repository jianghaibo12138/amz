package com.amazing2j.amz.amz_server.service.trs_manager;

public class TrsRunner implements Runnable {

    @Override
    public void run() {
        new TCCService().getTCCTx();
    }
}
