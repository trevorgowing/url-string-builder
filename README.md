[![Build Status](https://travis-ci.org/trevorgowing/url-string-builder.svg?branch=master)](https://travis-ci.org/trevorgowing/url-string-builder)  

# Url String Builder

A simple java url string builder using [StringBuilder](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html) behind the scenes.

UrlStringBuilder offers three simple behaviours, construction with a base url or not as well as the appending of paths and/or queries.

## Usage

### Construction

Static builder methods are provided for ease of construction.

`UrlStringBuilder myUrlBuilder = UrlStringBuilder.emptyUrlBuilder()` or  
`UrlStringBuilder myUrlBuilder = UrlStringBuilder.basedUrlBuilder("http://trevorgowing.com")`.

### Appending paths

Paths can be appended with or without a preceding backslash.

`myUrlBuilder.appendPath("/home")` or `myUrlBuilder.appendPath("home")`

### Appending queries

UrlStringBuilder takes care of appending the appropriate preceding character, either: '?' or '&amp;'.

`myUrlBuilder.appendQuery("type", "log")` and `myUrlBuilder.appendQuery("date", "20170521")`

### ToString

When you are done building your url just call `myUrlBuilder.toString()` to get your url string 
`"http://trevorgowing.com/home?type=log&date=20170521`.

## Null and empty [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) parameter handling

All methods, namely: `basedUrlBuilder(String baseUrl)`, `appendPath(String path)` and 
`appendQuery(String queryName, String queryValue)` handle `null` and empty 
[String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) parameters consistently.

Any `null` parameters will result in an 
[IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html).  
Any empty [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) parameters will result in nothing 
being appended.