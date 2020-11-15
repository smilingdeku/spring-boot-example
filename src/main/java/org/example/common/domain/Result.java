package org.example.common.domain;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.common.enums.Code;

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

    public Result(Code code, Object data) {
        this(code.getCode(), code.getMsg(), data);
    }

    public Result(Integer code, String msg, IPage<Object> page) {
        super.put("code", code);
        super.put("msg", msg);
        super.put("data", page.getRecords());
        super.put("total", page.getTotal());
    }

    public Result(Code code, IPage<Object> page) {
        this(code.getCode(), code.getMsg(), page);
    }

    public static Result success() {
        return new Result(Code.SUCCESS, (Object) null);
    }
    
    public static Result success(String msg) {
        return new Result(Code.SUCCESS.getCode(), msg, (Object) null);
    }
    
    public static Result success(Object data) {
        return new Result(Code.SUCCESS, data);
    }
    
    public static Result page(IPage<Object> page) {
        return new Result(Code.SUCCESS, page);
    }
    
    public static Result failure() {
        return new Result(Code.FAILURE, (Object) null);
    }
    
    public static Result failure(String msg) {
        return new Result(Code.FAILURE.getCode(), msg, (Object) null);
    }
    
}
