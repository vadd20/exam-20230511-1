import io.DataLoader;
import io.DataWriter;
import service.BrewingProcessor;

import java.io.IOException;

/**
 * Основной класс.
 *
 * @author Vadim Podogov
 * @since 2024.03.31
 */
public class Main {

    public static void main(String[] args) throws IOException {
        DataLoader dataLoader = new DataLoader();
        BrewingProcessor brewingProcessor = new BrewingProcessor();
        DataWriter dataWriter = new DataWriter();
        dataWriter.writeResult(brewingProcessor.getDateToEmployees(dataLoader.readBrewings()));
    }
}
