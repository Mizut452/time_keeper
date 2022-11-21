package Mizut452.time_keeper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Calc {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("second"));

    }
    public int add(int a, int b) {
     a = 10;
     b = 20;
        return a * b;
    }


}
