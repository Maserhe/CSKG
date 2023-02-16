package cn.day1.cskg.common.dto.entityrelationship;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 * 修改实体关系
 *
 * @author maserhe
 * @date 2023/2/16 14:04
 **/
@Data
public class ChangeRelationShipDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体关系ID  id
     */
    private Integer entRelationId;

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
     * 修改者
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;


}

