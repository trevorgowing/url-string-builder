package com.trevorgowing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.trevorgowing.UrlStringBuilder.basedUrlBuilder;
import static com.trevorgowing.UrlStringBuilder.emptyUrlBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(JUnit4.class)
public class UrlStringBuilderTests {

    private static final String BASE_URL = "http://trevorgowing.com";

    @Test
    public void testConstructUrlStringBuilderWithBaseUrl_shouldSetUrlToBaseUrl() {
        // Exercise SUT
        String actualUrl = basedUrlBuilder(BASE_URL).toString();

        // Verify behaviour
        assertThat(actualUrl, is(BASE_URL));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendPathWithNull_shouldThrowIllegalArgumentException() {
        // Exercise SUT
        emptyUrlBuilder().appendPath(null);
    }

    @Test
    public void testAppendPathWithEmptyString_shouldNotAppendBackslash() {
        // Exercise SUT
        String actualUrl = emptyUrlBuilder().appendPath("").toString();

        // Verify behaviour
        assertThat(actualUrl, isEmptyString());
    }

    @Test
    public void testAppendPathWithoutBackslash_shouldAppendPathWithExactlyOneBackslash() {
        // Set up fixture
        String pathWithoutBackslash = "home";
        String expectedUrl = "/home";

        // Exercise SUT
        String actualUrl = emptyUrlBuilder().appendPath(pathWithoutBackslash).toString();

        // Verify behaviour
        assertThat(actualUrl, is(expectedUrl));
    }

    @Test
    public void testAppendPathWithBackslash_shouldAppendPathWithExactlyOneBackslash() {
        // Set up fixture
        String pathWithBackslash = "/home";

        // Exercise SUT
        String actualUrl = emptyUrlBuilder().appendPath(pathWithBackslash).toString();

        // Verify behaviour
        assertThat(actualUrl, is(pathWithBackslash));
    }

    @Test
    public void testEqualsWithDifferentUrls_shouldNotBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder("http://someotherdomain.com");

        // Verify behaviour
        assertThat(urlStringBuilderOne.equals(urlStringBuilderTwo), is(false));
    }

    @Test
    public void testEqualsWithTheSameUrls_shouldBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder(BASE_URL);

        // Verify behaviour
        assertThat(urlStringBuilderOne.equals(urlStringBuilderTwo), is(true));
    }

    @Test
    public void testHashCodeWithDifferentUrls_shouldNotBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder("http://someotherdomain.com");

        // Verify behaviour
        assertThat(urlStringBuilderOne.hashCode(), is(not(equalTo(urlStringBuilderTwo.hashCode()))));
    }

    @Test
    public void testHashCodeWithTheSameUrls_shouldBeEqual() {
        // Set up fixture
        UrlStringBuilder urlStringBuilderOne = basedUrlBuilder(BASE_URL);
        UrlStringBuilder urlStringBuilderTwo = basedUrlBuilder(BASE_URL);

        // Verify behaviour
        assertThat(urlStringBuilderOne.hashCode(), is(equalTo(urlStringBuilderTwo.hashCode())));
    }
}
