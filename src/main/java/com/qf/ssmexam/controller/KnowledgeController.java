package com.qf.ssmexam.controller;

import com.qf.ssmexam.commons.annotions.PermitRole;
import com.qf.ssmexam.commons.enums.RoleType;
import com.qf.ssmexam.service.KnowledgeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 知识点处理类, AOP(面向切面编程)
 */
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Resource
    private KnowledgeService knowledgeService;

    /**
     * RolType是只能老师访问
     * @return
     */
    @PermitRole({RoleType.teacher})
    @RequestMapping
    public Object getKnowledges() {
        return knowledgeService.getAll();
    }
}
