package com.example.core.service.impl;

import com.example.core.constants.ResponseCode;
import com.example.core.dao.IUserDao;
import com.example.core.entity.User;
import com.example.core.exception.ApplicationException;
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
        //分页操作
        Page<User> page = PageHelper.startPage(offset, pageSize);
        List<User> userList = userDao.selectListByConditionPage(dateStart, dateEnd, keyword);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
        //RowBounds rowBounds = new RowBounds(offSet, pageSize);
        //List<Information> list = null;
        //list = informationMapper.selectListByCondition(condition, rowBounds);
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

    @Override
    public void add(User entity) {
        //这里对entity实体进行处理
        userDao.insert(entity);
    }

    @Override
    public User modifyInformation(Map<String, Object> modifyMap) {

        //根据account账号查询
        String account = (String) modifyMap.get("account");
        User user = getByAccount(account);

        //更新user实体，判断是否有空值

        //更新user实体到数据库中
        modifyById(user);
        return getByAccount(account);
    }


    /****************************************************************************************
                                      新增相关
     ***************************************************************************************/
    /**
     * 根据id更新实体信息
     * @param entity 实体信息，其中包含id
     * @return 无返回
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User add(User user, List<String> businessCardUrlList, List<String> businessLicenseUrlList) {

        User user1 = new User();
        user1.setTargetType(0);
        user1.setCorporationId(0L);
        user1.setPosition("CEO");
        user1.setAccount("durant");
        user1.setPassword("123456");
        user1.setRealName("kevin");
        user1.setIdentityNumber("1234567890");
        user1.setNickName("death god");
        user1.setEmail("durant@163.com");
        user1.setGender(0);
        user1.setCountry("USA");
        user1.setCity("new york");
        user1.setStatus(0);
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());
        userDao.insert(user1);
        return user1;
    }
}
