package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Summer
 * @Date:2020/9/16 14:44
 * @Description:
 */
@Slf4j(topic = "c.test")
public class DownLoader {
    public static  List<String>download() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://www.baidu.com/").openConnection();
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line=reader.readLine())!=null)
            {
                lines.add(line);
            }
        }
        return lines;
    }
    public static void main(String[] args) throws Exception {
        List<String>list=DownLoader.download();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            log.debug("fd");
        }
    }

}
