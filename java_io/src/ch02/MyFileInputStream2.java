package ch02;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일 입력 스트림을 사용해 보자.
 */
public class MyFileInputStream2 {

    public static void main(String[] args) {

        // a.txt 파일에서 바이트 단위로 데이터를 읽어서 콘솔창에 출력해 보자.
        // 주의! : 한글은 3바이트 기반이라 1바이트씩 읽으면 깨짐이 발생할 수 있다.

        try (FileInputStream in = new FileInputStream("a.txt")) {

            // 사전 기반 지식
            // 파일에서 바이트 단위로 데이터를 읽을 때 더이상 읽을 데이터가 없다면
            // 정수값 -1 을 반환한다.
            int readData;
            while ((readData = in.read()) != -1) {
                System.out.print((char) readData);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    } // end of main

}
