package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.Timekeep;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimekeepMapper {
    List<Timekeep> selectAll();
    void add(Timekeep timekeep);
}
