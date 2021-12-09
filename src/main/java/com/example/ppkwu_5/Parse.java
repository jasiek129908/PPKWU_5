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

        String name = "";
        String telephone = "";
        String email = "";
        String website = "";

        String street = "";
        String  addressLocality= "";
        String  postalCode= "";
        String  country= "";

        for (Element el : select) {
            JSONObject jsonObject = new JSONObject(el.data());
            System.out.println(jsonObject);
            name = jsonObject.getString("name");
            telephone = jsonObject.getString("telephone");
            email = jsonObject.getString("email");
            website = jsonObject.getString("sameAs");

            JSONObject jsonObject2= jsonObject.getJSONObject("address");
            street = jsonObject2.getString("streetAddress");
            addressLocality = jsonObject2.getString("addressLocality");
            postalCode = jsonObject2.getString("postalCode");
            country = jsonObject2.getString("addressCountry");

            String formatedString = String.format(" ")
        }
    }

}
