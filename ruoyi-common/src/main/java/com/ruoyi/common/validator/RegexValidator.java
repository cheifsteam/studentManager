package com.ruoyi.common.validator;

import com.ruoyi.common.constant.Regexp;
import com.ruoyi.common.utils.RegexpUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为合法的手机号码
 *
 *
 */
public class RegexValidator  {

    public static boolean isValid(String s,String regex) {
            if (StringUtils.isBlank(s)) {
                return false;
            } else {

                return RegexpUtils.match(regex, s);
            }
    }
}