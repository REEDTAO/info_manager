package com.reed.info_manager.constant;


import java.util.Random;

public  class Constant  {
    public static final Integer FAIL_JOIN_USER_ROLE=0;
    public static final Integer SUCCESS=1;
    public static final String FILE_ROOT_DIR= "/Users/luweitao/Documents/FILE/";


    public static String randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return "#"+changeToHexString(r)+changeToHexString(g)+changeToHexString(b);
    }
    public static String changeToHexString(int i){
        String str = Integer.toHexString(i);
        if(str.length()==1){
            return "0"+str;
        }else return str;
    }


}
