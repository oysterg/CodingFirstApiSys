package com.fjut.cf.pojo.vo;

/**
 * 自定义的与前端交互的类
 *
 * @author axiang [2019/10/8]
 */

import com.fjut.cf.pojo.enums.ResultJsonCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ResultJsonVO {
    private Integer code;
    private String msg;
    private List<Object> datas;


    public ResultJsonVO() {
        datas = new ArrayList<>();
    }

    public ResultJsonVO(ResultJsonCode resultJsonCode) {
        datas = new ArrayList<>();
        this.code = resultJsonCode.getCode();
        this.msg = resultJsonCode.getName();
    }

    public ResultJsonVO(ResultJsonCode resultJsonCode, String msg) {
        datas = new ArrayList<>();
        this.code = resultJsonCode.getCode();
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setStatus(ResultJsonCode resultJsonCode) {
        this.code = resultJsonCode.getCode();
    }

    public void setStatus(ResultJsonCode resultJsonCode, String msg) {
        this.code = resultJsonCode.getCode();
        this.msg = StringUtils.isEmpty(msg) ? resultJsonCode.getName() : msg;
    }

    public void cleanDatas() {
        this.datas.clear();
    }

    public void addInfo(Object object) {
        datas.add(object);
    }

    @Override
    public String toString() {
        return "ResultJsonVO {\n" +
                " code=" + code +
                ",\n msg='" + msg + '\'' +
                ",\n datas= \"不允许查看\"" +
                "\n}";
    }
}
