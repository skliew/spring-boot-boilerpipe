package com.example;

public class ExtractTextResult {
    private final boolean result;
    private final String text;
    private static final ExtractTextResult defaultResult = new ExtractTextResult(false, "");

    public ExtractTextResult(boolean result, String text) {
        this.result = result;
        this.text = text;
    }

    public boolean getResult() {
        return result;
    }

    public final String getText() {
        return text;
    }

    public static ExtractTextResult getDefaultResult() {
        return defaultResult;
    }

}
