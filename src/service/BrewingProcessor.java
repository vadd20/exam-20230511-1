package service;

import domain.TeaBrewing;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * todo vpodogov
 *
 * @author Vadim Podogov
 * @since 2024.03.31
 */
public class BrewingProcessor {

    public Map<String, Map<String, Long>> getDateToEmployees(List<TeaBrewing> brewingList) {

        Map<String, Map<String, Long>> dateToNamesToMistakes = brewingList.stream()
                .filter(TeaBrewing::isMistake)
                .collect(Collectors.groupingBy(b -> b.getBrewingStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                Collectors.groupingBy(
                                        TeaBrewing::getName,
                                        Collectors.counting()
                                )
                ));

        return dateToNamesToMistakes.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        stringMapEntry -> {
                            Long max = Collections.max(stringMapEntry.getValue().values());
                            return stringMapEntry.getValue().entrySet().stream()
                                    .filter(e -> e.getValue().equals(max))
                                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                        }
                ));
    }
}
