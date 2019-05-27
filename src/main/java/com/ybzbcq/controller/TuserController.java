package com.ybzbcq.controller;


import com.ybzbcq.model.TUser;
import com.ybzbcq.service.TuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tuser/")
@Slf4j
public class TuserController {


    @Autowired
    private TuserService tuserService;

    @RequestMapping(value = "id", method = RequestMethod.GET)
    public TUser getById() {

        TUser tUser = tuserService.selectByPrimaryKey(2);

        return tUser;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<TUser> getAll() {
        List<TUser> tUsers = tuserService.selectAllUser();
        return tUsers;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Map<String, Object> updateUser(@RequestBody TUser user) {

        Map<String, Object> result = new LinkedHashMap<String, Object>();

        try {
            int i = tuserService.updateUser(user);
            if (i == 0) {
                result.put("code", 1);
                result.put("msg", "更新发生异常");
            }else if (i == 1){
                result.put("code", 0);
                result.put("msg", "更新成功");
            }
        } catch (Exception ex) {
            result.put("code", 2);
            result.put("msg", "更新发生异常");

            log.error("更新数据发生异常", ex);

        }


        return result;
    }
}
