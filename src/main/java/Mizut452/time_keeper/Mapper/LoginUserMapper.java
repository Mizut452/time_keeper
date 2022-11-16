package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginUserMapper {
    List<LoginUser> selectAll();
    LoginUser selectUsername(String username);
}
