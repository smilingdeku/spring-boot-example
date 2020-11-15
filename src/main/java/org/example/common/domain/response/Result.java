package org.example.common.domain.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.common.enums.ResultCode;

import java.util.HashMap;

/**
 * @author cark
 * @since 2020-11-15
 **/
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result(Integer code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        super.put("data", data);
    }

    public Result(ResultCode code, Object data) {
        this(code.getCode(), code.getMsg(), data);
    }

    public Result(Integer code, String msg, IPage page) {
        super.put("code", code);
        super.put("msg", msg);
        super.put("data", page.getRecords());
        super.put("total", page.getTotal());
    }

    public Result(ResultCode code, IPage page) {
        this(code.getCode(), code.getMsg(), page);
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS, (Object) null);
    }
    
    public static Result success(String msg) {
        return new Result(ResultCode.SUCCESS.getCode(), msg, (Object) null);
    }
    
    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS, data);
    }
    
    public static Result page(IPage page) {
        return new Result(ResultCode.SUCCESS, page);
    }
    
    public static Result failure() {
        return new Result(ResultCode.FAILURE, (Object) null);
    }
    
    public static Result failure(String msg) {
        return new Result(ResultCode.FAILURE.getCode(), msg, (Object) null);
    }
    
}
