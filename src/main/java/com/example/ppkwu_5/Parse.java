package com.example.ppkwu_5;


import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parse {

    public static void parseResponse(String profession) throws IOException {
        String htmlResponse = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>BusinessCard from Panorama Firm</title>" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "</head>\n" +
                "<body><div>Result for porffesion: <b>" + profession + "</b>:</div>";

        Document doc = Jsoup.connect(profession).get();
        Elements select = doc.select("[type=\"application/ld+json\"]");

        JSONObject jsonParentObject = new JSONObject();
        for (Element li : select) {
            System.out.println(li);
            System.out.println("\n\n");
        }
    }
}
