package com.econetwireless.utils.formatters;

import com.econetwireless.utils.enums.ResponseCode;

/**
 * Created by taurai on 7/10/16.
 */
public class ResponseMarker {

    private ResponseMarker() {
        super();
    }

    public static String getMarker(final String responseCode) {
        final boolean isSuccess = ResponseCode.SUCCESS.getCode().equalsIgnoreCase(responseCode);
        return isSuccess ? "[SUCCESS] " : "[FAILED] ";
    }
}
