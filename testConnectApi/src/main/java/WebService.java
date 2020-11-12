import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class WebService {
    public static void main(String[]args) throws UnsupportedEncodingException {
        // Host url
        String host = "https://data.opendatasoft.com/api/records/1.0/search/";
        String charset = "UTF-8";
        // Headers for a request
        String x_rapidapi_host = "movie-database-imdb-alternative.p.rapidapi.com";
//        String x_rapidapi_key = <YOUR_RAPIDAPI_KEY>;//Type here your key
        // Params
        String s = "dataset=open-beer-database%40public-us&q=duvel&facet=style_name&facet=cat_name&facet=name_breweries&facet=country";
        // Format query for preventing encoding problems
        String query = String.format("s=%s", URLEncoder.encode(s, charset));

        try {
            HttpResponse <JsonNode> response = Unirest.get(host + '?' + s)
//                    .header("x-rapidapi-host", x_rapidapi_host)
//                    .header("x-rapidapi-key", x_rapidapi_key)
                    .asJson();
            System.out.println("response=="+response.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
