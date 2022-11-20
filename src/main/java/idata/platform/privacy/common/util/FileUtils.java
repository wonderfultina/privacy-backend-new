package idata.platform.privacy.common.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtils {
    public  void saveJsonData(String jsonString, String jsonFilePath) {

        // 在文件夹目录下新建文件
        File file = new File(jsonFilePath);

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    System.out.println();
                }
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");
            // 写入内容
            osw.write(jsonString);
            // 换行
            osw.write("\r\n");
        } catch (Exception e) {
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                System.out.println();
            }
        }
    }
}
