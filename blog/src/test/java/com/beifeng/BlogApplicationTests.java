package com.beifeng;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        String ids = "00204dee3bf542f7ab2139848fde6224,0f3cd79b9bdc4c349a220d6ce04f07e5,37c7505bb021433eb338a4f11efee5e3";
        if (!"".equals(ids) && ids != null) {
            String[] idArray = ids.split(",");
            for (int i=0; i < idArray.length;i++) {
                list.add(String.valueOf(idArray[i]));
            }
        }

        for (String id:list
             ) {
            System.out.println(id);
        }
    }
}
