package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.Timekeep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TimekeepMapper {
    List<Timekeep> selectAll();
    void add(Timekeep timekeep);
    List<Timekeep> principalSelectAll(String username);

    void deleteItem(Timekeep timekeep);

    @Update("UPDATE timekeeplist SET subject = #{subject}, context = #{context}, totalTime = #{totalTime}, Wdate = #{Wdate} WHERE username = #{username}")
    boolean updateItem(Timekeep timekeep);
}
