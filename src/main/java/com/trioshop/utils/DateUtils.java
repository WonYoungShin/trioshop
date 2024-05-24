package com.trioshop.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    private final static DateUtils dateUtils = new DateUtils();

    private DateUtils() {
    }

    /*
     일자추가
    public List<LocalDate> generateSalesDates() {
        List<LocalDate> list = new ArrayList<>();
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        for (int year = currentYear; year >= 2021; year--) {
            int startMonth = (year == currentYear) ? currentMonth : 12;
            for (int month = startMonth; month >= 1; month--) {
                YearMonth yearMonth = YearMonth.of(year, month);
                for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
                    list.add(LocalDate.of(year, month, day));
                }
            }
        }
        return list;
    }


     */

    public List<Integer> generateYearList() {
        List<Integer> yearList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        for (int year = currentYear; year >= 2021; year--) {
            yearList.add(year);
        }
        return yearList;
    }

    public List<Integer> generateMonthList() {
        List<Integer> monthList = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            monthList.add(month);
        }
        return monthList;
    }

    public static DateUtils getDateUtils() {
        return dateUtils;
    }
}
