package com.sy.bmq.controller;

import com.sy.bmq.common.util.FastDFSClientWrapper;
import com.sy.bmq.model.Information;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Autowired
    private InformationService informationService;

    @RequestMapping("/findAll.do")
    public BaseResult findAll(int limit, int page, BaseResult result) throws Exception {
        List<Information> list = informationService.findAll(page, limit,result);
        if (!list.isEmpty()) {
            result.setData(list);
            result.setMsg(BaseResult.MSG_SUCCESS);
            result.setCode(BaseResult.CODE_SUCCESS);
        } else {
            result.setCode(BaseResult.CODE_FAILED);
            result.setMsg(BaseResult.MSG_FAILED);
        }
        return result;
    }


    @RequestMapping("/remove.do")
    public BaseResult remove(Integer id) throws Exception {
        BaseResult result = new BaseResult();
        informationService.remove(id);
        result.setCode(BaseResult.CODE_SUCCESS);
        result.setMsg(BaseResult.MSG_SUCCESS);
        return result;
    }

    @RequestMapping("/upload.do")
    public BaseResult uploadFile(MultipartFile file, Information information, HttpServletRequest request) throws Exception {

        System.out.println("MultipartFile:" + file);
        information.setPublishTime(new Date());

        byte[] bytes = new byte[0];
        bytes = file.getBytes();
        String originalName = file.getOriginalFilename();
        //获取文件后缀--.doc
        String extension = originalName.substring(originalName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        //获取文件大小
        long fileSize = file.getSize();
        information.setFileName(fileName);
        System.out.println(originalName + "==" + fileName + "==" + fileSize + "==" + extension + "==" + bytes.length);
//        information.getTypeName(extension);
        String fileRe = fastDFSClientWrapper.uploadFile(bytes, fileSize, extension);
        String url = "http://192.168.100.250:8888/" + fileRe;
        System.out.println("图片地址" + url);
        information.setFilePath(fileRe);
        information.setFileSize(fileSize);
        BaseResult result = new BaseResult();
        information.setUploadTime(new Date());
        String remoteUser = request.getRemoteUser();
        information.setPublisher(remoteUser);
        int insert = informationService.insert(information);
        if (insert >0) {
            result.setCode(BaseResult.CODE_SUCCESS);
            result.setData(url);
            return result;
        } else {
            result.setMsg(BaseResult.MSG_FAILED);
            result.setCode(BaseResult.CODE_FAILED);
        }
        return result;

    }

    @RequestMapping("/download.do")
    public void download(String path) throws Exception {
        BaseResult result = new BaseResult();
        byte[] bytes = fastDFSClientWrapper.downloadFile(path);
        String substring = path.substring(path.lastIndexOf("/") + 1);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/bmq/", substring));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        result.setCode(0);
    }

}
