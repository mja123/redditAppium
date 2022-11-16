package org.mja123.pages.login;

import org.mja123.pages.IEnums;

public enum ELoginOptions implements IEnums {
    GOOGLE("Continue with Google"),
    EMAIL("Continue with email"),
    SKIP("Skip");

    private final String value;

    ELoginOptions(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
