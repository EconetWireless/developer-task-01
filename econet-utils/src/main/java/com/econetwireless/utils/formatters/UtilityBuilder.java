package com.econetwireless.utils.formatters;

public class UtilityBuilder {

    private UtilityBuilder() {
        super();
    }

    public static void buildErrorMessage(final StringBuilder builder, final boolean isMissingValue, final String errorMessage) {
        if (isMissingValue) {
            if (builder.length() <= 0) {
                builder.append(errorMessage);
            } else {
                builder.append(", ").append(errorMessage);
            }
        }
    }
}