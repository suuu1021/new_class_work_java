package ch03;

/**
 * 논리 연산자 &&, ||, !
 * 비교 연산자(<, <= ..)와 함께 많이 사용이 됩니다.
 * 연산의 결과가 true, false 로 반환 된다.
 */
public class Operation5 {

    // 코드의 진입점
    public static void main(String[] args) {

        int n1 = 100;
        int n2 = 200;

        // 1. 논리 곱 ( && )
        //                     T     &&     T
        boolean result1  = (n1 > 0)  &&  (n2 > 0);
        System.out.println("result1 : " + result1);

        //                     T     &&     F
        boolean result2  = (n1 > 0)  &&  (n2 < 0);
        System.out.println("result2 : " + result2);

        // 2. 논리 합 ( || )

        //                   T     ||    F
        boolean result3 = (n1 > 0) || (n2 < 0);
        System.out.println("result3 : " + result3);

        //                   F     ||   F
        boolean result4 = (n1 < 0) || (n2 < 0);
        System.out.println("result4 : " + result4);

        // 3. 부정 ( ! )
        System.out.println("부정 연산 확인 : " +  !result4);

        // 로깅 -> 값 확인을 해 본다.
        System.out.println("실제 변수 값 확인 : " + result4);

    } // end of main 
}
