package me.synology.freash97.common.enumconfig;

public enum ResultValue {
    succ("success"),
    fail("fail");

    private final String value;

    ResultValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
