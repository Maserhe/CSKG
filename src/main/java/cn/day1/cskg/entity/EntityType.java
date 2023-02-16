package cn.day1.cskg.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EntityType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体类型编码  1
     */
    private Integer entTypeId;

    /**
     * 实体类型英文名称 文本/PPT/图像/视频/语音
     */
    private String entTypeEname;

    /**
     * 实体类型英文名称 text/ppt/image/video/audio
     */
    private String entTypeCname;

    /**
     * 状态 N：正常    F：停用
     */
    private String status;

    /**
     * 创建时间  time
     */
    private LocalDateTime createTime;

    /**
     * 创建者  username
     */
    private String createBy;

    /**
     * 修改时间 time
     */
    private LocalDateTime updateTime;

    /**
     * 修改者 username
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;


}
