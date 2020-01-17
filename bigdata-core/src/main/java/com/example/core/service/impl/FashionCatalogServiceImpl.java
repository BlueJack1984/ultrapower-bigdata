package com.example.core.service.impl;

import com.example.core.dao.IFashionCatalogDao;
import com.example.core.entity.ApplicationCase;
import com.example.core.entity.FashionCatalog;
import com.example.core.entity.Product;
import com.example.core.entity.Solution;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IApplicationCaseService;
import com.example.core.service.IFashionCatalogService;
import com.example.core.service.IProductService;
import com.example.core.service.ISolutionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FashionCatalogServiceImpl implements IFashionCatalogService {


    private final IFashionCatalogDao fashionCatalogDao;
    private final IProductService productService;
    private final ISolutionService solutionService;
    private final IApplicationCaseService applicationCaseService;

    private static final String PRODUCT_TARGET_TYPE = "0";
    private static final String SOLUTION_TARGET_TYPE = "1";
    private static final String APPLICATION_CASE_TARGET_TYPE = "2";
    private static final String TOTAL_AMOUNT_FLAG = "total";

    /****************************************************************************************
                                    基础服务
     ***************************************************************************************/
    @Override
    public FashionCatalog getById(Long id) {
        return null;
    }

    @Override
    public void modifyById(FashionCatalog entity) {

    }

    @Override
    public void add(FashionCatalog entity) {

    }


    /****************************************************************************************
                                         定制服务
     ***************************************************************************************/

    @Override
    public Map<String, Object> getListByConditionPage(String keyword, Integer offset, Integer pageSize) throws ApplicationException{

        //分页操作
        Page<FashionCatalog> page = PageHelper.startPage(offset, pageSize);
        List<FashionCatalog> fashionCatalogList = fashionCatalogDao.selectListByConditionPage(keyword);
        //Long totalAmount = page.getTotal();
        PageInfo<FashionCatalog> pageInfo = new PageInfo<>(fashionCatalogList);
        Long totalAmount = pageInfo.getTotal();
        //统计各个实体的数值
        Map<String, List<Long>> map = new HashMap<>();
        for(FashionCatalog fashionCatalog : fashionCatalogList) {
            String targetTypeKey = fashionCatalog.getTargetType().toString();
            Long targetId = fashionCatalog.getTargetId();
            List<Long> idList = map.get(targetTypeKey);
            if(null == idList) {
                //没有添加过
                List<Long> newList = new ArrayList<>();
                newList.add(targetId);
                map.put(targetTypeKey, newList);
            }else {
                //添加过,更新数据
                idList.add(targetId);
                map.put(targetTypeKey, idList);
            }
        }
        Map<String, Object> result = getListByGroup(map);
        result.put(TOTAL_AMOUNT_FLAG, totalAmount);
        return result;
    }

    private Map<String, Object> getListByGroup(Map<String, List<Long>> map) throws ApplicationException {

        Map<String, Object> collection = new HashMap<>();
        //产品
        List<Long> productIds = map.get(PRODUCT_TARGET_TYPE);
        if(null != productIds && productIds.size() > 0) {
            List<Product> productList = productService.getListByIds(productIds);
            collection.put(PRODUCT_TARGET_TYPE, productList);
        }
        //解决方案
        List<Long> solutionIds = map.get(SOLUTION_TARGET_TYPE);
        if(null != solutionIds && solutionIds.size() > 0) {
            List<Solution> solutionList = solutionService.getListByIds(solutionIds);
            collection.put(SOLUTION_TARGET_TYPE, solutionList);
        }
        //成功案例
        List<Long> applicationCaseIds = map.get(APPLICATION_CASE_TARGET_TYPE);
        if(null != applicationCaseIds && applicationCaseIds.size() > 0) {
            List<ApplicationCase> applicationCaseList = applicationCaseService.getListByIds(applicationCaseIds);
            collection.put(APPLICATION_CASE_TARGET_TYPE, applicationCaseList);
        }
        return collection;
    }
}
