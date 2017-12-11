package com.yue.service.impl;


import com.yue.entity.Staff;
import com.yue.repository.StaffDao;
import com.yue.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    /**
     * 保存用户
     * @param staff
     */
    public void saveStaff(Staff staff){
        staffDao.save(staff);
    }

    public List<Staff> findStaffByUsername(String username){
        return staffDao.findByUsername(username);
    }



    public Staff findStaffById(long id){
        return this.staffDao.findById(id);
    }
}
