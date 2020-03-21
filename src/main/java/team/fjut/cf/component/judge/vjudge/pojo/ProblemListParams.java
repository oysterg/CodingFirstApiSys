package team.fjut.cf.component.judge.vjudge.pojo;

import lombok.Data;

/**
 * VJ请求题目相关参数类
 * start 记录开始位置
 * length 一次性记录的大小
 * OJId 查找OJ的名称
 * category 仓库名，默认为"all"，代表全部题目集
 * probNum 查找的题目ID
 * title 查找标题
 * source 查找的题目源
 *
 * @author axiang [2020/2/10]
 */
@Data
public class ProblemListParams {
     int start;
     int length;
     String OJId;
     String probNum;
     String title;
     String source;
     String category;

}
