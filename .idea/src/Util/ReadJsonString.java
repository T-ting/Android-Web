package Util;

/*
当前端发送Json数据 无法通过 request.getParameter 直接获取
需通过输入流读数据 转换为文本数据
在通过FastJson转换为指定的对象
 */

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public  class ReadJsonString {

    //json字符串拼接
    public static String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();

        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }

}
