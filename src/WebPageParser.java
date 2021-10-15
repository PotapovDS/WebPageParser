import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

class WebPageParser {

    public static String DownloadWebPageText(String webpage) throws PatternSyntaxException {
        try {

            Document doc = Jsoup.connect(webpage).get();
            System.out.println("title - " + doc.title());
            String text = doc.body().text();
            return text;

        } catch (PatternSyntaxException ie) {
            System.out.println("IOException raised:" + ie);
        } catch (IOException ie) {
            System.out.println("IOException raised:" + ie);
        }
        return "";
    }

    public static void WordsCount(String text){
        String[] words = text.split("[{',.!?%°©\";:/|«»[',]=()+\\d\\n\\r\\t\\h\\v\\s}]");
        HashMap<String, Integer> wordsCount = new HashMap<>();

        for (String word : words){
            if (word.isEmpty() | word.equals("-")){
                continue;
            }
            if (!wordsCount.containsKey(word)) {
                wordsCount.put(word, 1);
            } else {
                wordsCount.put(word, wordsCount.get(word)+1);
            }
        }

        for (Map.Entry entry: wordsCount.entrySet()) {
            System.out.println(entry);
        }

    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.yandex.ru";
        String text = DownloadWebPageText(url);
        WordsCount(text);
    }
}
