import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8089;
        Scanner scanner = new Scanner(System.in);

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("Уважаемый сервер, говорит клиент. Давайте начнем работу!\n");
            for (int i = 0; i < 2; i++) {
                String s = in.readLine();
                System.out.println(s);
                String str = scanner.nextLine();
                out.println(str);
            }

        } catch (IIOException e) {
            e.printStackTrace();
        }
    }
}
