package Mizut452.time_keeper.Service;

import Mizut452.time_keeper.Mapper.TimekeepMapper;
import Mizut452.time_keeper.Model.Entity.Timekeep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class AddTimeKeepService {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    @Autowired
    TimekeepMapper timekeepMapper;

    public void addTimekeep(Timekeep timekeep) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        UserDetails principal = (UserDetails) auth.getPrincipal();
        timekeep.setUsername(principal.getUsername());
        timekeep.setWdate(now.format(f));
        timekeepMapper.add(timekeep);
    }
}
