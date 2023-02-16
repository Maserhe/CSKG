package cn.day1.cskg.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体关系信息
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EntityRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体关系ID  id
     */
    @TableId(value = "ent_relation_id", type = IdType.AUTO)
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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改者
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;


}
