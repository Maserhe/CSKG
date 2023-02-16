package cn.day1.cskg.common.dto.entitytype;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 * 修改实体类型的DTO
 *
 * @author maserhe
 * @date 2023/2/16 15:09
 **/
@Data
public class ChangeEntityTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体类型编码  1
     */
    @TableId(value = "ent_type_id", type = IdType.AUTO)
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
     * 修改者 username
     */
    private String updateBy;

    /**
     * 备注
     */
    private String remark;


}

