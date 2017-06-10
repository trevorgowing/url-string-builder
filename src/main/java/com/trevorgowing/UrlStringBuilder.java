package com.trevorgowing;

import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * UrlStringBuilder is a simple url string builder using {@link StringBuilder} behind the scenes.
 *
 * UrlStringBuilder offers three simple behaviours, construction with a base url or not as well as the appending of
 * paths and/or queries.
 *
 * All methods, namely: {@link #basedUrlBuilder(String)}, {@link #appendPath(String)}
 * and {@link #appendQuery(String, String)} handle {@code null} and empty parameter strings consistently.
 * Any {@code null} parameters will result in an {@link IllegalArgumentException}.
 * Any empty {@link String} parameters will result in nothing being appended.
 */
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

    /**
     * Construct a new UrlStringBuilder with no initial url.
     *
     * @return The newly created UrlStringBuilder.
     */
    public static UrlStringBuilder emptyUrlBuilder() {
        return new UrlStringBuilder();
    }

    /**
     * Constrct a new UrlStringBuilder with an initial url.
     *
     * @param baseUrl The url {@link String} to initialise this UrlStringBuilder with.
     * @return The newly created UrlStringBuilder.
     */
    public static UrlStringBuilder basedUrlBuilder(String baseUrl) {
        return new UrlStringBuilder(baseUrl);
    }

    /**
     * Append the given path to this url.
     *
     * If the given path does not contain a preceding backslash one will be prepended.
     * If the given path does contain a preceding backslash it will be removed so as to avoid duplication.
     *
     * @param path The {@link String} path to append.
     * @return This UrlStringBuilder.
     */
    public UrlStringBuilder appendPath(String path) {
        path = ofNullable(path).orElseThrow(() -> new IllegalArgumentException("Path may not be null"));

        if (!path.isEmpty()) {
            urlBuilder.append("/");
            urlBuilder.append(path.replaceAll("/", ""));
        }

        return this;
    }

    /**
     * Append the given path to this url.
     *
     * @param path The <code>int</code> path to append.
     * @return This UrlStringBuilder.
     */
    public UrlStringBuilder appendPath(int path) {
        urlBuilder.append("/").append(path);
        return this;
    }

    /**
     * Append the given path to this url.
     *
     * @param path The <code>long</code> path to append.
     * @return This UrlStringBuilder.
     */
    public UrlStringBuilder appendPath(long path) {
        urlBuilder.append("/").append(path);
        return this;
    }

    /**
     * Append the given query to this url.
     *
     * If this is the first query to be appended a preceding {@code ?} will be prepended.
     * If this is not the first query a preceding {@code &} will be prepended.
     *
     * @param queryName The {@link String} name of the query parameter to be appended.
     * @param queryValue The {@link String} value of the query parameter to be appended.
     * @return This UrlStringBuilder.
     */
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

    /**
     * Consider the {@link Object} being to this UrlStringBuilder as equal if they are of the same type
     * and their {@link #toString()} methods produce equal {@link String}s.
     *
     * @param objectToCompareTo The object to compare for equality.
     * @return {@code true} is these objects are equal as defined above or false otherwise.
     */
    @Override
    public boolean equals(Object objectToCompareTo) {
        if (this == objectToCompareTo) return true;
        if (objectToCompareTo == null || getClass() != objectToCompareTo.getClass()) return false;
        UrlStringBuilder urlStringBuilderToCompareTo = (UrlStringBuilder) objectToCompareTo;
        return Objects.equals(urlBuilder.toString(), urlStringBuilderToCompareTo.toString());
    }

    /**
     * @return An {@code int} representing the hash of the {@link String} produced by this class's {@link #toString()}
     * method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(urlBuilder.toString());
    }

    /**
     * @return A {@link String} representation of the built url.
     */
    @Override
    public String toString() {
        return urlBuilder.toString();
    }
}