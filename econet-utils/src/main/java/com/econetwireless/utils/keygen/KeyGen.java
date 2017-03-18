package com.econetwireless.utils.keygen;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by tnyamakura on 29/11/2016.
 */
public class KeyGen {

    private KeyGen() {
        super();
    }

    public static Long getUniqueId() {
        final StringBuilder builder = new StringBuilder();
        builder.append(System.currentTimeMillis());
        builder.append(RandomStringUtils.randomNumeric(5));
        return new Long(builder.toString());
    }


}
