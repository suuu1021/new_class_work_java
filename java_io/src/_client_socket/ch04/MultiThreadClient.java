package _client_socket.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiThreadClient {

    public static void main(String[] args) {
        System.out.println("=== 클라이언트 측 실행 ===");

        Socket socket = null;

        try {
            socket = new Socket("localhost", 5000);
            System.out.println("--- connected to the server ---");

            // 서버와 통신을 하기 위한 스트림 준비하기

            // 서버측에 문자열 기반으로 데이터를 보내기 위한 스트림을 준비
            PrintWriter writerStream = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader readerStream
                    = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 키보드 값을 입력 받기 위한 스트림 준비
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            // 서버로 부터 데이터를 읽는 스레드 생성
            Thread readThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = readerStream.readLine()) != null) {
                        if ("exit".equalsIgnoreCase(serverMessage)) {
                            System.out.println("서버가 정상적으로 채팅을 종료합니다.");
                            break;
                        }
                        System.out.println("서버에서 온 msg : " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("서버 측에서 채팅이 종료 되었습니다.");
                }
            });

            // 서버에게 데이터를 보내는 스레드 생성
            Thread writeTread = new Thread(() -> {
                try {
                    String clientMessage;
                    while ((clientMessage = keyboardReader.readLine()) != null) {
                        writerStream.println(clientMessage); // 글자 + \n
                        writerStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("메세지 전송 중 오류가 발생했습니다.");
                }
            });

            // 스레드 시작
            readThread.start();
            writeTread.start();

            // readThread, writeTread 종료 전까지 메인 스레드 대기
            readThread.join();
            writeTread.join();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류발생");
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("자원 해제 중 오류 발생");
            }
        }
    }
}
