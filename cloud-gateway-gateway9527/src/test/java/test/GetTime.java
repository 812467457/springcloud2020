package test;

import org.junit.Test;

import java.time.ZonedDateTime;

public class GetTime {
    @Test
    public void test(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
