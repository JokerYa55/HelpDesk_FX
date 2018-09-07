/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author vasil
 */
public class utils {

    public static final LocalDate NOW_LOCAL_DATE() {
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    public static final LocalDate getLocalDate(Date dateIn) {
        LocalDate res = null;
        String date = new SimpleDateFormat("dd-MM-yyyy").format(dateIn);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        res = LocalDate.parse(date, formatter);
        return res;
    }
    
    public static Date localDateToDate(LocalDate date)
    {
        Date tDate = null;
        LocalDate localDate = date;
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        tDate = Date.from(instant);
        return tDate; 
    }
}
