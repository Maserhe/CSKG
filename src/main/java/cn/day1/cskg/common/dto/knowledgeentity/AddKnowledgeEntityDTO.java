package cn.day1.cskg.common.dto.knowledgeentity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description:
 *  添加实体 节点
 * @author maserhe
 * @date 2023/2/19 11:52
 **/
@Data
public class AddKnowledgeEntityDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 知识点实体名称 实体名称
     */
    @NotBlank(message = "知识点实体名称不能为空")
    private String knowEntName;

    /**
     * 知识点实体类型ID  EntytyType
     */
    @NotNull(message = "知识点实体类型ID不能为空")
    private Integer entTypeId;

    /**
     * 状态  N / F
     */
    @NotBlank(message = "状态不能为空")
    private String status;


    /**
     * 创建者
     */
    @NotBlank(message = "创建者不能为空")
    private String createBy;


    /**
     * 备注
     */
    private String remark;

    /**
     * 存储路径
     */
    private String knowPath;


    /**
     * 父节点的 id, 当前节点， 添加 下面关系。
     */
    @NotNull(message = "不能为空")
    private Integer fatherId;


    /**
     * 是父亲 节点的 关系
     */
    @NotNull(message = "不能为空")
    private Integer relationshipId;

}
