package end;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Server {
	public static void main(String[] args) throws IOException {
		
		HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);

		server.createContext("/", new BetController());

		server.setExecutor(null); 
		server.start();

		System.out.println("Betting Server is listening on port 8001");
	}
}
