package cn.day1.cskg.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * KnowledgeEntity 的搜索 结果的
 *
 * @author maserhe
 * @date 2023/2/19 14:14
 **/
@Data
public class SearchResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    /**
     * entityType
     */
    private String category;

    /**
     * 存储路径
     */
    private String path;

    /**
     * 与 父 节点的关系
     */
    private String relation;


    /**
     * 孩子 节点
     */
    List<SearchResultVO> children;

}
