import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

class WebPageParser {

    public static String ReadUrlFromUser(){
        Scanner in = new Scanner(System.in);
        String url = new String();
        while (url.length() == 0) {
            System.out.print("Введите адресс в формате http://wwww.adress.aaa\n Для выхода из программы введите 'exit' \n :");
            url = in.nextLine();
            System.out.println(url);

            if (url.toLowerCase() == "exit") {
                System.out.println("Выход из программы");
                System.exit(0);
            }

            if (url.matches("http://www\\.+.+\\.+.*")) {
                return url;
            } else if (url.matches("www\\.+\\w+\\.+\\w*")) {
                return "http://" + url;
            } else {
                System.out.println("неверный формат: ");
                url = "";
            }
        }
        return "";
    }

    public static String DownloadWebPageText(String webpage) throws IllegalArgumentException {
        try {
            Document doc = Jsoup.connect(webpage).get();
            String text = doc.body().text();
            return text;

        } catch (IllegalArgumentException | IOException ie) {
            System.out.println("Exception raised: введен некорректный адресс" + ie);
        }
        return "";
    }

    public static void WordsCount(String text){
        String[] words = text.split("[{',.!?%°©*#\";:/|«»[',]=()+\\d\\n\\r\\t\\h\\v\\s}]");
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
        String url = ReadUrlFromUser();
        String text = DownloadWebPageText(url);
        if (text.isEmpty()){
            System.out.println("На указанном адресе текста не найдено");
        } else {
            WordsCount(text);
        }
    }
}
