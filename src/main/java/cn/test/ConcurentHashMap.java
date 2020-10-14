package cn.test;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:Summer
 * @Date:2020/9/25 19:02
 * @Description:
 *                        concurrentHashMap
 */
@Slf4j(topic="c.test")
public class ConcurentHashMap {
    static final String ALPHA="qwertyuiopasdfghjklzmnxbcv";

    public static void main(String[] args) {
        int length=ALPHA.length();
        int count=200;
        List<String>list=new ArrayList<>(length*count);
        for (int i = 0; i < length; i++) {
            char ch=ALPHA.charAt(i);
            for (int i1 = 0; i1 < count; i1++) {
                list.add(String.valueOf(ch));
            }
        }
        Collections.shuffle(list);
        for (int i = 0; i < 26; i++) {
            try(PrintWriter out=new PrintWriter(new OutputStreamWriter(new FileOutputStream("tmp/"+(i+1)+".txt"))))
            {
                String collect=list.subList(i*count,(i+1)*count).stream().collect(Collectors.joining("\n"));
                out.print(collect);

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
