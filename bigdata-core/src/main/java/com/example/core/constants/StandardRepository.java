package com.example.core.constants;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 标准仓库，用来存储相关标准信息
 * 用于各种数据校验是否满足要求
 * @author daniel
 * @date 2019-01-15
 */
@Slf4j
public class StandardRepository {

    /**
     * 图片、文件（word，pdf等）校验
     */
    public static Map<String, Object> fileRepository = new HashMap<>();
}
