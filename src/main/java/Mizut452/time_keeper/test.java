package Mizut452.time_keeper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static void main(String[] args) {
        String id = "testMan";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(id));
        message("Really");
        keisan k = new keisan();
        k.a = 10;
        k.print();

    }

    public static class keisan {
        private int a;
        private int b = a * 10;

        void keisan(int a, int b) {
            this.a = a;
            this.b = b;
        }
        void print() {
            System.out.println(b);
        }




    }

    static void message(String name) {
        System.out.println("こんにちは" + name + "さん");
    }
}

