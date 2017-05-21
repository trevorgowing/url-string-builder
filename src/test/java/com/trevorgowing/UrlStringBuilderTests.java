package com.trevorgowing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.trevorgowing.UrlStringBuilder.basedUrlBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnit4.class)
public class UrlStringBuilderTests {

    private static final String BASE_URL = "http://trevorgowing.com";

    @Test
    public void testConstructUrlStringBuilderWithBaseUrl_shouldSetUrlToBaseUrl() {
        // Exercise SUT
        String actualUrl = basedUrlBuilder(BASE_URL).toString();

        // Verify state
        assertThat(actualUrl, is(BASE_URL));
    }

    @Test
    public void testEqualsWithDifferentUrls_shouldNotBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder("http://someotherdomain.com");

        // Verify state
        assertThat(urlStringBuilderOne.equals(urlStringBuilderTwo), is(false));
    }

    @Test
    public void testEqualsWithTheSameUrls_shouldBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder(BASE_URL);

        // Verify state
        assertThat(urlStringBuilderOne.equals(urlStringBuilderTwo), is(true));
    }

    @Test
    public void testHashCodeWithDifferentUrls_shouldNotBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder("http://someotherdomain.com");

        // Verify state
        assertThat(urlStringBuilderOne.hashCode(), is(not(equalTo(urlStringBuilderTwo.hashCode()))));
    }

    @Test
    public void testHashCodeWithTheSameUrls_shouldBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder(BASE_URL);

        // Verify state
        assertThat(urlStringBuilderOne.hashCode(), is(equalTo(urlStringBuilderTwo.hashCode())));
    }
}
