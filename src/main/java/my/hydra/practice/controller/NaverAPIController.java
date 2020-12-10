package my.hydra.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/naver")
public class NaverAPIController {

    private final String NEWS_API_URL = "https://openapi.naver.com/v1/search/news.json";
    private final String NAVER_CLIENT_ID = "4Rd8f5WH1ahavZyE5lqF";
    private final String NAVER_CLIENT_SECRET = "uHvdzB2l0w";
    @RequestMapping(value = "/GetNews",method = RequestMethod.GET)
    public String getNews(String searchText, int offset, int pageSize, String sort) {
        try {
            String url = NEWS_API_URL + "?query=" + URLEncoder.encode(searchText, "UTF-8") +
                    "&start=" + offset + "&display=" + pageSize + "&sort=" + sort;
            String responseStr = requestNaver(url);

            return responseStr;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return "ERROR";
    }
    private String requestNaver(String url) {
        try {
            URL targetUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) targetUrl.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id",NAVER_CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret",NAVER_CLIENT_SECRET);

            return getString(con);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return "FAILED";
    }

    private String getString(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine = "";
        StringBuffer response = new StringBuffer();

        if(responseCode == 200) {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            } in.close();

        }
        return response.toString();
    }
}
