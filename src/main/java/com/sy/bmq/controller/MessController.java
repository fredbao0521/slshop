package com.sy.bmq.controller;


import com.sy.bmq.model.Affiche;
import com.sy.bmq.model.LeaveMessage;
import com.sy.bmq.model.LeaveReply;
import com.sy.bmq.model.User;
import com.sy.bmq.service.UserService;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.AfficheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/affiche")
public class MessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessController.class);

    @Autowired
    private AfficheService afficheService;
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public BaseResult findAll(int limit, int page, BaseResult result) {
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
            if (i > 0) {
                result.setCode(BaseResult.CODE_SUCCESS);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(BaseResult.CODE_FAILED);
        return result;
    }


    @RequestMapping("/updateaffiche.do")
    public BaseResult updateAffiche(Affiche affiche, BaseResult result) {
        System.out.println(affiche.toString());

        int i = 0;
        try {
            i = afficheService.updateAffiche(affiche);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(i + "=====");
        if (i > 0) {
            result.setCode(BaseResult.CODE_SUCCESS);
            result.setMsg(BaseResult.MSG_SUCCESS);
            System.out.println(111111);
        } else {
            result.setCode(BaseResult.CODE_FAILED);
            result.setMsg(BaseResult.MSG_FAILED);
        }
        return result;
    }

    @RequestMapping("/save.do")
    public BaseResult saveAffiche(Affiche affiche, BaseResult result, HttpServletRequest request) {
        try {
            String remoteUser = request.getRemoteUser();
            affiche.setPublisher(remoteUser);
            System.out.println(affiche.toString());
            int i = afficheService.addAffiche(affiche);
            if (i > 0) {
                result.setCode(BaseResult.CODE_SUCCESS);
            } else {
                result.setCode(BaseResult.CODE_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/getSession.do")
    public BaseResult getSession(HttpServletRequest request) throws Exception {
        BaseResult baseResult = new BaseResult();
        String remoteUser = request.getRemoteUser();
        User user = userService.selectByUsername(remoteUser);
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setData(user);
        return baseResult;
    }


    @RequestMapping("/findByName.do")
    public BaseResult findByName(int limit, int page, HttpServletRequest request) throws Exception {
        BaseResult baseResult = new BaseResult();
        String remoteUser = request.getRemoteUser();
        List<LeaveMessage> leaveMessages = afficheService.findMessByName(page, limit, remoteUser, baseResult);
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setData(leaveMessages);
        return baseResult;
    }

    @RequestMapping("/findAllMess.do")
    public BaseResult findAllMess(int limit, int page) throws Exception {
        BaseResult baseResult = new BaseResult();
        List<LeaveMessage> leaveMessages = afficheService.findAllMess(page, limit, baseResult);
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setData(leaveMessages);
        return baseResult;
    }

    @RequestMapping("/saveMess.do")
    public BaseResult saveMess(LeaveMessage leaveMessage, HttpServletRequest request) throws Exception {
        String code = generateMessNumber();
        BaseResult baseResult = new BaseResult();
        String remoteUser = request.getRemoteUser();
        leaveMessage.setCreatedBy(remoteUser);
        leaveMessage.setMessageCode(code);
        int i = afficheService.saveMess(leaveMessage);
        if (i > 0) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
        }
        return baseResult;
    }


    @RequestMapping("/removeById.do")
    public BaseResult removeById(Integer id) throws Exception {
        BaseResult baseResult = new BaseResult();
        int i = afficheService.removeById(id);
        if (i > 0) {
            baseResult.setCode(BaseResult.CODE_SUCCESS);
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
        }
        return baseResult;
    }

    @RequestMapping("/saveMessage.do")
    public BaseResult saveMessage(LeaveReply leaveReply, HttpServletRequest request)throws Exception{
        BaseResult result= new BaseResult();
        String remoteUser = request.getRemoteUser();
        leaveReply.setCreatedBy(remoteUser);
        System.out.println(leaveReply.toString());
        int i = afficheService.saveMessage(leaveReply);
        int modify = afficheService.updateMessage(leaveReply.getMessageId());
        if (i>0 && modify>0){
            result.setMsg(BaseResult.MSG_SUCCESS);
            result.setCode(BaseResult.CODE_SUCCESS);
        }else {
            result.setCode(BaseResult.CODE_FAILED);
            result.setMsg(BaseResult.MSG_FAILED);
        }
        return  result;
    }



    /**
     * 获取当前时间,与随机字符串组合成一个不会重复的留言编码
     *
     * @return
     */
    public static String generateMessNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = sdf.format(cal.getTime());
        return format + getRandomNum(5);
    }

    /**
     * 获取随机字符串
     *
     * @param num
     * @return
     */
    public static String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
