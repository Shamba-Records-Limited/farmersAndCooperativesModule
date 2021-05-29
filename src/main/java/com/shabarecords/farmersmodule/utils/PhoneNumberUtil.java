package com.shabarecords.farmersmodule.utils;

public class PhoneNumberUtil {

    private static final String pattern1 = "^([0]+[7|1]\\d{8})$";
    private static final String pattern2 = "^([+254]+[7|1]\\d{8})$";
    private static final String pattern3 = "^([254]+[7|1]\\d{8})$";
    private static final String pattern4 = "^([7|1]\\d{8})$";
    // public static final String generalPattern = "^(?:254|\\+254|0)?(?:(?:(7(?:(?:[01236789][0-9])|(4[0-3|5-6|8-9])|(5[0-6])))|(1(?:(0[0-2])|(1[0-1]))))[0-9]{6})$";

    public static final String generalPattern = "^(?:254|\\+254|0)?(?:(?:(7(?:(?:[01236789][0-9])|(4[0-3|5-6|8-9])|(5[0-9])))|(1(?:(0[0-2])|(1[0-5]))))[0-9]{6})$";



//    public static String safaricomRegex ="^(?:254|\\+254|0)?(?:(?:(7(?:(?:[0129][0-9])|(4[0-3|5-6|8])|(5[7-9])|(6[8-9])))|(1(?:(1[0-5]))))[0-9]{6})$";
//
//    public static String airtelRegex = "^(?:254|\\+254|0)?(?:(?:(7(?:(?:[38][0-9])|(5[0-6])|(6[2|7])))|(1(?:(0[0-2]))))[0-9]{6})$";
//    public static String equitelRegex = "^(?:254|\\+254|0)?(7(?:(6[3-6]))[0-9]{6})$";
//    public static String telkomRegex = "^(?:254|\\+254|0)?(7(?:(7[0-9]))[0-9]{6})$";
//    public static String faibaRegex = "^(?:254|\\+254|0)?(7(?:(4[9]))[0-9]{6})$";


    // Standardize phone number
    public static String getFormattedPhoneNumber(String phoneNumber) {
        String prefix = "254";

        if (isValidPhoneNumber(phoneNumber)) {
            if (phoneNumber.matches(pattern1)) {
                return prefix + phoneNumber.replaceFirst("0", "").trim();
            } else if (phoneNumber.matches(pattern2)) {
                return phoneNumber.replaceFirst("\\+", "").trim();

            } else if (phoneNumber.matches(pattern4)) {
                return prefix + phoneNumber;
            } else {
                return phoneNumber;
            }
        }

        return null;
    }


    // Validate phone number
    public static boolean isValidPhoneNumber(String phoneNumber) {

        return phoneNumber.trim().matches(generalPattern);
    }


    public static String getUserName(String username){
        if(isValidPhoneNumber(username))
            return getFormattedPhoneNumber(username);

        return username;
    }




    // MSISDN = CC + NDC + SN
    //     CC = Country Code
    //     NDC = National Destination Code. It identifies one or part of a Public Land Mobile Network (PLMN)
    //     SN = Subscriber Number


}
