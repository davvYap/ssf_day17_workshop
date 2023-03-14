package sg.edu.nus.iss.workshop17.model;

import java.util.LinkedList;
import java.util.List;

public class Weathers {
    List<Weather> weathers = new LinkedList<>();

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public void addWeatherToList(Weather w) {
        this.weathers.add(w);
    }

}
