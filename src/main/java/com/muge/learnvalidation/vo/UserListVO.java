package com.muge.learnvalidation.vo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author huyanlong
 * @Date 2022/1/8 19:37
 */
@Data
public class UserListVO {
    @NotNull(message = "userVOS不能为空")
    private List<@Valid UserVO> userVOS;

    @Size(min = 1, max = 10, message = "大小异常")
    private List<String> ids;
}
