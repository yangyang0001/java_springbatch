package com.deepblue.springbatch.test;

import lombok.Data;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ObjectTest {

    public static void main(String[] args) throws InterruptedException {

        List<A> aList = new ArrayList<>();

        A a1 = new A();
        A a2 = new A();
        A a3 = new A();
        A a4 = new A();
        A a5 = new A();

        a1.setId(1L).setName("a1").setCreateTime(new Date());
        Thread.sleep(2000L);
        a2.setId(2L).setName("a2").setCreateTime(new Date());
        Thread.sleep(2000L);
        a3.setId(3L).setName("a3").setCreateTime(new Date());
        Thread.sleep(2000L);
        a4.setId(4L).setName("a4").setCreateTime(new Date());
        Thread.sleep(2000L);
        a5.setId(5L).setName("a5").setCreateTime(new Date());
        Thread.sleep(2000L);

        aList.add(a1);
        aList.add(a2);
        aList.add(a3);
        aList.add(a4);
        aList.add(a5);

        long count = aList.stream().map(A::getCreateTime).filter(Objects::isNull).count();
        System.out.println("count:" + count);

        String createMin = null;
        String createMax = null;

        if(count == 0) {
            Date min = aList.stream().map(A::getCreateTime).filter(Objects::nonNull).min(Date::compareTo).get();
            Date max = aList.stream().map(A::getCreateTime).filter(Objects::nonNull).max(Date::compareTo).get();

            createMin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(min);
            createMax = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(max);
        }

        System.out.println("createMin:" + createMin);
        System.out.println("createMax:" + createMax);

    }


    @Data
    @Accessors(chain = true)
    public static class A {

        private Long id;

        private String name;

        private Date createTime;
    }

}
