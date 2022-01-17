package com.yun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ fileName:Character
 * @ description:每种性格的人数
 * @ author:李心雨
 * @ createTime:2021/12/8 18:08
 * @ version:1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterA {
    private Long redCount;
    private Long yellowCount;
    private Long blueCount;
    private Long greenCount;
}
