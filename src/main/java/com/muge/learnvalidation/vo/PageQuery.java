package com.muge.learnvalidation.vo;

import lombok.Data;

/**
 * @Author huyanlong
 * @Date 2022/1/14 17:46
 */
@Data
public class PageQuery {
    private int pageNum = 1;
    private int pageSize = 10;
}
