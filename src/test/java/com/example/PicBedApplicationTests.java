package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class PicBedApplicationTests {

    private BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testPasswordEncoder(){
        String encode = bcryptPasswordEncoder.encode("********");
        System.out.println(encode);
    }
}
