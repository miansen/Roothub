package wang.miansen.roothub.common.util;

import java.util.UUID;

/**
 * <p>生成新的文件名</p>
 * @author: miansen.wang
 * @date: 2019-03-20
 */
public class FileNameUtil {

	/**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return UUID.randomUUID().toString().replace("-", "") + getSuffix(fileOriginName);
    }
}
