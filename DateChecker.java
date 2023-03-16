///**
// * This class checks whether a provided date number is valid or not
// *
// * @author Huong Thien Do
// *
// */
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.time.LocalDateTime;
//
//public class DateChecker implements DateValidator {
//    /**
//     * Checks if the given date number is a valid date number.
//     *
//     * @param date the date number to validate
//     * @return true is the number if a valid date number, false otherwise
//     */
//    @Override
//    public boolean validate(Date date) {
//        if (date == null || date.equals("")) {
//            return false;
//        }
//
//        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        String strDate = dateFormat.format(date);
//
//        strDate = strDate.replaceAll("/", "");
//        if (strDate.length() != 8) return false;
//
////        for (int i = 0; i < 8; i++) {
////            if (strDate.charAt(i) < '0' || strDate.charAt(i) > '9') {
////                throw new NumberFormatException("Invalid number type detected");
////            }
////        }
//        int month = Integer.parseInt(strDate.substring(0, 2));
//        if (month < 0 && month > 12) {
//            throw new NumberFormatException("Invalid month type detected");
//        }
//        int day = Integer.parseInt(strDate.substring(2, 4));
//        if (day < 0 && day > 31) {
//            throw new NumberFormatException("Invalid day type detected");
//        }
//        int year = Integer.parseInt(strDate.substring(4, 8));
//        if (year < 2022) {
//            throw new NumberFormatException("Invalid year type detected");
//        } else if (year == 2022) {
//
//        }
//
//        return true;
//    }
//}
