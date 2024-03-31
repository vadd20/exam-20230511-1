package io;

import domain.Tea;
import domain.TeaBrewing;
import domain.TeaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Загрузка данных из файлов.
 *
 * @author Vadim Podogov
 * @since 2024.03.31
 */
public class DataLoader {

    private static String TEA_TYPE_FILENAME = "data\\tea_type.csv";
    private static String TEA_FILENAME = "data\\tea.csv";
    private static String TEA_BREWING_FILENAME = "data\\tea_brewing.csv";

    private Map<Long, TeaType> readTeaType() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(TEA_TYPE_FILENAME))) {
            return lines.map(s -> s.split(";"))
                    .map(data -> new TeaType(Long.parseLong(data[0]),
                            data[1],
                            Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]),
                            Integer.parseInt(data[4]),
                            Integer.parseInt(data[5]))).collect(Collectors.toMap(TeaType::getId, t -> t));
        }
    }

    private Map<Long, Tea> readTea() throws IOException {
        var teaTypes = readTeaType();
        try (Stream<String> lines = Files.lines(Path.of(TEA_FILENAME))) {
            return lines.map(s -> s.split(";"))
                    .map(data -> new Tea(
                            Long.parseLong(data[0]),
                            data[1],
                            teaTypes.getOrDefault(Long.parseLong(data[2]), null))
                    ).collect(Collectors.toMap(Tea::getId, t -> t));
        }
    }

    /**
     * Чтение данных из файла.
     *
     * @return соответствие дня заваркам в этот день
     * @throws IOException исключение
     */
    public List<TeaBrewing> readBrewings() throws IOException {
        var teas = readTea();
        try (Stream<String> lines = Files.lines(Path.of(TEA_BREWING_FILENAME))) {
            return lines.map(s -> s.split(";"))
                    .map(data ->
                            new TeaBrewing(
                                    data[0],
                                    teas.getOrDefault(Long.parseLong(data[1]), null),
                                    data[2],
                                    data[3],
                                    Integer.parseInt(data[4]))).toList();
        }
    }
}
