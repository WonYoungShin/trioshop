package com.trioshop.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class DateUtils {

    // 연도 리스트 생성 메서드 (최근 10년)
    public List<Integer> getYearList() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> yearList = new ArrayList<>();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            yearList.add(i);
        }
        return yearList;
    }

    // 월 리스트 생성 메서드 (1월부터 12월까지)
    public List<Integer> getMonthList() {
        List<Integer> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i);
        }
        return monthList;
    }

    // 오늘의 연도 가져오기
    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    // 오늘의 연도 가져오기
    public int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

}