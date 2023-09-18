package com.agendados.master.Service;

public class NumberCleaner {
    public static String cleanerNumber(String number){
       return number.replaceAll("[^0-9]", "");
    }
}