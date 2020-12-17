package service;

import lombok.SneakyThrows;
import model.Examples;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ReversoContextService {

    @SneakyThrows
    public Examples getExamples(String word) {
        final Document doc = Jsoup.connect("https://context.reverso.net/перевод/английский-русский/" + word).get();

        final Element elementWithExample = getFirstExampleElement(doc);

        return new Examples(parseEngExample(elementWithExample), parseRusExample(elementWithExample));
    }


    private Element getFirstExampleElement(Document doc) {
        return doc.getElementsByClass("example").first();
    }

    private String parseEngExample(Element element) {
        return element.getElementsByClass("src ltr").text();
    }

    private String parseRusExample(Element element) {
        return element.getElementsByClass("trg ltr").text();
    }
}
