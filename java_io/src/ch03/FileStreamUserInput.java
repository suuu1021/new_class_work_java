package ch03;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * 문자 기반 스트림을 사용하자.
 * 1. 키보드에서 값을 받아서 파일에 쓰기
 * 2. 다시 그 파일을 읽는 함수를 만들어서 실행하기
 */
public class FileStreamUserInput {

    public static void main(String[] args) {
        writeUserInputToFile("user_input.txt");
//        readFromFile(".txt");
    } // end of main

    // 키보드에서 입력을 받아 파일에 쓰는 함수를 만들어 보세요
    public static void writeUserInputToFile(String fileName) {
        try (InputStreamReader reader = new InputStreamReader(System.in);
             // 두번째 인자값 true -> append 모드 활성화
             FileWriter writer = new FileWriter(fileName, true)) {
            System.out.println("콘솔에서 테스트를 입력");
            // 한 문자씩 읽고 유니코드 정수값을 반환 - reader.read();
            int charCode;
            while ((charCode = reader.read()) != -1) {
                writer.write(charCode);
                writer.flush(); // 문자 하나 받고 물을 내리자
            }
            System.out.println(fileName + ".txt 파일에 텍스트를 모두 작성함");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 파일에서 텍스트를 읽는 함수 만들어 보기
    public static void readFromFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            int readData;
            while ((readData = reader.read()) != -1) {
                System.out.print((char) readData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
