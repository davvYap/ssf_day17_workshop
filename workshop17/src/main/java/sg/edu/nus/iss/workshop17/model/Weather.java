package sg.edu.nus.iss.workshop17.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Weather {
    private String city;
    private String temperature;
    private Long weatherTimeStamp;
    private Long sunsetTimeStamp;
    private Long sunriseTimeStamp;
    private List<Condition> conditionList = new LinkedList<>();

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Long getWeatherTimeStamp() {
        return weatherTimeStamp;
    }

    public void setWeatherTimeStamp(Long weatherTimeStamp) {
        this.weatherTimeStamp = weatherTimeStamp;
    }

    public Long getSunsetTimeStamp() {
        return sunsetTimeStamp;
    }

    public void setSunsetTimeStamp(Long sunsetTimeStamp) {
        this.sunsetTimeStamp = sunsetTimeStamp;
    }

    public Long getSunriseTimeStamp() {
        return sunriseTimeStamp;
    }

    public void setSunriseTimeStamp(Long sunriseTimeStamp) {
        this.sunriseTimeStamp = sunriseTimeStamp;
    }

    public static Weather createFromJSON(String json) throws IOException {
        Weather w = new Weather();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader jr = Json.createReader(is);
            JsonObject jsObj = jr.readObject();
            w.setCity(jsObj.getString("name"));

            JsonNumber weatherDate = jsObj.getJsonNumber("dt");
            w.setWeatherTimeStamp(weatherDate.longValue());

            // to retrieve from main object in json
            JsonObject mainObj = jsObj.getJsonObject("main");
            JsonNumber jsTemp = mainObj.getJsonNumber("temp");
            w.setTemperature(jsTemp.toString());

            JsonObject sysObj = jsObj.getJsonObject("sys");
            JsonNumber jsSunrise = sysObj.getJsonNumber("sunrise");
            w.setSunriseTimeStamp(jsSunrise.longValue());
            JsonNumber jsSunset = sysObj.getJsonNumber("sunset");
            w.setSunsetTimeStamp(jsSunset.longValue());

            // set condition list
            List<Condition> wConditions = jsObj.getJsonArray("weather").stream()
                    .map(wea -> (JsonObject) wea)
                    .map(wea -> Condition.createFromJSON(wea))
                    .toList();
            w.setConditionList(wConditions);
        }
        return w;
    }

}
