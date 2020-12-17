package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnkiCard {
    private String word;
    private String transcription;
    private String translation;
    private Examples examples;
    private String soundURL;

    @Override
    public String toString() {
        return word + ";" + transcription + ";" + translation + ";"
                + examples.getEngExample() + ";" + examples.getRusExample() + ";" + soundURL + "\n";
    }
}
