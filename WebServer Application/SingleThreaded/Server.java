import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() throws IOException {
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(20000); // Optional, for timeout
        
        System.out.println("Server is listening on port: " + port);
        
        while (true) {
            try (Socket acceptedConnection = serverSocket.accept(); // Accept a client connection
                 PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                 BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()))) {

                System.out.println("Connected to client: " + acceptedConnection.getRemoteSocketAddress());
                
                // Send a message to the client
                toClient.println("Hello World from the server");

                // Optionally, read messages from the client (if needed)
                // String clientMessage = fromClient.readLine();
                // System.out.println("Client says: " + clientMessage);

            } catch (IOException ex) {
                ex.printStackTrace();
                break; // Optionally break the loop if an error occurs
            }
        }

        // Close the server socket when the server shuts down
        serverSocket.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
