package com.sy.zy.service;

import com.sy.bmq.model.Report2;
import com.sy.zy.dao.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {
    @Autowired
    Report reportService;
    @Override
    public List<Report2> find(Integer type) throws Exception {
        return reportService.find(type);
    }

}
