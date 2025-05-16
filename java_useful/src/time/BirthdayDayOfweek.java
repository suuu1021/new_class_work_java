package time;

import java.time.LocalDate;

public class BirthdayDayOfweek {

    public static void main(String[] args) {

        // 2000년 5월13일이 무슨 요일일까?
        LocalDate birthday = LocalDate.of(2000,5,13);

        //해당 날짜의 요일을 반환(예 :  monday ....)
        System.out.println("2000sus 5월 13일의 요일은 : " + birthday.getDayOfWeek());

    }


}
