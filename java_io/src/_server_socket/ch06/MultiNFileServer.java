package _server_socket.ch06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiNFileServer {

    private static final int PORT = 5000;
    private static final String UPLOAD_DIR = "Uploads/";
    public static int connectedCount = 0;


    public static class ClientHandler extends Thread {
        private Socket socket;
        private BufferedOutputStream out;
        private BufferedInputStream in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedInputStream(socket.getInputStream());
                out = new BufferedOutputStream(socket.getOutputStream());

                byte[] nameBuffer = new byte[100];
                in.read(nameBuffer);
                String fileName = new String(nameBuffer).trim();
                System.out.println("파일 이름 수신 : " + fileName);

                File file = new File(UPLOAD_DIR + fileName);
                FileOutputStream fos = new FileOutputStream(file);

                byte[] buffer = new byte[4096];
                int bytesRead;
                // EOF 를 -1 로 표현함
                while ((bytesRead = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                System.out.println("서버 컴퓨터에 파일 저장 완료 : " + fileName);

                out.write("파일 업로드 완료: ".getBytes());
                out.write(fileName.getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // socket.close();
            }
        }
    } // end of inner class

    public static void main(String[] args) {
        System.out.println("서버 스타트");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new MultiNFileServer.ClientHandler(serverSocket.accept()).start();
                connectedCount++;
                System.out.println("클라이언트 연결 됨 " + connectedCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}