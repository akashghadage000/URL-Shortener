package URL;

import java.util.HashMap;
import java.util.Random;

public class short_URL{

    private static final int SHORT_URL_LENGTH = 6;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String BASE_URL = "https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/";

    private HashMap<String, String> shortToLongUrls;
    private HashMap<String, String> longToShortUrls;

    public short_URL() {
        shortToLongUrls = new HashMap<>();
        longToShortUrls = new HashMap<>();
    }

    public String shortenUrl(String longUrl) {
        if (longToShortUrls.containsKey(longUrl)) {
            return BASE_URL + longToShortUrls.get(longUrl);
        } else {
            String shortUrl;
            do {
                shortUrl = generateShortUrl();
            } while (shortToLongUrls.containsKey(shortUrl));
            shortToLongUrls.put(shortUrl, longUrl);
            longToShortUrls.put(longUrl, shortUrl);
            return BASE_URL + shortUrl;
        }
    }

    public String expandUrl(String shortUrl) {
        return shortToLongUrls.get(shortUrl.replace(BASE_URL, ""));
    }

    private String generateShortUrl() {
        Random random = new Random();
        StringBuilder shortUrlBuilder = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrlBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortUrlBuilder.toString();
    }

    public static void main(String[] args) {
        short_URL urlShortener = new short_URL();
        String longUrl = "https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/";
        String shortUrl = urlShortener.shortenUrl(longUrl);
        System.out.println("Short URL for " + longUrl + ": " + shortUrl);
        System.out.println("Expanded URL for " + shortUrl + ": " + urlShortener.expandUrl(shortUrl));
    }
}

