package test0003;

/**
 * @Author: ZhouAnYan
 * @Date: 2022/2/17 15:37
 */
public @interface Ann03 {
    public String name();
    String name2() default "222";
}
