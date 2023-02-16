package cn.day1.cskg.common.dto.entitytype;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 *  添加实体关系
 * @author maserhe
 * @date 2023/2/16 15:10
 **/
@Data
public class EntityTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;


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
     * 创建者  username
     */
    private String createBy;


    /**
     * 备注
     */
    private String remark;


}
