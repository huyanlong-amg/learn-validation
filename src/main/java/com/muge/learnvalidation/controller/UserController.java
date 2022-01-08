package com.muge.learnvalidation.controller;

import com.muge.learnvalidation.utils.ResultUtil;
import com.muge.learnvalidation.utils.ValidatorUtil;
import com.muge.learnvalidation.validator.group.Insert;
import com.muge.learnvalidation.validator.group.Update;
import com.muge.learnvalidation.vo.ResponseVO;
import com.muge.learnvalidation.vo.UserListVO;
import com.muge.learnvalidation.vo.UserVO;
import com.muge.learnvalidation.vo.ValidationListVO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author huyanlong
 * @Date 2022/1/7 23:51
 */
@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    private ConcurrentHashMap<Integer, UserVO> cache = new ConcurrentHashMap<Integer, UserVO>();
    private AtomicInteger count = new AtomicInteger();

    @GetMapping("/query")
    public ResponseVO queryUser(@NotNull(message = "用户id不能为空") @Positive(message = "id不合规") Integer id) {
//        if (results.hasErrors()) {
//            return ResultUtil.resultError(results.getFieldError().getDefaultMessage());
//        }
        return ResultUtil.resultSuccess(cache.get(id));
    }

    @PostMapping("/add")
    // todo Default.class 为默认分组，当java bean的校验注解上没有组标识的时候，但是还需要验证的话 就需要如此
    public ResponseVO addUser(@Validated({Insert.class, Default.class}) @RequestBody UserVO userVO/*, BindingResult results*/) {
       /* if (results.hasFieldErrors()) {
            return ResultUtil.resultError(results.getFieldError().getDefaultMessage());
        }*/
        int id = count.incrementAndGet();
        userVO.setId(id);
        cache.put(id, userVO);
        return ResultUtil.resultSuccess(true);
    }

    @PutMapping("/modify")
    public ResponseVO modifyUser(@Validated({Update.class}) @RequestBody UserVO userVO, BindingResult results) {
        if (results.hasFieldErrors()) {
            return ResultUtil.resultError(HttpStatus.BAD_REQUEST.value(), results.getFieldError().getDefaultMessage());
        }
        cache.put(userVO.getId(), userVO);
        return ResultUtil.resultSuccess(true);
    }

    @DeleteMapping("/delete")
    public ResponseVO deleteUser(@NotNull(message = "用户id不能为空") Integer id) {
        cache.remove(id);
        return ResultUtil.resultSuccess(true);
    }

    @PostMapping("/other")
    public ResponseVO other(@RequestBody UserVO userVO) {
        Optional<String> optional = ValidatorUtil.inValid(userVO, Insert.class, Default.class);
        if (optional.isPresent()) {
            return ResultUtil.resultError(HttpStatus.BAD_REQUEST.value(), optional.get());
        }
        return ResultUtil.resultSuccess(true);
    }

    @PostMapping("/add/list")
    public ResponseVO addList(@RequestBody @Validated({Insert.class, Default.class}) ValidationListVO<UserVO> userVOs) {
        userVOs.stream()
                .map(userVO -> {
                    userVO.setId(count.incrementAndGet());
                    return userVO;
                })
                .forEach(userVO -> cache.put(userVO.getId(), userVO));
        return ResultUtil.resultSuccess(true);
    }

    @PostMapping("add/user")
    public ResponseVO addList(@Validated({Insert.class, Default.class}) @RequestBody UserListVO userListVO) {
        userListVO.getUserVOS().stream()
                .map(userVO -> {
                    userVO.setId(count.incrementAndGet());
                    return userVO;
                })
                .forEach(userVO -> cache.put(userVO.getId(), userVO));
        return ResultUtil.resultSuccess(true);
    }
}
