package ch04;

import java.io.*;

public class Test {

    public static void main(String[] args) {

        // 문자 기반 스트림을 사용해서 키보드에서 입력한 값을 파일에 저장하시오
        // APPEND 모드 활성화, 보조 스트림 버퍼 사용
        String file = "newFile.txt";

        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(isr);
             FileWriter fw = new FileWriter(file, true);
             BufferedWriter bfw = new BufferedWriter(fw);
             FileReader fr = new FileReader(file);
             BufferedReader bfr = new BufferedReader(fr);
             PrintStream ps = new PrintStream(System.out);
             PrintWriter pw = new PrintWriter(System.out)) {
            System.out.println("텍스트를 입력하세요");
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                bfw.write(line + '\n');
                bfw.flush();
                pw.write(line);
                pw.flush();
            }
            String line2;
            while ((line2 = bfr.readLine()) != null && !line2.isEmpty()) {
                // System.out.println(line2);
                ps.println(line2);
                ps.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
