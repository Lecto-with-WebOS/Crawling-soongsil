package com.lecto.crawlingsoongsil.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class StringParser {
    // Method to extract relative image URL using a regular expression
    public static String extractRelativeImageUrl(String styleAttribute) {
        return styleAttribute.replaceAll("background-image:url\\('(.+)'\\);", "$1");
    }

    // Method to concatenate base URL and relative URL using URI
    public static String concatUrls(String baseUrl, String relativeUrl) throws URISyntaxException {
        URI baseUri = new URI(baseUrl);
        URI resolvedUri = baseUri.resolve(relativeUrl);
        return resolvedUri.toString();
    }

    public static String extractImageUrl(String input) {

        if (input.startsWith("background-image:url(")) {
            // Replace the prefix with an empty string
            input = input.replace("background-image:url(", "");
        }

        if (input.endsWith(");")) {
            // Remove the last two characters
            input = input.substring(0, input.length() - 2);
        }

        return input;
    }
}

