package cn.day1.cskg.controller;


import cn.day1.cskg.common.dto.knowledgeentity.ChangeKnowledgeEntityDTO;
import cn.day1.cskg.common.dto.knowledgeentity.KnowledgeEntityDTO;
import cn.day1.cskg.common.lang.Result;
import cn.day1.cskg.entity.EntityType;
import cn.day1.cskg.entity.KnowledgeEntity;
import cn.day1.cskg.service.EntityTypeService;
import cn.day1.cskg.service.KnowledgeEntityService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 知识点实体信息 前端控制器
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-16
 */
@RestController
@RequestMapping("/knowledge-entity")
public class KnowledgeEntityController {


    @Autowired
    private KnowledgeEntityService knowledgeEntityService;

    @Autowired
    private EntityTypeService entityTypeService;


    /**
     * 获取所有知识点
     * @return
     */
    @GetMapping("/getAll")
    public Result getAllKnowEntity() {
        return Result.succ(knowledgeEntityService.list());
    }

    /**
     * 添加 知识点实体
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result addKnowledgeEntity(@RequestBody KnowledgeEntityDTO dto) {

        // 1， 检查 参数 知识点类型是否正确
        final Integer typeId = dto.getEntTypeId();
        final String status = dto.getStatus();
        if (typeId == null || (!StringUtils.isEmpty(status) && !"N".equals(status) && !"F".equals(status))) {
            return Result.fail("参数错误");
        }

        // 2， 检查 知识点类型
        final EntityType entityType = entityTypeService.getById(typeId);
        if (entityType == null) {
            return Result.fail("实体类型不存在");
        }

        // 3, 存储知识点
        KnowledgeEntity knowledgeEntity = new KnowledgeEntity();
        BeanUtil.copyProperties(dto, knowledgeEntity);

        // 4, 设置创建时间
        LocalDateTime time = LocalDateTime.now();
        knowledgeEntity.setCreateTime(time);
        knowledgeEntity.setUpdateTime(time);
        knowledgeEntity.setUpdateBy(dto.getCreateBy());

        // 保存结果
        final boolean save = knowledgeEntityService.save(knowledgeEntity);
        return  save? Result.succ("添加成功"): Result.fail("添加失败");
    }


    public Result changeKnowledgeEntity(@RequestBody ChangeKnowledgeEntityDTO dto) {

        return null;
    }


}
