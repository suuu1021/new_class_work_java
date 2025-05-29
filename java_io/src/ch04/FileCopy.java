package ch04;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {

    public static void main(String[] args) {
        // 파일 경로 지정
        String sourceFilePath = "image.zip";
        String destinationFilePath = "image_copy.zip";

        // 소요 시간 측정 시작
        long startTime = System.nanoTime();

        // 바이트 기반 스트림으로 데이터를 다룰 수 있다.
        try (FileInputStream fis = new FileInputStream(sourceFilePath);
             FileOutputStream fos = new FileOutputStream(destinationFilePath)) {

            // 한 바이트 씩 읽어서 한 바이트 씩 쓰게한다.
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data); // 읽은 한 바이트를 파일에다가 출력
            }
            System.out.println("입력 스트림 --> 출력 스트림 --> 파일 복사 완료");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 소요 시간 측정 종료
        long endTime = System.nanoTime();
        // endTime - startTime = 측정시간
        long duration = endTime - startTime;

        // 소요 시간을 나노초와 초 단위로 출력
        double seconds = duration / 1_000_000_000.0;
        System.out.println("나노초 값 : " + duration);
        System.out.println("초 값 : " + seconds);

    }
}
