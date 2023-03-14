package sg.edu.nus.iss.workshop17.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Condition implements Serializable {
    private String main;
    private String description;
    private String icon;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    // actually not require for this workshop
    public JsonObjectBuilder toJSON() {
        return Json.createObjectBuilder()
                .add("main", this.getMain())
                .add("description", this.getDescription())
                .add("icon", this.getIcon());
    }

    public static Condition createFromJSON(JsonObject jsObj) {
        Condition cd = new Condition();
        String jsMain = jsObj.getString("main");
        String jsDescription = jsObj.getString("description");
        String jsIcon = "https://openweathermap.org/img/wn/%s@4x.png".formatted(jsObj.getString("icon"));
        cd.setMain(jsMain);
        cd.setDescription(jsDescription);
        cd.setIcon(jsIcon);
        return cd;
    }

}
