package com.econetwireless.utils.formatters;

import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.execeptions.InvalidMobileNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by taurai on 10/31/15.
 */
public class MobileNumberUtils {

    private final Logger LOGGER = LoggerFactory.getLogger(MobileNumberUtils.class);

    private MobileNumberUtils() {

    }

    public static String formatMobileNumber(final String mobileNumber) {
        String newMobileNumber = removeAllSpaces( mobileNumber);
        final String invalidMobileNumberMessage = "Invalid Mobile Number Supplied";
        if (newMobileNumber.length() > 9) {
            newMobileNumber = trimMobile(newMobileNumber);
        }
        try {
            if (isNumeric(newMobileNumber)) {
                if (newMobileNumber.startsWith("77") || newMobileNumber.startsWith("78")) {
                    return newMobileNumber;
                } else {
                    throw new InvalidMobileNumberException(ResponseCode.INVALID_REQUEST, invalidMobileNumberMessage);
                }
            } else {
                throw new InvalidMobileNumberException(ResponseCode.INVALID_REQUEST, invalidMobileNumberMessage);
            }
        } catch (Exception ex) {
            LOGGER.error("Error on formatting mobile : ", ex);
            throw new InvalidMobileNumberException(ResponseCode.INVALID_REQUEST, invalidMobileNumberMessage);
        }
    }
    private static String trimMobile(String mobileNumber) {
        if(canTrimMobile(mobileNumber)) {
            return mobileNumber.substring(mobileNumber.length() - 9);
        }
        throw new InvalidMobileNumberException(ResponseCode.INVALID_REQUEST, "Invalid Mobile Number Supplied "+mobileNumber);

    }

    private static boolean canTrimMobile(String mobileNumber) {
       if(mobileNumber.startsWith("0") && mobileNumber.length() == 10) {
            return true;
        }
        return canTrimMobileWithCountryCode(mobileNumber);
    }

    private static boolean canTrimMobileWithCountryCode(String mobileNumber) {
        final boolean is00263Format = mobileNumber.startsWith("00263") && mobileNumber.length() == 14;
        final boolean is263Format = mobileNumber.startsWith("263") && mobileNumber.length() == 12;
        final boolean isPlus263Format = mobileNumber.startsWith("+263") && mobileNumber.length() == 13;
        return is00263Format || is263Format || isPlus263Format;
    }
    public static String formatMobileToInternationalMode(String mobileNumber) throws InvalidMobileNumberException {
        return "263"+formatMobileNumber(mobileNumber);
    }

    public static boolean isNumeric(final String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static String removeAllSpaces(final String msisdn) {
        return msisdn.replaceAll(" ", "");
    }

    public static void main(String[] args) {
        try {
            LOGGER.info(">>>>> 1. Result : "+formatMobileNumber("785898366"));
            LOGGER.info(">>>>> 2. Result : "+formatMobileNumber("0785898366"));
            LOGGER.info(">>>>> 3. Result : "+formatMobileNumber("00263785898366"));
            LOGGER.info(">>>>> 4. Result : "+formatMobileNumber("+263785898366"));
            LOGGER.info(">>>>> 5. Result : "+formatMobileNumber("263785898366"));
            LOGGER.info(">>>>> 6. Result : "+formatMobileNumber("2637858983667"));
        } catch (Exception e) {
            LOGGER.error("Error In Main", e);
        }
    }

    public static boolean isInitiatorSameAsBeneficiary(String initiatorMobileNumber, String beneficiaryMobileNumber) {
        try {
            return formatMobileNumber(initiatorMobileNumber).equalsIgnoreCase(formatMobileNumber(beneficiaryMobileNumber));
        } catch (InvalidMobileNumberException e) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.error("Error Checking Initiator if Same with beneficiary : ", e);
            }
            return false;
        }
    }
}
