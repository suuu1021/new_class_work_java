package _server_socket.ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultiNServer {

    private static final int PORT = 5000;
    private static Vector<PrintWriter> clientWriters = new Vector<>();
    public static int connectedCount = 0;

    public static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * 클라이언트와 통신을 처리한다.
         * 메세지를 수신하여 모든 클라이언트에게 브로드캐스트 하고 연결종료 시 자원을 정리한다.
         */
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                // 클라이언트 측과 연결된 출력스트림을 자료구조에 저장
                clientWriters.add(out);

                // 클라이언트로 부터 메세지를 수신하고 방송할 예정
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received : " + message);
                    for (PrintWriter writer : clientWriters) {
                        writer.println("방송 : " + message);
                    }
                }
            } catch (Exception e) {
                System.out.println("클라이언트에서 연결 종료");
            } finally {
                try {
                    if (socket != null) socket.close();
                    // static 자료 구조에 저장된 나의 출력 스트림을 제거하기
                    clientWriters.remove(out);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("소켓 자원 해제 중 오류 발생");
                }
            }
        }
    } // end of inner class

    public static void main(String[] args) {
        System.out.println("서버 스타트");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
                connectedCount++;
                System.out.println("클라이언트 연결 됨 " + connectedCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

} // end of outer class
