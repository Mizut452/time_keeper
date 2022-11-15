package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.PostRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordMapper {

    PostRecord findUsername(String username);
}
