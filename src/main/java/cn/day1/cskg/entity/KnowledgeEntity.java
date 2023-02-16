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
 * 知识点实体信息
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KnowledgeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 知识点实体ID  id
     */
    @TableId(value = "know_ent_id", type = IdType.AUTO)
    private Integer knowEntId;

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

    /**
     * 存储路径
     */
    private String knowPath;


}
