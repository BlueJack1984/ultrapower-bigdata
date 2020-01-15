package com.example.core.service.impl;

import com.example.core.constants.ResponseCode;
import com.example.core.dao.IUserDao;
import com.example.core.entity.Corporation;
import com.example.core.entity.User;
import com.example.core.exception.ApplicationException;
import com.example.core.service.ICorporationService;
import com.example.core.service.IUserService;
import com.example.core.utils.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户功能模块
 * @author daniel
 * @date 2019-01-13
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserDao userDao;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final DateUtil dateUtil;
    private final ICorporationService corporationService;

    /****************************************************************************************
                                       查询相关
     ***************************************************************************************/
    /**
     * 根据id获取实体信息
     * @param id 实体id
     * @return 返回实体信息
     */
    @Override
    public User getById(Long id) {
        //User user = userDao.selectByPrimaryKey(id);
        User user = userDao.selectById(id);
        log.info("用户账号：" + user.getAccount());
        log.info("用户姓名：" + user.getRealName());
        return user;
    }

    /**
     * 根据账号account获取实体信息
     * @param account 账号信息
     * @return 返回实体信息
     */
    @Override
    public User getByAccount(String account) {

        if(StringUtils.isEmpty(account)) {
            log.info("【user---根据账户account查询用户，account参数为空】");
            return null;
        }
        User user = userDao.selectByAccount(account);
        return user;
    }

    /**
     * 获取所有的正常用户的列表
     * @return 返回所有列表信息
     */
    @Override
    public List<User> getListAll() {

        List<User> userList = userDao.selectListAll();
        return userList;
    }

    /**
     * 多条件查询用户列表
     * @param conditionMap
     * param searchDateStart 查询用户的创建时间起始点
     * param searchDateEnd 查询用户的创建时间结束点
     * param keywords 输入的用户账号参数
     * param offSet 查询的页码
     * param pageSize 每页显示的数据条数
     * @return
     */
    @Override
    public PageInfo<User> getListByConditionPage(Map<String, Object> conditionMap) throws ApplicationException{

        Integer offset = (Integer) conditionMap.get("offset");
        Integer pageSize = (Integer) conditionMap.get("pageSize");

        String keyword = (String) conditionMap.get("keyword");
        String searchDateStart = (String) conditionMap.get("searchDateStart");
        String searchDateEnd = (String) conditionMap.get("searchDateEnd");
        Date dateStart = stringToDateFormat(searchDateStart, DATE_FORMAT);
        Date dateEnd = stringToDateFormat(searchDateEnd, DATE_FORMAT);
        //RowBounds rowBounds = new RowBounds(offSet, pageSize);
        //List<Information> list = null;
        //list = informationMapper.selectListByCondition(condition, rowBounds);
        //分页操作
        Page<User> page = PageHelper.startPage(offset, pageSize);
        List<User> userList = userDao.selectListByConditionPage(dateStart, dateEnd, keyword);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    /**
     * 校验日期字符串
     * @param dateString 查询用户的创建时间起始点
     * @param dateFormat 查询用户的创建时间结束点
     * @return
     */
    private Date stringToDateFormat(String dateString, String dateFormat) throws ApplicationException{

        if(StringUtils.isEmpty(dateString)) {
            log.info("搜索的日期参数为空！");
            return null;
        }
        //日期字符串不为空
        Date date = dateUtil.StringToDate(dateString, dateFormat);
        if(null == date) {
            log.error("【user：多条件查询用户列表接口中-日期格式转换错误】");
            throw new ApplicationException(ResponseCode.DATE_FORMAT_CONVERSION_ERROR);
        }
        return date;
    }

    /****************************************************************************************
                                       修改相关
     ***************************************************************************************/
    /**
     * 根据id更新实体信息
     * @param entity 实体信息，其中包含id
     * @return 无返回
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyById(User entity) {

        Long id = entity.getId();
        if(null == id) {
            log.error("");
            return;
        }
        userDao.updateById(entity);
    }

    /**
     * 新增一个用户
     * @param entity 实体信息，其中可能包含id，也可能没有id
     * @return 无返回
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(User entity) {
        //这里对entity实体进行处理
        userDao.insert(entity);
    }

    /**
     * 根据id更新实体信息
     * @param modifyMap 需要更新的实体信息，其中包含id
     * @return 无返回
     */
    @Override
    public User modifyInformation(Map<String, Object> modifyMap) throws ApplicationException{

        //根据account账号查询
        String account = (String) modifyMap.get("account");
        User user = getByAccount(account);
        if(null == user) {
            log.error("【user：修改用户个人信息接口-根据账号搜索不到用户实体】");
            throw new ApplicationException(ResponseCode.USER_NOT_EXIST_ERROR);
        }
        //更新user实体，判断是否有空值
        copyUserProperties(user, modifyMap);
        user.setUpdateTime(new Date());
        //更新user实体到数据库中
        modifyById(user);
        //营业执照，这个在公司表中，根据id查询公司信息进行更新
        Corporation corporation = corporationService.getById(user.getCorporationId());
        if(null == corporation) {
            log.error("【user：修改用户个人信息接口-根据公司id搜索不到公司实体】");
            throw new ApplicationException(ResponseCode.CORPORATION_NOT_EXIST_ERROR);
        }
        //个人名片
        List<String> businessLicenseUrlList = (List<String>) modifyMap.get("businessLicenseUrlList");
        if(null != businessLicenseUrlList && businessLicenseUrlList.size() > 0) {
            //这里对营业执照的数据进行处理后保存
            //本文目前采用取第一条数据
            corporation.setBusinessLicenseUrl(businessLicenseUrlList.get(0));
        }
        corporation.setUpdateTime(new Date());
        corporationService.modifyById(corporation);
        return user;
    }
    /**
     * 根据id更新实体信息
     * @param target 需要更新的实体信息，其中包含id
     * @param sourceMap 需要更新的实体信息，其中包含id
     * @return 无返回
     */
    private void copyUserProperties(User target, Map<String, Object> sourceMap) {
        //用户类型
        Integer targetType = (Integer) sourceMap.get("targetType");
        if(null != targetType) {
            target.setTargetType(targetType);
        }
        //真实姓名
        String realName = (String) sourceMap.get("realName");
        if(null != realName) {
            target.setRealName(realName);
        }
        //公司名称
        String corporationName = (String) sourceMap.get("corporationName");
        if(null != corporationName) {
            target.setCorporationName(corporationName);
        }
        //公司职位
        String position = (String) sourceMap.get("position");
        if(null != position) {
            target.setPosition(position);
        }
        //邮箱
        String email = (String) sourceMap.get("email");
        if(null != email) {
            target.setEmail(email);
        }
        //个人名片
        List<String> businessCardUrlList = (List<String>) sourceMap.get("businessCardUrlList");
        if(null != businessCardUrlList && businessCardUrlList.size() > 0) {
            //这里对个人名片的数据进行处理后保存
            //本文目前采用取第一条数据
            target.setBusinessCardUrl(businessCardUrlList.get(0));
        }
    }


    /****************************************************************************************
                                      新增相关
     ***************************************************************************************/
    /**
     * 根据id更新实体信息
     * @param user 实体信息，其中包含id
     * @param businessCardUrlList 实体信息，其中包含id
     * @param businessLicenseUrlList 实体信息，其中包含id
     * @return 无返回
     */
    @Override
    public User add(User user, List<String> businessCardUrlList, List<String> businessLicenseUrlList) throws ApplicationException{

        //判断用户是否注册
        String account = user.getAccount();
        User storedUser = getByAccount(account);
        if(null != storedUser) {
            log.error("【user：添加用户信息接口-根据输入账号搜索到已存在用户】");
            throw new ApplicationException(ResponseCode.USER_ALREADY_EXIST_ERROR);
        }
        //根据填写公司名称查找公司信息
        String corporationName = user.getCorporationName();
        Corporation corporation = corporationService.getByName(corporationName);
        //公司信息不存在，创建公司实体
        if(null == corporation) {
            corporation = corporationService.addInformation(corporationName);
        }
        //公司信息存在并关联
        user.setCorporationId(corporation.getId());
        //处理个人名片和营业执照
        //将信息插入到数据库中
        add(user);
        return user;
    }
}
