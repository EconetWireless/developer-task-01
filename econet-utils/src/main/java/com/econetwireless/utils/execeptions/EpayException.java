package com.econetwireless.utils.execeptions;

import com.econetwireless.utils.enums.ResponseCode;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public class EpayException extends RuntimeException {
    private ResponseCode responseCode;

    public EpayException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

}
