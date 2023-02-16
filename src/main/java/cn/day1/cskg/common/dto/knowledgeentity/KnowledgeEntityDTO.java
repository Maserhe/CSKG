package cn.day1.cskg.common.dto.knowledgeentity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author maserhe
 * @date 2023/2/16 15:42
 **/
@Data
public class KnowledgeEntityDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 知识点实体名称 实体名称
     */
    private String knowEntName;

    /**
     * 知识点实体类型ID  EntytyType
     */
    private Integer entTypeId;

    /**
     * 状态  N / F
     */
    private String status;


    /**
     * 创建者
     */
    private String createBy;


    /**
     * 备注
     */
    private String remark;

    /**
     * 存储路径
     */
    private String knowPath;


}
