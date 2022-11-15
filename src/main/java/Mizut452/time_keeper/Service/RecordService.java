package Mizut452.time_keeper.Service;

import Mizut452.time_keeper.Mapper.RecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordService {
    private final RecordMapper recordMapper;

    public RecordService(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    @Transactional(readOnly = true)
    public Record findUsername(String username) {
        return recordMapper.findUsername(username);
    }


}
