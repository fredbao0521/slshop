package com.sy.bmq.controller;


import com.sy.bmq.model.Affiche;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.AfficheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/affiche")
public class MessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessController.class);

    @Autowired
    private AfficheService afficheService;


    @RequestMapping("/findAll.do")
    public BaseResult findAll(int limit, int page,BaseResult result) {
        try {
            List<Affiche> allAffiches = afficheService.findAllAffiches(page, limit, result);
            result.setData(allAffiches);
            result.setCode(BaseResult.CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    @RequestMapping("/remove.do")
    public BaseResult removeAffiche(int id, BaseResult result) {
        try {
            int i = afficheService.removeAffiche(id);
            if (i>0){
                result.setCode(BaseResult.CODE_SUCCESS);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(BaseResult.CODE_FAILED);
        return result;
    }
}
