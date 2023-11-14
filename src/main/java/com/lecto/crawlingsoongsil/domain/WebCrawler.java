package com.lecto.crawlingsoongsil.domain;

import com.google.gson.Gson;
import com.lecto.crawlingsoongsil.utils.StringParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

    public static void main(String[] args) {
        // URL of the page you want to crawl
        String url = "https://fun.ssu.ac.kr/ko/program/all/list/all/";

        // Base URL of the website
        String baseUrl = "https://fun.ssu.ac.kr";

        try {
            // Connect to the website and get the HTML document
            for (int i = 1; i < 300; i++) {
                Document document = Jsoup.connect(url + i).get();

                // Select the elements you want to extract
                Elements items = document.select("li > div[data-module=eco][data-role=item]");

                // Create a list to store the crawled data
                List<CrawledData> dataList = new ArrayList<>();

                // Loop through each item and extract the data
                for (Element item : items) {
                    String title = item.select(".title").text();
                    String department = item.select(".department .institution").text();
                    String date = item.select(".department small time").attr("datetime");

                    // Extract the relative image URL from the style attribute
                    String relativeImageUrl = item.select(".cover").attr("style");
                    relativeImageUrl = StringParser.extractRelativeImageUrl(relativeImageUrl);
                    String extractImageUrl = StringParser.extractImageUrl(relativeImageUrl);

                    // Combine the base URL with the relative image URL using URI
                    String imageUrl = StringParser.concatUrls(baseUrl, extractImageUrl);

                    // Add more lines to extract other data as needed

                    // Create a CrawledData object and add it to the list
                    CrawledData crawledData = new CrawledData(title, department, date, imageUrl);
                    dataList.add(crawledData);
                }

                // Convert the list to JSON using Gson
                Gson gson = new Gson();
                String json = gson.toJson(dataList);

                // Save the JSON data to a file
                try (FileWriter writer = new FileWriter("crawled_data" + i + ".json")) {
                    writer.write(json);
                    System.out.println("Data saved to 'crawled_data" + i + ".json'");
                }
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }
}