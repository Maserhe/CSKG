package cn.day1.cskg.controller;


import cn.day1.cskg.common.lang.Result;
import cn.day1.cskg.service.KnowledgeTripleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 知识点实体关系信息（实体三元组信息） 前端控制器
 * </p>
 *
 * @author Maserhe
 * @since 2023-02-16
 */
@RestController
@RequestMapping("/knowledge-triple")
public class KnowledgeTripleController {

    @Autowired
    private KnowledgeTripleService knowledgeTripleService;

    /**
     * 获取所有 知识点三元组 关系
     * @return
     */
    @GetMapping("/getAll")
    public Result getAllKnowledgeTriple() {
        return Result.succ(knowledgeTripleService.list());
    }



}
