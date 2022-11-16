package org.mja123.pages.login;

import org.mja123.pages.IEnums;

public enum EContext implements IEnums {

    WEB("WEBVIEW_com.google.android.gms.ui"),
    NATIVE("NATIVE_APP");
    private final String value;

    EContext(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
