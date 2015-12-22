/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tiger.common.dal.dataobject.AccountDO;
import tiger.common.dal.dataobject.PermissionDO;
import tiger.common.dal.dataobject.RoleDO;
import tiger.common.dal.dataobject.example.AccountExample;
import tiger.common.dal.persistence.AccountLoginLogMapper;
import tiger.common.dal.persistence.AccountMapper;
import tiger.common.dal.persistence.PermissionMapper;
import tiger.common.dal.persistence.RoleMapper;
import tiger.common.dal.query.AccountQuery;
import tiger.common.util.Paginator;
import tiger.common.util.StringUtil;
import tiger.core.base.PageResult;
import tiger.core.constants.SystemConstants;
import tiger.core.domain.AccountDomain;
import tiger.core.domain.AccountResetPwdDomain;
import tiger.core.domain.PermissionDomain;
import tiger.core.domain.convert.AccountConvert;
import tiger.core.domain.convert.PermissionConvert;
import tiger.core.domain.convert.RoleConvert;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;
import tiger.core.service.AccountService;
import tiger.core.service.AttachService;
import tiger.core.service.SystemParamService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:09 PM yiliang.gyl Exp $
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountLoginLogMapper accountLoginLogMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    AttachService attachService;

    @Autowired
    SystemParamService systemParamService;

    /**
     * @see AccountService#read(Long)
     */
    @Override
    public AccountDomain read(Long accountId) {
        AccountDO accountDO = accountMapper.selectByPrimaryKey(accountId);
        if (accountDO == null) {
            return null;
        }
        AccountDomain accountDomain = AccountConvert.convert2Domain(accountDO);
        if (StringUtil.isNotBlank(accountDO.getIcon())) {
            accountDomain.setIcon(attachService.read(Long.parseLong(accountDO.getIcon())));
        }
        return getRoleAndPermission(accountDomain);
    }

    /**
     * @see AccountService#readByAccount(String)
     */
    @Override
    public AccountDomain readByAccount(String account) {
        long beginTime = System.currentTimeMillis();
        if (logger.isInfoEnabled()) {
            logger.info("查询用户,account = " + account);
        }
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);

        List<AccountDO> accountDOs = accountMapper.selectByExample(example);

        if (logger.isInfoEnabled()) {
            logger.info("查询用户,account = " + account + " ,查询耗时: " + (System.currentTimeMillis() - beginTime) + "ms");
        }

        if (accountDOs.size() > SystemConstants.SIZE_ONE) {
            logger.error("获取到" + accountDOs.size() + "个相同账户[" + account + "]的用户.");
            throw new AppException(ErrorCodeEnum.DB_EXCEPTION);
        } else if (accountDOs.size() == SystemConstants.SIZE_ONE) {
            AccountDO accountDO = accountDOs.get(SystemConstants.FIRST_INDEX);
            AccountDomain accountDomain = AccountConvert.convert2Domain(accountDO);
            // 设置用户头像
            if (StringUtil.isNotBlank(accountDO.getIcon())) {
                accountDomain.setIcon(attachService.read(Long.parseLong(accountDO.getIcon())));
            }

            if (logger.isInfoEnabled()) {
                logger.info("查询用户,mobile = " + account + " ,服务完成耗时: " + (System.currentTimeMillis() - beginTime) + "ms");
            }

            return getRoleAndPermission(accountDomain);
        }
        return null;
    }


    /**
     * @see AccountService#deleteLoginToken(Long, String)
     */
    @Override
    public Boolean deleteLoginToken(Long accountId, String loginToken) {
        int rc = accountLoginLogMapper.delete(accountId, loginToken);
        return rc > 0;
    }

    /**
     * @see AccountService#updateAccount(AccountDomain)
     */
    @Override
    public AccountDomain updateAccount(AccountDomain accountDomain) {
        AccountDO accountDO = AccountConvert.convert2DO(accountDomain);
        if(accountMapper.updateByPrimaryKeySelective(accountDO) > 0){
            return read(accountDO.getId());
        }else{
            return null;
        }
    }

    /**
     * @see AccountService#updateAccountHeaderIcon(Long, Long)
     */
    @Override
    public int updateAccountHeaderIcon(Long accountId, Long attachId) {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(accountId);
        accountDO.setIcon(attachId.toString());
        return accountMapper.updateByPrimaryKeySelective(accountDO);
    }


    /**
     * @see AccountService#resetPasswordById(Long, String)
     */
    @Override
    public Boolean resetPasswordById(Long accountId, String newPassword) {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(accountId);
        accountDO.setPassword(newPassword);
        int rc = accountMapper.updateByPrimaryKeySelective(accountDO);
        return (rc > 0);
    }

    /**
     * 根据原始密码改密码
     *
     * @param resetPassDomain
     * @return
     */
    @Override
    public boolean resetPasswordByOldPassword(AccountResetPwdDomain resetPassDomain) {
        if (!isCorrectPassword(resetPassDomain.getAccountId(), resetPassDomain.getOldPassword())) {
            logger.error("编号为[" + resetPassDomain.getId() + "]的用户使用错误的密码[" + resetPassDomain.getOldPassword() + "]将密码更新为" + resetPassDomain.getPassword());
            throw new AppException(ErrorCodeEnum.BIZ_FAIL, "密码错误");
        }
        return resetPasswordById(resetPassDomain.getAccountId(), resetPassDomain.getPassword());
    }

    @Override
    public void delete(long id) {
        accountMapper.deleteByPrimaryKey(id);
    }


    /**
     * 增加一个新的Account
     */
    @Override
    public AccountDomain addAccount(AccountDomain domain) {
        AccountDO accountDO = AccountConvert.convert2DO(domain);
        int count = accountMapper.insert(accountDO);

        // 如果失败，这里最好不要返回false，而是抛出异常，让前台知道错误码
        if (count != 1) {
            throw new AppException(ErrorCodeEnum.DB_EXCEPTION);
        }

        return read(accountDO.getId());
    }

    /**
     * @see tiger.core.service.AccountService#query(tiger.common.dal.query.AccountQuery)
     */
    @Override
    public PageResult<List<AccountDomain>> query(AccountQuery query) {
        //构建分页查询器
        Paginator paginator = new Paginator();
        int count = accountMapper.countAccount(query);
        paginator.setItems(count);
        paginator.setItemsPerPage(query.getPageSize());
        paginator.setPage(query.getPageNum());
        //查询客户列表
        List<AccountDO> accountDOs = accountMapper.queryAccount(query, paginator.getOffset(),
                paginator.getLength());
        PageResult<List<AccountDomain>> result = new PageResult<>();
        //转换成客户模型
        List<AccountDomain> customerDomains = AccountConvert.convert2Domain(accountDOs);
        result.setData(customerDomains);
        result.setPaginator(paginator);
        return result;
    }

    //~ private methods

    /**
     * 获取账户角色权限信息
     *
     * @param accountDomain
     * @return
     */
    private AccountDomain getRoleAndPermission(AccountDomain accountDomain) {
        //获取用户角色
        List<RoleDO> roles = roleMapper.findByAccountId(accountDomain.getId());
        accountDomain.setRoles(RoleConvert.convert2Domain(roles));

        //根据角色获取权限
        Set<PermissionDomain> permissionDomainSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)) {
            List<Long> roleIds = new ArrayList<>();
            roleIds.addAll(roles.stream().map(RoleDO::getId).collect(Collectors.toSet()));
            List<PermissionDO> permissionDOs = permissionMapper.selectByRoleIds(roleIds);
            permissionDomainSet.addAll(PermissionConvert.convert2Domain(permissionDOs));
        }
        List<PermissionDomain> permissionDomains = new ArrayList<>(permissionDomainSet);
        accountDomain.setPermissions(permissionDomains);

        return accountDomain;
    }

    /**
     * 统一用户密码检查工具
     *
     * @param accountId
     * @param password
     * @return
     */
    private boolean isCorrectPassword(Long accountId, String password) {
        String originPassword = read(accountId).getPassword();
        return StringUtil.equals(password, originPassword);
    }
}
