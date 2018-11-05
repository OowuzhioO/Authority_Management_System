package com.sprout.service;

import com.google.common.base.Preconditions;
import com.sprout.dao.SysUserMapper;
import com.sprout.exception.ParamException;
import com.sprout.model.SysUser;
import com.sprout.param.UserParam;
import com.sprout.util.BeanValidator;
import com.sprout.util.MD5Util;
import com.sprout.util.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getTelephone(), param.getId())) {
            throw new ParamException("email已被占用");
        }


        String password = PasswordUtil.randomPassword();
//        TODO:sendEmail
        password = "12345678";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder().username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .password(encryptedPassword).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();

        user.setOperator("system");
        user.setOperateIp("127.0.0.1");
        user.setOperateTime(new Date());
//        TODO: sendEmail

        sysUserMapper.insertSelective(user);
    }

    public void update(UserParam param) {
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getTelephone(), param.getId())) {
            throw new ParamException("email已被占用");
        }

        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新用户不存在");

        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone()).mail(param.getMail())
                .password(before.getPassword()).deptId(param.getDeptId()).status(param.getStatus()).remark(param.getRemark()).build();

        sysUserMapper.updateByPrimaryKeySelective(after);
    }



    public boolean checkEmailExist(String mail, Integer userId) {

        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId) {

        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }

    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

}
