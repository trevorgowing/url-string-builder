package com.trevorgowing;

import java.util.Objects;

import static java.util.Optional.ofNullable;

public class UrlStringBuilder {

    private final StringBuilder urlBuilder;
    private boolean firstQuery = true;

    private UrlStringBuilder() {
        this.urlBuilder = new StringBuilder();
    }

    private UrlStringBuilder(String baseUrl) {
        baseUrl = ofNullable(baseUrl)
                .orElseThrow(() -> new IllegalArgumentException("Base url may not be null"));

        this.urlBuilder = new StringBuilder(baseUrl);
    }

    public static UrlStringBuilder emptyUrlBuilder() {
        return new UrlStringBuilder();
    }

    public static UrlStringBuilder basedUrlBuilder(String baseUrl) {
        return new UrlStringBuilder(baseUrl);
    }

    public UrlStringBuilder appendPath(String pathWithoutBackslash) {
        String path = ofNullable(pathWithoutBackslash)
                .orElseThrow(() -> new IllegalArgumentException("Path may not be null"));

        if (!path.isEmpty()) {
            urlBuilder.append("/");
            urlBuilder.append(path.replaceAll("/", ""));
        }

        return this;
    }

    public UrlStringBuilder appendQuery(String queryName, String queryValue) {
        queryName = ofNullable(queryName)
                .orElseThrow(() -> new IllegalArgumentException("Query name may not be null"));

        queryValue = ofNullable(queryValue)
                .orElseThrow(() ->  new IllegalArgumentException("Query value may not be null"));

        if (!queryName.isEmpty() && !queryValue.isEmpty()) {
            urlBuilder.append(firstQuery ? "?" : "&").append(queryName).append("=").append(queryValue);
            firstQuery = false;
        }

        return this;
    }

    @Override
    public boolean equals(Object objectToCompareTo) {
        if (this == objectToCompareTo) return true;
        if (objectToCompareTo == null || getClass() != objectToCompareTo.getClass()) return false;
        UrlStringBuilder urlStringBuilderToCompareTo = (UrlStringBuilder) objectToCompareTo;
        return Objects.equals(urlBuilder.toString(), urlStringBuilderToCompareTo.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlBuilder.toString());
    }

    @Override
    public String toString() {
        return urlBuilder.toString();
    }
}
