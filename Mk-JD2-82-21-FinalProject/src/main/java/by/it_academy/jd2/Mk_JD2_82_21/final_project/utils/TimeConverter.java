package by.it_academy.jd2.Mk_JD2_82_21.final_project.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TimeConverter {

    public  LocalDateTime convertFromMillsToLocalDateTime(Long milliSeconds){
        LocalDateTime result = Instant.ofEpochMilli(milliSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return result;
    }

    public  Long convertFromLocalDateTimeToMills(LocalDateTime localDateTime){
        Long result = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return result;
    }

    public  LocalDate convertFromMillsToLocalDate(Long milliSeconds){
        LocalDate result = Instant.ofEpochMilli(milliSeconds).atZone(ZoneId.systemDefault()).toLocalDate();
        return result;
    }
}
