package cn.day1.cskg.controller;

import cn.day1.cskg.common.dto.entityrelationship.ChangeRelationShipDTO;
import cn.day1.cskg.common.dto.entitytype.ChangeEntityTypeDTO;
import cn.day1.cskg.common.dto.entitytype.EntityTypeDTO;
import cn.day1.cskg.common.lang.Result;
import cn.day1.cskg.entity.EntityRelationship;
import cn.day1.cskg.entity.EntityType;
import cn.day1.cskg.service.EntityTypeService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-16
 */
@RestController
@RequestMapping("/entity-type")
public class EntityTypeController {

    @Autowired
    EntityTypeService entityTypeService;

    /**
     * 获取所有实体类型
     * @return
     */
    @GetMapping("/getAll")
    public Result getAllEntityType() {
        return Result.succ(entityTypeService.list());
    }


    /**
     * 添加实体关系
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result addEntityType(@RequestBody EntityTypeDTO dto) {

        EntityType entityType = new EntityType();
        BeanUtil.copyProperties(dto, entityType);

        final String status = dto.getStatus();
        if (!StringUtils.isEmpty(status) && !"N".equals(status) && !"F".equals(status)) {
            return Result.fail("参数错误");
        }

        // 创建时间
        LocalDateTime time = LocalDateTime.now();
        entityType.setCreateTime(time);
        entityType.setUpdateTime(time);
        entityType.setUpdateBy(dto.getCreateBy());

        boolean save = entityTypeService.save(entityType);
        return save? Result.succ("添加成功"): Result.fail("添加失败");
    }


    /**
     * 修改实体类型
     * @param dto
     * @return
     */
    @PostMapping("/change")
    public Result changeEntityType(@RequestBody ChangeEntityTypeDTO dto) {


        // 检查是否 存在该 实体关系
        Integer id = dto.getEntTypeId();
        EntityType sourceEntity = entityTypeService.getById(id);
        if (sourceEntity == null) {
            return Result.fail("id错误，实体关系不存在");
        }

        final String status = dto.getStatus();
        if (!StringUtils.isEmpty(status) && !"N".equals(status) && !"F".equals(status)) {
            return Result.fail("参数错误");
        }
        BeanUtil.copyProperties(sourceEntity, dto);
        sourceEntity.setUpdateTime(LocalDateTime.now());

        boolean saveOrUpdate = entityTypeService.saveOrUpdate(sourceEntity);
        return saveOrUpdate? Result.succ("修改成功"): Result.fail("修改失败");
    }

    /**
     * 删除实体类型
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result removeEntityType(Integer id) {
        boolean res = entityTypeService.removeById(id);
        return res? Result.succ("删除成功"): Result.fail("删除失败");
    }

}
