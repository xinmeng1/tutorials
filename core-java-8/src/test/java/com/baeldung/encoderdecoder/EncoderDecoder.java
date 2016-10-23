package com.baeldung.encoderdecoder;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EncoderDecoder {

    private static final String URL = "http://www.baeldung.com?key1=value+1&key2=value%40%21%242&key3=value%253";
    private static final Logger LOGGER = LoggerFactory.getLogger(EncoderDecoder.class);

    private String encodeValue(String value) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error encoding parameter {}", e.getMessage(), e);
        }
        return encoded;
    }


    private String decode(String value) {
        String decoded = null;
        try {
            decoded = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error encoding parameter {}", e.getMessage(), e);
        }
        return decoded;
    }

    @Test
    public void givenURL_whenAnalyze_thenCorrect() throws Exception {
        URL url = new URL(URL);

        Assert.assertThat(url.getProtocol(), CoreMatchers.is("http"));
        Assert.assertThat(url.getHost(), CoreMatchers.is("www.baeldung.com"));
        Assert.assertThat(url.getQuery(), CoreMatchers.is("key1=value+1&key2=value%40%21%242&key3=value%253"));
    }

    @Test
    public void givenRequestParam_whenUTF8Scheme_thenEncode() throws Exception {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("key1", "value 1");
        requestParams.put("key2", "value@!$2");
        requestParams.put("key3", "value%3");

        String encodedQuery = requestParams.keySet().stream()
                .map(key -> key + "=" + encodeValue(requestParams.get(key)))
                .collect(Collectors.joining("&"));
        String encodedURL = "http://www.baeldung.com?" + encodedQuery;

        Assert.assertThat(URL, CoreMatchers.is(encodedURL));
    }

    @Test
    public void givenRequestParam_whenUTF8Scheme_thenDecodeRequestParams() throws Exception {
        URL url = new URL(URL);
        String query = url.getQuery();

        String decodedQuery = Arrays.stream(query.split("&"))
                .map(param -> param.split("=")[0] + "=" + decode(param.split("=")[1]))
                .collect(Collectors.joining("&"));

        Assert.assertEquals(
                "http://www.baeldung.com?key1=value 1&key2=value@!$2&key3=value%3", url.getProtocol() + "://" + url.getHost() + "?" + decodedQuery);
    }

}