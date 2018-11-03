package com.sprout.controller;


import com.sprout.common.ApplicationContextHelper;
import com.sprout.common.JsonData;
import com.sprout.dao.SysAclModuleMapper;
import com.sprout.exception.ParamException;
import com.sprout.exception.PermissionException;
import com.sprout.model.SysAclModule;
import com.sprout.param.TestVo;
import com.sprout.util.BeanValidator;
import com.sprout.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

//    @RequestMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        log.info("hello");
//        System.out.println("test successfully");
//        return "hello, permission";
//    }

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        return JsonData.success("hello, permission");
//        throw new PermissionException("test exception");
//        throw new RuntimeException("test excetion");
    }


//    @RequestMapping("/validate.json")
//    @ResponseBody
//    public JsonData validaete(TestVo vo) {
//        log.info("validate");
//        try {
//            Map<String, String> map = BeanValidator.validateObject(vo);
//            if (MapUtils.isNotEmpty(map)) {
//                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    log.info("{}->{}", entry.getKey(), entry.getValue());
//                }
//            }
//        } catch (Exception e) {
//
//        }
//        return JsonData.success("hello, validate");
//    }


//    @RequestMapping("/validate.json")
//    @ResponseBody
//    public JsonData validaete(TestVo vo) throws ParamException {
//        log.info("validate");
//        BeanValidator.check(vo);
//        return JsonData.success("hello, validate check");
//    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validaete(TestVo vo) throws ParamException {
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        BeanValidator.check(vo);
        return JsonData.success("hello, validate check");
    }



}
