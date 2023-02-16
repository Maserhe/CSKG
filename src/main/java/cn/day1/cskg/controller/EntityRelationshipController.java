package cn.day1.cskg.controller;


import cn.day1.cskg.common.dto.entityrelationship.ChangeRelationShipDTO;
import cn.day1.cskg.common.dto.entityrelationship.EntityRelationshipDTO;
import cn.day1.cskg.common.lang.Result;
import cn.day1.cskg.entity.EntityRelationship;
import cn.day1.cskg.service.EntityRelationshipService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 实体关系信息 前端控制器
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/entity-relationship")
public class EntityRelationshipController {


    @Autowired
    private EntityRelationshipService entityRelationshipService;

    @GetMapping("/getAll")
    public Result addEntityRelationShip() {
        return Result.succ(entityRelationshipService.list());
    }


    /**
     * 添加实体关系， 添加前，确保实体关系 是否已经存在
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result addEntityRelationShip(@RequestBody EntityRelationshipDTO dto) {

        EntityRelationship relationship = new EntityRelationship();
        BeanUtil.copyProperties(dto, relationship);

        final String status = dto.getStatus();
        if (!StringUtils.isEmpty(status) && !"N".equals(status) && !"F".equals(status)) {
            return Result.fail("参数错误");
        }

        // 创建时间
        LocalDateTime time = LocalDateTime.now();
        relationship.setCreateTime(time);
        relationship.setUpdateTime(time);
        relationship.setUpdateBy(dto.getCreateBy());

        boolean save = entityRelationshipService.save(relationship);
        return save? Result.succ("添加成功"): Result.fail("添加失败");
    }


    /**
     * 根据 实体关系 id 删除实体关系
     * 删除前请确定 没有节点 使用该实体关系
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result removeRelationShip(Integer id) {
        boolean res = entityRelationshipService.removeById(id);
        return res? Result.succ("删除成功"): Result.fail("删除失败");
    }


    /**
     * 修改实体关系接口
     * @param dto
     * @return
     */
    @PostMapping("/change")
    public Result changeRelationShip(@RequestBody ChangeRelationShipDTO dto) {


        // 检查是否 存在该 实体关系

        Integer id = dto.getEntRelationId();
        EntityRelationship sourceEntity = entityRelationshipService.getById(id);
        if (sourceEntity == null) {
            return Result.fail("id错误，实体关系不存在");
        }
        final String status = dto.getStatus();
        if (!StringUtils.isEmpty(status) && !"N".equals(status) && !"F".equals(status)) {
            return Result.fail("参数错误");
        }
        BeanUtil.copyProperties(dto,sourceEntity);
        sourceEntity.setUpdateTime(LocalDateTime.now());

        boolean saveOrUpdate = entityRelationshipService.saveOrUpdate(sourceEntity);
        return saveOrUpdate? Result.succ("修改成功"): Result.fail("修改失败");
    }

}
