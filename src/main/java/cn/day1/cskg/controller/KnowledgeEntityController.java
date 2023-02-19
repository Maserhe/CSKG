package cn.day1.cskg.controller;


import cn.day1.cskg.common.dto.knowledgeentity.AddKnowledgeEntityDTO;
import cn.day1.cskg.common.dto.knowledgeentity.ChangeKnowledgeEntityDTO;
import cn.day1.cskg.common.dto.knowledgeentity.KnowledgeEntityDTO;
import cn.day1.cskg.common.lang.Result;
import cn.day1.cskg.common.vo.SearchResultVO;
import cn.day1.cskg.entity.EntityRelationship;
import cn.day1.cskg.entity.EntityType;
import cn.day1.cskg.entity.KnowledgeEntity;
import cn.day1.cskg.entity.KnowledgeTriple;
import cn.day1.cskg.service.EntityRelationshipService;
import cn.day1.cskg.service.EntityTypeService;
import cn.day1.cskg.service.KnowledgeEntityService;
import cn.day1.cskg.service.KnowledgeTripleService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private EntityRelationshipService entityRelationshipService;


    @Autowired
    private KnowledgeTripleService knowledgeTripleService;


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


    /**
     * 添加实体 节点，并与添加 节点的 关系， 注意父亲 关系 和 兄弟关系
     * @param dto
     * @return
     */
    @PostMapping("/addRelationEntity")
    public Result addKnowledgeEntity(@RequestBody @Validated AddKnowledgeEntityDTO dto) {

        final String status = dto.getStatus();
        if ((!StringUtils.isEmpty(status) && !"N".equals(status) && !"F".equals(status))) {
            return Result.fail("参数错误");
        }

        // 1, 创建 节点
        KnowledgeEntity newNode = new KnowledgeEntity();
        BeanUtil.copyProperties(dto, newNode);

        // 2， 检测关系类型Id， ， 和 当前节点ID， 新节点的类型ID 是否存在
        final EntityRelationship relationship = entityRelationshipService.getById(dto.getRelationshipId());
        final EntityType entityType = entityTypeService.getById(dto.getEntTypeId());
        final KnowledgeEntity father = knowledgeEntityService.getById(dto.getFatherId());
        if (relationship == null || entityType == null || father == null) return Result.fail("参数错误");

        // 3, 插入新节点
        LocalDateTime time = LocalDateTime.now();
        newNode.setCreateTime(time);
        newNode.setUpdateTime(time);
        newNode.setUpdateBy(dto.getCreateBy());

        // 4， 添加节点
        knowledgeEntityService.save(newNode);
        // 5, 添加三元组 关系 创建 三元组关系
        KnowledgeTriple triple = new KnowledgeTriple();
        triple.setTripleEntHeadid(father.getKnowEntId());
        triple.setTripleEntTailid(newNode.getKnowEntId());
        triple.setEntRelationId(relationship.getEntRelationId());
        triple.setCreateTime(time);
        triple.setUpdateTime(time);
        triple.setUpdateBy(dto.getCreateBy());
        triple.setCreateBy(dto.getCreateBy());
        triple.setRemark(dto.getRemark());
        triple.setStatus(dto.getStatus());
        
        boolean save = knowledgeTripleService.save(triple);
        return  save? Result.succ("添加成功"): Result.fail("添加失败");
    }


    /**
     * 根据 文本 匹配 知识点， 查询 结果
     * @param text
     * @return
     */
    @GetMapping("/search")
    public Result searchKnowledge(String text) {
        
        if (StringUtils.isEmpty(text)) {
            return Result.fail("禁止空查询");
        }
        
        // 1。 匹配知识点
        KnowledgeEntity nowNode = knowledgeEntityService.getOne(new QueryWrapper<KnowledgeEntity>().eq("know_ent_name", text));
        if (nowNode == null) return Result.fail("查询不到结果");

        // 2。 查询 所有 子节点 id
        final List<KnowledgeTriple> tripleList = knowledgeTripleService.list(new QueryWrapper<KnowledgeTriple>().eq("triple_ent_headid", nowNode.getKnowEntId()));
        // 3, 匹配 所有 子节点
        List<Integer> childrenIdList = tripleList.stream().map(t -> t.getTripleEntTailid()).collect(Collectors.toList());
        List<KnowledgeEntity> childrenList = knowledgeEntityService.listByIds(childrenIdList).stream().collect(Collectors.toList());

        // 4， 查询所有 关系，查询所有 类型
        List<EntityRelationship> relationshipsList = entityRelationshipService.list();
        List<EntityType> typeList = entityTypeService.list();

        SearchResultVO res = buildTree(nowNode, relationshipsList, typeList, childrenList, tripleList);
        return Result.succ(res);
    }

    /**
     * 根据 父亲 节点 获取 所有的 儿子 节点
     * @param id
     * @return
     */
    @GetMapping("/childrens")
    public Result getChildrenTree(Integer id) {
        if (id == null) return Result.fail("参数错误");
        // 1。 匹配知识点
        KnowledgeEntity nowNode = knowledgeEntityService.getById(id);
        if (nowNode == null) return Result.fail("查询不到该节点");
        // 2。 查询 所有 子节点 id
        final List<KnowledgeTriple> tripleList = knowledgeTripleService.list(new QueryWrapper<KnowledgeTriple>().eq("triple_ent_headid", nowNode.getKnowEntId()));

        // 不存在子节点
        if (tripleList.size() == 0) {
            return Result.fail("没有儿子");
        }

        // 3, 匹配 所有 子节点
        List<Integer> childrenIdList = tripleList.stream().map(t -> t.getTripleEntTailid()).collect(Collectors.toList());

        List<KnowledgeEntity> childrenList = knowledgeEntityService.listByIds(childrenIdList).stream().collect(Collectors.toList());

        // 4， 查询所有 关系，查询所有 类型
        List<EntityRelationship> relationshipsList = entityRelationshipService.list();
        List<EntityType> typeList = entityTypeService.list();

        SearchResultVO res = buildTree(nowNode, relationshipsList, typeList, childrenList, tripleList);
        return Result.succ(res);
    }



    /**
     *  构建 返回的 树状结构 数据
     * @param root
     * @param relationships
     * @param typeList
     * @param childrenList
     * @return
     */
    private SearchResultVO buildTree(KnowledgeEntity root, List<EntityRelationship> relationships, List<EntityType> typeList, List<KnowledgeEntity> childrenList, List<KnowledgeTriple> tripleList) {
        SearchResultVO ans = new SearchResultVO();
        ans.setId(String.valueOf(root.getKnowEntId()));
        ans.setName(root.getKnowEntName());
        ans.setPath(root.getKnowPath());
        ans.setCategory(getCategoryName(typeList, root.getEntTypeId()));

        List<SearchResultVO> list = new ArrayList<>();
        // 设置儿子 属性

        for (int i = 0; i < childrenList.size(); i ++ ) {
            KnowledgeEntity entity = childrenList.get(i);
            SearchResultVO t = new SearchResultVO();
            t.setId(String.valueOf(entity.getKnowEntId()));
            t.setName(entity.getKnowEntName());
            t.setPath(entity.getKnowPath());
            t.setCategory(getCategoryName(typeList, entity.getEntTypeId()));

            // 设置 关系
            for (KnowledgeTriple triple: tripleList) {
                if (entity.getKnowEntId() == triple.getTripleEntTailid()) {
                    t.setRelation(getRelationName(relationships, triple.getEntRelationId()));
                    break;
                }
            }
            list.add(t);
        }

        ans.setChildren(list);
        return ans;
    }

    private String getRelationName(List<EntityRelationship> relationships, Integer id) {
        for (EntityRelationship s: relationships) {
            if (s.getEntRelationId() == id) return s.getEntRelationCname();
        }
        return "";
    }

    private String getCategoryName(List<EntityType> typeList, Integer id) {
        for (EntityType type: typeList) {
            if (type.getEntTypeId() == id) return type.getEntTypeCname();
        }
        return "";
    }

}
