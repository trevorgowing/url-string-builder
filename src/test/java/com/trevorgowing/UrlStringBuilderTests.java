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

    @Test(expected = IllegalArgumentException.class)
    public void testConstructUrlStringBuilderWithNullBaseUrl_shouldThrowIllegalArgumentException() {
        // Exercise SUT
        basedUrlBuilder(null);
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void testAppendQueryWithNullQueryName_shouldThrowIllegalArgumentException() {
        // Set up fixture
        String queryValue = "log";

        // Exercise
        emptyUrlBuilder().appendQuery(null, queryValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendQueryWithNullQueryValue_shouldThrowIllegalArgumentException() {
        // Set up fixture
        String queryName = "log";

        // Exercise SUT
        emptyUrlBuilder().appendQuery(queryName, null);
    }

    @Test
    public void testAppendQueryWithEmptyQueryName_shouldNotAppendQuery() {
        // Set up fixture
        String emptyQueryName = "";
        String queryValue = "log";

        // Exercise SUT
        String actualUrl = emptyUrlBuilder().appendQuery(emptyQueryName, queryValue).toString();

        // Verify behaviour
        assertThat(actualUrl, isEmptyString());
    }

    @Test
    public void testAppendQueryWithEmptyQueryValue_shouldNotAppendQuery() {
        // Set up fixture
        String queryName = "type";
        String emptyQueryValue = "";

        // Exercise SUT
        String actualUrl = emptyUrlBuilder().appendQuery(queryName, emptyQueryValue).toString();

        // Verify behaviour
        assertThat(actualUrl, isEmptyString());
    }

    @Test
    public void testAppendQueryWithValidFirstQuery_shouldAppendQueryWithLeadingQuestionMark() {
        // Set up fixture
        String queryName = "type";
        String queryValue = "log";
        String expectedUrl = "?" + queryName + "=" + queryValue;

        // Exercise SUT
        String actualUrl = emptyUrlBuilder().appendQuery(queryName, queryValue).toString();

        // Verify behaviour
        assertThat(actualUrl, is(expectedUrl));
    }

    @Test
    public void testAppendQueryWithValidSecondQuery_shouldAppendQueryWithLeadingAmpersand() {
        // Set up fixture
        UrlStringBuilder urlStringBuilder = emptyUrlBuilder().appendQuery("type", "log");

        String secondQueryName = "date";
        String secondQueryValue = "20170521";

        String expectedUrl = "?type=log&date=20170521";

        // Exercise SUT
        String actualUrl = urlStringBuilder.appendQuery(secondQueryName, secondQueryValue).toString();

        // Very behaviour
        assertThat(actualUrl, is(expectedUrl));
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
