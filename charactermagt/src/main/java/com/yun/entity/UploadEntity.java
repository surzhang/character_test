package com.yun.entity;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ fileName:UploadEntity
 * @ description:
 * @ author:mxt
 * @ createTime:2021/12/6 10:09
 * @ version:1.0.0
 */
@Component
@Data
public class UploadEntity  {
    //响应状态
    private String response;
    //返回图片转换成为的字符串
    private String name;
}
