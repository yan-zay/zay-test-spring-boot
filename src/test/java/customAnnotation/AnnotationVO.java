package customAnnotation;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: ZhouAnYan
 * @Date: 2022/1/12 10:48
 * @Version 1.0
 */
@Data
public class AnnotationVO {
    private String name="name 01";
    private String age="age 01";
    private String sex="sex 01";
    private String succ="succ 01";

    private Long aa01 = 1L;
    private Long aa02 = 2L;
    private Long aa03 = 3L;

    private Double bb04 = 4.0;
    private Double bb05 = 5.0;
    private Double bb06 = 6.0;

    private String cc07 = "7";
    private String cc08 = "8";
    private String cc09 = "9";

    private BigDecimal dd10 = new BigDecimal(10.125);
}