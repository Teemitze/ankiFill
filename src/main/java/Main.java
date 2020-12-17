import lombok.SneakyThrows;
import model.AnkiCard;
import model.Examples;
import service.LingualeoClient;
import service.ReversoContextService;
import service.WooordHuntService;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        WooordHuntService wooordHuntService = new WooordHuntService();
        LingualeoClient lingualeoClient = new LingualeoClient();


        Set<String> lines = Files.lines(Path.of("words.txt")).map(String::toLowerCase).collect(Collectors.toSet());


        FileWriter fileWriter = new FileWriter("result.txt");

        for (String word : lines) {
            System.out.println(word);
            AnkiCard ankiCard = wooordHuntService.fillAnkiCard(word);

            if (checkAnkiNull(ankiCard)) {
                ankiCard = lingualeoClient.fillAnkiCard(word);
            }

            if (ankiCard.getExamples() == null || ankiCard.getExamples().getRusExample() == null
                    || ankiCard.getExamples().getEngExample() == null) {
                Examples examples = new ReversoContextService().getExamples(word);
                ankiCard.setExamples(examples);
            }


            fileWriter.write(ankiCard.toString());
        }

        fileWriter.close();
    }


    private static boolean checkAnkiNull(AnkiCard ankiCard) {
        return ankiCard.getTranslation() == null || ankiCard.getTranscription() == null || ankiCard.getSoundURL() == null;
    }
}
