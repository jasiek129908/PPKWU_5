package com.example.ppkwu_5;


import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.Address;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PPKWU_5 {

    public static String parseResponse(String profession) throws IOException {
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
        String image = "";

        String street = "";
        String addressLocality = "";
        String postalCode = "";
        String country = "";

        for (Element el : select) {
            JSONObject jsonObject = new JSONObject(el.data());
            try {
                name = jsonObject.getString("name");
                telephone = jsonObject.getString("telephone");
                email = jsonObject.getString("email");
                website = jsonObject.getString("sameAs");
                image = jsonObject.getString("image");

                JSONObject jsonObject2 = jsonObject.getJSONObject("address");
                street = jsonObject2.getString("streetAddress");
                addressLocality = jsonObject2.getString("addressLocality");
                postalCode = jsonObject2.getString("postalCode");
                country = jsonObject2.getString("addressCountry");
            } catch (JSONException e) {
                System.out.println(e);
            }
            String paramToApi = String.format("name=%s&telephone=%s&email=%s&website=%s&street=%s&postalCode=%s&addressLocality=%s",
                    name, telephone, email, website, street, postalCode, addressLocality);

            htmlResponse += String.format("<div>\n" +
                            "    <p>%s</p>\n" +
                            "    <p><img src=\"%s\" alt=\"componay image\"></img>\n" +
                            "    <p>telephone: %s</p>\n" +
                            "    <p>Email: %s</p>\n" +
                            "    <p>Website: %s</p>\n" +
                            "  \n" +
                            "    <p>Address</p>" +
                            "    <p>%s</p>\n" +
                            "    <p>%s</p>\n" +
                            "    <p>%s</p>\n" +
                            "    <p>%s</p>\n" +
                            "    <a href=\"/api/card/?%s>\"><button>wygeneruj vCard</button></a>\n" +
                            "</div><br>",
                    name, image, telephone, email, website, street, postalCode, addressLocality, country, paramToApi);
        }
        htmlResponse += "</body></html>";
        return htmlResponse;
    }

    public static String generateCard(String name, String telephone, String email, String website, String street, String postalCode, String addressLocality) {
        VCard vcard = new VCard();
        Address address = new Address();
        address.setPostalCode(postalCode);
        address.setStreetAddress(street);
        address.setRegion(addressLocality);
        vcard.setFormattedName(name);
        vcard.addTelephoneNumber(telephone);
        vcard.addEmail(email);
        vcard.addUrl(website);
        vcard.addAddress(address);
        return Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
    }

}
