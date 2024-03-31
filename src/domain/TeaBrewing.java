package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Запись о заварке чая.
 *
 * @author Vadim Podogov
 * @since 2024.03.31
 */
public class TeaBrewing {
    private String name;
    private Tea tea;
    private LocalDateTime brewingStart;
    private LocalDateTime brewingEnd;
    private int temperature;
    private Long duration;
    private Boolean isMistake;

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public TeaBrewing(String name, Tea tea, String brewingStart, String brewingEnd, int temperature) {
        this.name = name;
        this.tea = tea;
        this.brewingStart = LocalDateTime.parse(brewingStart, formatter);
        this.brewingEnd = LocalDateTime.parse(brewingEnd, formatter);
        this.temperature = temperature;
        this.duration = this.brewingStart.until(this.brewingEnd, ChronoUnit.SECONDS);
        TeaType teaType = this.tea.getTeaType();
        this.isMistake = duration > teaType.getTimeTo()
                || duration < teaType.getTimeFrom()
                || this.temperature > teaType.getTemperatureTo()
                || this.temperature < teaType.getTemperatureFrom();
    }

    public Boolean isMistake() {
        return isMistake;
    }

    public LocalDateTime getBrewingStart() {
        return brewingStart;
    }

    public String getName() {
        return name;
    }
}
