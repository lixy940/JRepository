package com.hansy.dao.mybaties.secondary;


import com.hansy.domain.secondary.Message;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */
public interface MessageMapper {

    List<Message> getAll();

    Message getOne(Long id);

    void insert(Message Message);

//    void update(Message Message);

}
