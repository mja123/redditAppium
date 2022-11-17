package org.mja123.pages.signUp;

import org.mja123.pages.IEnums;

public enum ESignUpOptions implements IEnums {
    GOOGLE("Continue with Google"),
    EMAIL("Continue with email"),
    SKIP("Skip");

    private final String value;

    ESignUpOptions(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
