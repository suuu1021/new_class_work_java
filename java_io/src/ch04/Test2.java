package ch04;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Test2 {

    public static void main(String[] args) {

        String file = "students.txt";

        Random random = new Random();

        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(isr);
             FileWriter fw = new FileWriter(file, true);
             BufferedWriter bfw = new BufferedWriter(fw);
             FileReader fr = new FileReader(file);
             BufferedReader bfr = new BufferedReader(fr);
             PrintStream ps = new PrintStream(System.out)) {
            System.out.println("텍스트를 입력하세요");
            String line;
//            while ((line = br.readLine()) != null && !line.isEmpty()) {
//                bfw.write(line + '\n');
//                bfw.flush();
//            }
            // String[] classMate = new String[7];
            ArrayList<String> arrayList = new ArrayList();
            String line2;
            // int count = 0;
            while ((line2 = bfr.readLine()) != null && !line2.isEmpty()) {
                // System.out.println(line2);
                ps.println(line2);
                //classMate[count] = line2;
                arrayList.add(line2);
                ps.flush();
                //count++;
            }

            // ps.println(line2); 와 같은 의미
            // for (String  f : classMate) {
            //    System.out.println("f : " + f);
            // }

            // 랜덤 변수 하나 추출 1 ~ 7 까지

            Random random1 = new Random();
            // int ranNumber = random1.nextInt(7) + 1;
            int ranNumber = random1.nextInt(arrayList.size());
            // System.out.println("당첨 인원 : " + classMate[ranNumber]);
            System.out.println("당첨 인원 : " + arrayList.get(ranNumber));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
