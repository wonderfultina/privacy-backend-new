package idata.platform.privacy.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IdRandomUtils {
    /**
     * 生成随机ID：当前年月日时分秒 +五位随机数
     */
    public static Long getRandomID() {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int ran = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return Long.valueOf(str + ran);// 当前时间
    }

    public static Date timeStamp2Date(Long timeLong) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
