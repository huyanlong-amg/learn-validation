package com.muge.learnvalidation.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author huyanlong
 * @Date 2022/1/8 19:37
 */
@Data
public class UserListVO {
    @NotNull(message = "userVOS不能为空")
    private List<@Valid UserVO> userVOS;
}
