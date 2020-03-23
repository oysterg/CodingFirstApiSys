package team.fjut.cf.pojo.vo;

/**
 * 自定义的与前端交互的类
 *
 * @author axiang [2019/10/8]
 */

import team.fjut.cf.pojo.enums.ResultCode;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultJson {
    private Integer code;
    private String msg;
    private List<Object> datas;


    public ResultJson() {
        datas = new ArrayList<>();
    }

    public ResultJson(ResultCode resultCode) {
        datas = new ArrayList<>();
        this.code = resultCode.getCode();
        this.msg = resultCode.getName();
    }

    public ResultJson(ResultCode resultCode, String msg) {
        datas = new ArrayList<>();
        this.code = resultCode.getCode();
        this.msg = msg;
    }

    public ResultJson(ResultCode resultCode, String msg, Object... infos) {
        datas = new ArrayList<>();
        this.code = resultCode.getCode();
        this.msg = msg;
        this.datas.addAll(Arrays.asList(infos));
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

    public void setStatus(ResultCode resultCode) {
        this.code = resultCode.getCode();
    }

    public void setStatus(ResultCode resultCode, String msg) {
        this.code = resultCode.getCode();
        this.msg = StringUtils.isEmpty(msg) ? resultCode.getName() : msg;
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
                ",\n datas= \"日志中不允许查看\"" +
                "\n}";
    }
}
