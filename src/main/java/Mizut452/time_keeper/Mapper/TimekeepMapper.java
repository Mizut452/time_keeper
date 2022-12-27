package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.Timekeep;
import Mizut452.time_keeper.Model.Entity.TimekeepUpdateReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TimekeepMapper {

    @Select("SELECT * FROM timeKeepList")
    List<Timekeep> selectAll();

    @Insert("INSERT INTO timeKeepList(username, subject, context, totalTime, WhatDate) VALUES(#{username}, #{subject}, #{context}, #{totalTime}, CURRENT_TIMESTAMP)")
    void add(Timekeep timekeep);

    @Select("SELECT * FROM timeKeepList WHERE username = #{username}")
    List<Timekeep> principalSelectAll(String username);

    @Select("SELECT * FROM timeKeepList WHERE timeKeepId = #{timeKeepId}")
    Timekeep findById(int timeKeepId);

    @Update("UPDATE timeKeepList SET subject = #{subject}, context = #{context}, totalTime = #{totalTime}, WhatDate = #{WhatDate} WHERE timeKeepId = #{timeKeepId}")
    void update(TimekeepUpdateReq timekeepUpdateReq);

    @Delete("DELETE FROM timeKeepList WHERE timeKeepId = #{timeKeepId}")
    void delete(int timeKeepId);
}
