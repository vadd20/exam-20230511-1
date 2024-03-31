package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Запись результата в файл.
 *
 * @author Vadim Podogov
 * @since 2024.03.31
 */
public class DataWriter {

    private static String RESULT_FILENAME = "result.txt";
    public void writeResult(Map<String, Map<String, Long>> maxMistakesByDate) throws IOException {
        try (var bw = new BufferedWriter(new FileWriter(RESULT_FILENAME))) {

            maxMistakesByDate.forEach((date, nameToMistake) -> {
                var sb = new StringBuilder();
                sb.append(date).append(" - ");
                nameToMistake.forEach((name, mistake) -> {
                    sb.append(name).append(", ");
                });
                sb.delete(sb.length() - 2, sb.length());
                try {
                    bw.write(String.valueOf(sb));
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
 }
