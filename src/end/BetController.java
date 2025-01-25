package end;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BetController implements HttpHandler {
	private BetService betService;
	public BetController() {
		betService = new BetService();
	}
	
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String[] parts = path.split("/");

        if (parts.length == 3) {
        	String response = "";
        	String keyWord = parts[2];
            int id = Integer.parseInt(parts[1]);
            switch (keyWord) {
				case "highstakes":
					response = betService.getHighStakes(id);
					break;
				case "stake":
					InputStream inputStream = exchange.getRequestBody();
					String stakeStr = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
	                        .readLine();
					
					response = betService.offerBet(id, uri.getQuery(),stakeStr);
					break;
				case "session":
					response = betService.getSessionKey(id);
					break;
			}

            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        } else {
            String response = "Invalid path.";
            exchange.sendResponseHeaders(404, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

}
