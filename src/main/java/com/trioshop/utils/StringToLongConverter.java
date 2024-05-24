package com.trioshop.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringToLongConverter {

    public static List<Long> convertListOfStringsToLongs(List<String> stringList) {
        return stringList.stream().map(Long::parseLong)
                .collect(Collectors.toList());
    }
}