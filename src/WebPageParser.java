import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.regex.PatternSyntaxException;

class WebPageParser {

    public static void DownloadWebPage(String webpage) throws PatternSyntaxException{
        try {

            Document doc = Jsoup.connect(webpage).get();
            System.out.println("title - " + doc.title());
            String text = doc.body().text();
            System.out.println("Text - " + text);
            String[] words = text.split("[{',.!?%°\";:/|[',]()+\\d\\n\\r\\t\\h\\v\\s}]");

        } catch (PatternSyntaxException ie) {
            System.out.println("IOException raised:" + ie);
        } catch (IOException ie) {
            System.out.println("IOException raised:" + ie);
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.yandex.ru";
        DownloadWebPage(url);
    }
}
