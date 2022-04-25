import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Запуск сервера");
        int port = 8089;
        ServerSocket serverSocket = new ServerSocket(port);
        // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
        while (true) {
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outForClient = new PrintWriter(clientSocket.getOutputStream(), true);
            //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//почему out не buffer? как выбирать то, чем пользоваться?

            String str = in.readLine();
            System.out.println(str);//дождались запрос от клиента, напечатали его запрос в консоль сервера
            outForClient.write("Добрый День! Как вас зовут?\n");
            //in.wait(100); вызывает исключение...не могу заставить сервер ждать ввод имени, он почему-то сразу делает строку пустой...
            str = in.readLine();

            if (!str.isEmpty()) {
                System.out.println("Имя " + str);
                outForClient.println("Ваш возраст?\n");
                // out.write("Ваш возраст?\n");
                String age = in.readLine();
                System.out.println("Возраст " + age);
                if (Integer.getInteger(age) < 18) {
                    outForClient.println("Нужно подождать.Работаем только с совершеннолетними)");
                } else {
                    outForClient.println("Добро пожаловать! Мы работаем для Вас!");
                }
            } else {
                outForClient.println("имя не введено.С анонимами не работаем.До свидания");
                System.out.println(("!!! не введено имя !!!"));
                clientSocket.close();
                break;
            }

        }
        serverSocket.close();
    }
}



