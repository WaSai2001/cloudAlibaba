package com.wasai.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 骑猪去青岛
 * @data 2024-06-30
 * @time 15:25
 * @desc
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
//    结果状态码
    private String code;
//    描述
    private String message;

    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);

        return resultData;
    }
    public static <T> ResultData<T> fail(String code, String message) {
        ResultData resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);

        return resultData;
    }
}
