package cn.day1.cskg.common.dto.entityrelationship;

import lombok.Data;
import java.io.Serializable;

/**
 * Description:
 *  添加 实体关系使用
 * @author maserhe
 * @date 2023/2/16 13:21
 **/
@Data
public class EntityRelationshipDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 实体关系英文名称  包含关系/前驱关系/同一关系/兄弟关系/关联关系/相关关系
     */
    private String entRelationEname;

    /**
     * 实体关系中文名称
     */
    private String entRelationCname;


    /**
     * 状态N：正常     F：停用
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


}
