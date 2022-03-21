package com.ruoyi.common.constant;


/**
 * 正则常量
 *
 *
 */
public class Regexp {

    /**
     * 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
     */
  public final static   String MOBILE_REG = "1[3456789]\\d{9}";
    /**
     * 10位的编号
     */
    public final  static  String ID_REG ="\\d{10}";

    public final static  String StudentID_REG="20[12][0-9]\\d{6}";

}
