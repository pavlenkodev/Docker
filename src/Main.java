import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            while (true) {
                try (
                        Socket accept = serverSocket.accept()
                ) {
                    OutputStream outputStream = accept.getOutputStream();
                    String message = "Hello world";
                    outputStream.write("HTTP/1.1 200 OK \r\n".getBytes());
                    outputStream.write(("Content-Length: " + message.length() + "\r\n").getBytes());
                    outputStream.write("Connection: close \r\n".getBytes());
                    outputStream.write("\r\n".getBytes());
                    outputStream.write(message.getBytes());
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
