package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.Timekeep;
import Mizut452.time_keeper.Model.Entity.TimekeepUpdateReq;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TimekeeperMapperTest {
    @Autowired
    TimekeepMapper timekeepMapper;

    @Test
        void doAdd() {
           //addする準備
           Timekeep timekeep = new Timekeep();
           timekeep.setUsername("testMan");
           timekeep.setSubject("testSubject");
           timekeep.setContext("testContext");
           timekeep.setTotalTime("testTime");
           timekeep.setWdate("testWdate");
           timekeepMapper.add(timekeep);

           //きちんとaddされているかUsernameで確認
           assertEquals("testMan", timekeepMapper.selectAll().get(0).getUsername());
    }

    @Test
        void doPrincipalSelectAll() {
        //まずadd
        doAdd();

        //検索
        List<Timekeep> timekeeps = timekeepMapper.principalSelectAll("testMan");

        //確認
        assertEquals("testTime", timekeeps.get(0).getTotalTime());
    }

    @Test
        void doUpdateItem() {
        Timekeep timekeep = new Timekeep();
        timekeep.setUsername("testMan");
        timekeep.setSubject("testSubject");
        timekeep.setContext("testContext");
        timekeep.setTotalTime("testTime");
        timekeep.setWdate("testWdate");
        timekeepMapper.add(timekeep);

        System.out.println(timekeep.getSubject());
        assertEquals("testSubject", timekeepMapper.principalSelectAll("testMan").get(0).getSubject());

        TimekeepUpdateReq timekeepUpdateReq = new TimekeepUpdateReq();
        timekeepUpdateReq.setSubject("testUpdateSubject");
        timekeepUpdateReq.setTimekeepid(timekeep.getTimekeepid());
        timekeepUpdateReq.setWdate(timekeep.getWdate());
        timekeepUpdateReq.setContext(timekeep.getContext());
        timekeepUpdateReq.setTotalTime(timekeep.getTotalTime());
        timekeepMapper.update(timekeepUpdateReq);

        assertEquals("testUpdateSubject", timekeepMapper.principalSelectAll("testMan").get(0).getSubject());
    }

    @Test
        void doDelete() {
        Timekeep timekeep = new Timekeep();
        timekeep.setUsername("testMan");
        timekeep.setSubject("testSubject");
        timekeep.setContext("testContext");
        timekeep.setTotalTime("testTime");
        timekeep.setWdate("testWdate");
        timekeepMapper.add(timekeep);

        assertEquals(timekeep.getUsername(), timekeepMapper.principalSelectAll("testMan").get(0).getUsername());

        timekeepMapper.delete(timekeep.getTimekeepid());

        //成功するとエラーが出る。
        assertEquals(null, timekeepMapper.principalSelectAll("testMan").get(0).getUsername());
    }
}
