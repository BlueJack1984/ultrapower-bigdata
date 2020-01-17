package com.example.admin.controller;

import com.example.admin.dto.request.HotspotInput;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.Hotspot;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IHotspotService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/audit")
public class HotspotController {

    private final IHotspotService hotspotService;

    @GetMapping("/get/{id}")
    public OutputResult<Hotspot> getById(@PathVariable("id") Long id) throws ApplicationException {

        //判断参数是否为空
        if(null == id) {
            log.info("");
            return new OutputResult<>(201, "查询hotspot的id参数为空");
        }
        Hotspot hotspot = hotspotService.getById(id);
        return new OutputResult<>(hotspot);
    }

    @PostMapping("/add")
    public OutputResult<Hotspot> add(@RequestBody @Valid HotspotInput hotspotInput) throws ApplicationException{
        Hotspot hotspot = null;
        return new OutputResult<>(hotspot);
    }
}
