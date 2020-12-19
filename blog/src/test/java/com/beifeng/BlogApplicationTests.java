package com.beifeng;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public static void main(String[] args) {
        String email = "www.1611606790@qq.COM";
        if (email.trim().toLowerCase().contains("@qq.com")){
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(email);
            System.out.println(m.replaceAll("").trim());
        }

    }

}
