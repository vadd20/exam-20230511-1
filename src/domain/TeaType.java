package domain;

/**
 * Тип чая.
 *
 * @author Vadim Podogov
 * @since 2024.03.31
 */
public class TeaType {
    private long id;
    private String name;
    private int timeFrom;
    private int timeTo;
    private int temperatureFrom;
    private int temperatureTo;

    public TeaType(long id, String name, int timeFrom, int timeTo, int temperatureFrom, int temperatureTo) {
        this.id = id;
        this.name = name;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.temperatureFrom = temperatureFrom;
        this.temperatureTo = temperatureTo;
    }

    public long getId() {
        return id;
    }

    public int getTimeFrom() {
        return timeFrom;
    }

    public int getTimeTo() {
        return timeTo;
    }

    public int getTemperatureFrom() {
        return temperatureFrom;
    }

    public int getTemperatureTo() {
        return temperatureTo;
    }
}
