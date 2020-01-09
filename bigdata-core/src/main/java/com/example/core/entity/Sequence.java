package com.example.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * mysql序列表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Sequence implements Serializable {

    /**
     * 队列名称
     */
    private String name;
    /**
     * 当前队列值
     */
    private Long currentValue;
    /**
     * 队列递增步长
     */
    private Integer increment;
    /**
     * 队列最大值
     */
    private Long maxValue;
    /**
     * 队列初始值
     */
    private Long initialValue;

//--创建序列表<br>create table sequence(
//            name varchar(50) not null primary key,
//    current_value BIGINT not null DEFAULT 0,
//    increment int not null DEFAULT 1,
//    max_value BIGINT,  -- 最大值
//    initial_value BIGINT, -- 初始值，当当前值大于最大值，回到初始值
//    key (name)
//);
//    DROP TABLE IF EXISTS sequence;
//    CREATE TABLE sequence (
//            name VARCHAR(50) NOT NULL,
//    current_value INT NOT NULL,
//    increment INT NOT NULL DEFAULT 1,
//    PRIMARY KEY (name)
//) ENGINE=InnoDB;
}
