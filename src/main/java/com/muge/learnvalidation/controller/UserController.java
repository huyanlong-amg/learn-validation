package com.muge.learnvalidation.controller;

import com.alibaba.fastjson.JSON;
import com.muge.learnvalidation.utils.ResultUtil;
import com.muge.learnvalidation.utils.ValidatorUtil;
import com.muge.learnvalidation.validator.group.Insert;
import com.muge.learnvalidation.validator.group.Select;
import com.muge.learnvalidation.validator.group.Update;
import com.muge.learnvalidation.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.List;
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
@Slf4j
public class UserController {
    private ConcurrentHashMap<Integer, UserVO> cache = new ConcurrentHashMap<Integer, UserVO>();
    private AtomicInteger count = new AtomicInteger();

    @GetMapping("/query")
    public ResponseVO queryUser(@Validated({Select.class}) UserVO userVO, PageQuery pageQuery) {
        log.info("queryUser start ...");
//        if (results.hasErrors()) {
//            return ResultUtil.resultError(results.getFieldError().getDefaultMessage());
//        }
        return ResultUtil.resultSuccess(cache.get(userVO.getId()));
    }

    @PostMapping("/add")
    // todo Default.class 为默认分组，当java bean的校验注解上没有组标识的时候，但是还需要验证的话 就需要如此
    public ResponseVO addUser(@Validated({Insert.class, Default.class}) @RequestBody UserVO userVO/*, BindingResult results*/) {
        log.info("addUser start ... {}", JSON.toJSONString(userVO));
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
    public ResponseVO deleteUser(@NotNull(message = "") Integer id) {
        cache.remove(id);
        return ResultUtil.resultSuccess(true);
    }

    @PostMapping("/other")
    public ResponseVO other(@RequestBody @Validated({Insert.class, Default.class}) UserVO userVO) {
//        Optional<String> optional = ValidatorUtil.inValid(userVO, Insert.class, Default.class);
//        if (optional.isPresent()) {
//            return ResultUtil.resultError(HttpStatus.BAD_REQUEST.value(), optional.get());
//        }
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

    @PostMapping("/add/list1")
    public ResponseVO addList(@RequestBody @Validated({Insert.class, Default.class})
                                  @Size(min = 1, max = 5, message = "列表不能为空") List<String> userList) {
        log.info("userList, {}", userList);
        return ResultUtil.resultSuccess(true);
    }
}
