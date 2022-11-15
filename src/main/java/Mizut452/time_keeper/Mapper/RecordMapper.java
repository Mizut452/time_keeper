package Mizut452.time_keeper.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordMapper {

    Record findUsername(String username);
}
