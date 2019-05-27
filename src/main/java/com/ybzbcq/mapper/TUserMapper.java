package com.ybzbcq.mapper;

import com.ybzbcq.model.TUser;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer tId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUser> selectAllUser();
}