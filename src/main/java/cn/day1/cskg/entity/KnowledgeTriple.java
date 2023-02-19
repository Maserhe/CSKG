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
 * 知识点实体关系信息（实体三元组信息）
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KnowledgeTriple implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体三元组ID  id
     */
    @TableId(value = "triple_id", type = IdType.AUTO)
    private Integer tripleId;

    /**
     * 头实体ID  KnowledgeEntity
     */
    private Integer tripleEntHeadid;

    /**
     * 尾实体ID  KnowledgeEntity
     */
    private Integer tripleEntTailid;

    /**
     * 实体关系ID  包含关系/前驱关系/同一关系/兄弟关系/关联关系/相关关系  b
     */
    private Integer entRelationId;

    /**
     * 映射关系  ? 保留
     */
    private String entPath;

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
