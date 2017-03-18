package com.econetwireless.utils.execeptions;

import com.econetwireless.utils.enums.ResponseCode;

/**
 * Created by tnyamakura on 18/3/2017.
 */
public class InvalidMobileNumberException extends EpayException {

    public InvalidMobileNumberException(ResponseCode responseCode, String message) {
        super(responseCode, message);
    }
}
