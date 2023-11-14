package com.lecto.crawlingsoongsil.domain;

class CrawledData {
    String title;
    String department;
    String date;
    String imageUrl;

    CrawledData(String title, String department, String date, String imageUrl) {
        this.title = title;
        this.department = department;
        this.date = date;
        this.imageUrl = imageUrl;
    }
}