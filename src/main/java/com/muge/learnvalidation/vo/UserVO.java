package com.muge.learnvalidation.vo;

import com.muge.learnvalidation.validator.annotion.Phone;
import com.muge.learnvalidation.validator.annotion.Range;
import com.muge.learnvalidation.validator.group.Delete;
import com.muge.learnvalidation.validator.group.Insert;
import com.muge.learnvalidation.validator.group.Select;
import com.muge.learnvalidation.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author huyanlong
 * @Date 2022/1/7 23:52
 */
@Data
public class UserVO {
    @NotBlank(message = "用户id不能为空", groups = {Delete.class, Update.class, Select.class})
    private Integer id;
    @NotBlank(message = "用户名称不能为空", groups = {Insert.class, Update.class})
    // TODO 注解的的写法也可以和    @org.hibernate.validator.constraints.Range 一直 直接在注解上增加校验的注解
    @Range(max = 10, min = 1, message = "用户名称区间[1,10]", groups = {Insert.class, Update.class})
    private String name;
    @NotBlank(message = "用户名称不能为空", groups = {Insert.class, Update.class})
    private String gender;
    @Phone
    private String telephone;
}
