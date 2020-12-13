package my.hydra.practice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/weather")
public class WeatherAPIController {
    private final String baseApiUrl = "http://apis.data.go.kr/1360000";
    private final String apiKey = "8NSiUwnrP48P0h9w%2F2SCHeaoHqu4cf5nw28kgmOjDDa73GKAKOZrz%2BPkFX84gVmCmbjH%2BhjzdgXPcoqCtLj4bQ%3D%3D";

    /**
     * 날씨 초단기실황조회
     * @param numOfRows
     * @param pageNo
     * @param baseDate
     * @param baseTime
     * @param nx
     * @param ny
     * @return
     */
    @RequestMapping(value ="/getUltraSrtNcst", method = RequestMethod.GET)
    public String getUltraSrtNcst(int numOfRows, int pageNo, String baseDate, String baseTime,
                                  int nx, int ny) {
        String result = "";
        try {
            String url = baseApiUrl + "/VilageFcstInfoService/getUltraSrtNcst?serviceKey="
                    + apiKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo
                    + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx
                    + "&ny=" + ny + "&dataType=JSON";
            result = requestWeather(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 날씨 초단기예보조
     * @param numOfRows
     * @param pageNo
     * @param baseDate
     * @param baseTime
     * @param nx
     * @param ny
     * @return
     */
    @RequestMapping(value ="/getUltraSrtFcst", method = RequestMethod.GET)
    public String getUltraSrtFcst(int numOfRows, int pageNo, String baseDate, String baseTime,
                                  int nx, int ny) {
        String result = "";
        try {
            String url = baseApiUrl + "/VilageFcstInfoService/getUltraSrtFcst?serviceKey="
                    + apiKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo
                    + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx
                    + "&ny=" + ny + "&dataType=JSON";
            result = requestWeather(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 날씨 동네예보조회
     * @param numOfRows
     * @param pageNo
     * @param baseDate
     * @param baseTime
     * @param nx
     * @param ny
     * @return
     */
    @RequestMapping(value ="/getVilageFcst", method = RequestMethod.GET)
    public String getVilageFcst(int numOfRows, int pageNo, String baseDate, String baseTime,
                                  int nx, int ny) {
        String result = "";
        try {
            String url = baseApiUrl + "/VilageFcstInfoService/getVilageFcst?serviceKey="
                    + apiKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo
                    + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx
                    + "&ny=" + ny + "&dataType=JSON";
            result = requestWeather(url);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    private String requestWeather(String url) {
        try {
            URL targetUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) targetUrl.openConnection();

            con.setRequestMethod("GET");

            return getString(con);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return "FAILED";
    }

    static String getString(HttpURLConnection con) throws IOException {
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
