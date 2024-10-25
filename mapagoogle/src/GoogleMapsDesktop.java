import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class ElevationAPI {

    public static double getElevation(String latitude, String longitude, String apiKey) throws IOException {
        String urlString = String.format("https://maps.googleapis.com/maps/api/elevation/json?locations=%s,%s&key=%s", latitude, longitude, apiKey);
        String jsonString = new Scanner(new URL(urlString).openStream(), "UTF-8").useDelimiter("\\A").next();
        JSONObject json = new JSONObject(jsonString);
        return json.getJSONArray("results").getJSONObject(0).getDouble("elevation");
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj szerokość geograficzną (np. 52.2297): ");
            String latitude = scanner.nextLine();

            System.out.print("Podaj długość geograficzną (np. 21.0122): ");
            String longitude = scanner.nextLine();

            String apiKey = "TWÓJ_KLUCZ_API"; // Tutaj wstaw swój klucz API

            double elevation = getElevation(latitude, longitude, apiKey);

            System.out.println(elevation != -1 ? "Wysokość w tym miejscu wynosi " + elevation + " metrów." : "Nie można pobrać wysokości dla podanych współrzędnych.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
