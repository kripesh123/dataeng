package com.kripesh.utils;

import java.time.format.DateTimeFormatter;

/**
 * Dictionary of constant values;
 * @author kripesh
 */
public class Dictionary {

    /* DATE_FORMATTERS */
    public static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyhhmmss");
    /* FILE_LOCATION */
    public static final String FILE_LOCATION = "/home/kripesh/sentiance/";
    public static final String DATA_GENERATION = "DATAGENERATION";
    public static final String DATA_UPDATE = "DATAUPDATE";
    public static final String FILE_EXTENSION = ".txt";
    public static final String BACK_SLASH = "/";
    public static final String COMMA = ",";
    public static final String NEW_LINE = "\n";
    public static final String A_Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String a_z = "abcdefghijklmnopqrstuvxyz";
    public static final String NUMBER = "0123456789";
    public static final String GENERATE_DATA_SET = "locations,3,sensors,5,devices,4";
    public static final String UPDATE_DATA_SET = "locations,1,sensors,2,devices,2";
    public static final double THOUSAND = 1000;
    public static final double FILE_SIZE = 1;


}
