package test0001;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: ZhouAnYan
 * @Date: 2021/12/24 15:33
 */
public class Test0001 {

    @Test
    public void test() {
        System.out.println("@Test  测试类在中间，就是第二个出现");
    }

    @Before
    public void before() {
        System.out.println("@Before  我会先出现");
    }

    @After
    public void after() {
        System.out.println("@After  我是最后一个");
    }

}
