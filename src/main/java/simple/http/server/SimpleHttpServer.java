package simple.http.server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SimpleHttpServer {

    private ServerSocket serverSocket;

    public static void main(String[] args) {
        SimpleHttpServer server = new SimpleHttpServer();
        server.createServerSocket(8088);
        try {
            Socket sock = server.acceptClientSocket();
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            String s;
            while((s = br.readLine()) != null && !s.equals("")) {
                System.out.println(s);
            }

            pw.println("HTTP/1.1 200 OK");
            pw.println("Content-Type: text/html;");
            pw.println("");
            pw.println("Hello, World!!!");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createServerSocket(int port) {

        SocketAddress socketAddress = new InetSocketAddress(port);

        try {
            this.serverSocket = new ServerSocket();
            this.serverSocket.bind(socketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket acceptClientSocket() throws IOException {
        return getServerSocket().accept();
    }
}
