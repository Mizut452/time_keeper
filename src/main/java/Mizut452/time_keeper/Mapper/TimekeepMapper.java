package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.Timekeep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TimekeepMapper {
    List<Timekeep> selectAll();
    void add(Timekeep timekeep);
    List<Timekeep> principalSelectAll(String username);

    void deleteItem(Timekeep timekeep);

    void updateItem(Timekeep timekeep);


    List<Timekeep> selectUpdateItem(int timekeepid);

    @Select("SELECT timekeepid FROM timekeeplist WHERE timekeepid = #{timekeepid}")
    List<Timekeep> selectTimeKeepId(int timekeepid);
}
