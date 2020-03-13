package team.fjut.cf.service;

import team.fjut.cf.pojo.po.VjProblemInfo;

/**
 * @author axiang [2020/3/13]
 */
public interface VjProblemInfoService {
    /**
     * 插入一条数据
     *
     * @param vjProblemInfo
     * @return
     */
    int insert(VjProblemInfo vjProblemInfo);

    /**
     * 更新一条数据
     *
     * @param vjProblemInfo
     * @return
     */
    int update(VjProblemInfo vjProblemInfo);


    /**
     * 按照OJId和ProbNum查找一条数据
     *
     * @param OJId
     * @param probNum
     * @return
     */
    VjProblemInfo select(String OJId, String probNum);
}
