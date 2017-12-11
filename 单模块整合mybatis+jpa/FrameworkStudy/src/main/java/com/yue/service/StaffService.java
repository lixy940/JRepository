package com.yue.service;


import com.yue.entity.Staff;

import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */
public interface StaffService {

    void saveStaff(Staff staff);

   List<Staff> findStaffByUsername(String username);

    Staff findStaffById(long id);
}
