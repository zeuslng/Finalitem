package com.atguigu.springboot;

import com.atguigu.springboot.entities.comment;
import com.atguigu.springboot.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Scanner;
import java.util.Scanner;

public class test{
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String s = sc.nextLine();
//        System.out.println(tar(s));
    }

    @Autowired
    private CommentService commentService;


    // 递归方法
    public static String tar(String s) {
        // 递归结束的判断，说明全部解压完毕
        if (!s.contains("[") && !s.contains("|")) {
            return s;
        }
        // 形如2|cd的变成cdcd
        if (!s.contains("[") && s.contains("|")) {
            String x[] = s.split("\\|");
            int num = Integer.parseInt(x[0]);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < num; i++)
                sb.append(x[1]);
            return sb.toString();
        }
        // 上面if都不执行，说明既有[又有|，说明没有递归到最里层
        char a[] = s.toCharArray();
        // 用来存储完全解压后的字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            // 设置栅栏，使得"["与"]"的个数相同，比如HG[3|B[2|CA]]F,会取得[3|B[2|CA]]
            int latch = 0;
            if (a[i] == '[') {
                latch++;
                // 指针往前进一位，形如[3|B[2|CA]]，需要得到3|B[2|CA],为了去掉最外面的括号
                i++;
                if (a[i] == ']') {
                    latch--;
                }
                // 用来存储部分解压的字符串，比如有字符串HG[3|B[2|CA]]F中的,这次while循环结束 s1会变成3|B[2|CA]
                // 这里再次进行'['的判断是存在[[]]的情况
                StringBuffer s1 = new StringBuffer();
                while (!(a[i] == ']' && latch == 0)) {
                    if (a[i] == '[') {
                        latch++;
                    }
                    if (a[i] == ']') {
                        latch--;
                        if (latch == 0) {
                            // 说明到了最外层的]了，不进行下面的appen，为了取出最外层的[]
                            continue;
                        }

                    }
                    s1.append(a[i]);
                    // 指针后移，再次进入while循环
                    i++;

                }
                // 如果有初始字符串HG[3|B[2|CA]]F，此时s1为3|B[2|CA]，去除了一层括号，
                String s2 = tar(s1.toString());
                // 判断里面还有没有未解压的字符串，有就继续解压，会递归到最里面的2|CA，得到CACA，返回到s2=3|BCACA,再次进行解压
                while (s2.contains("|")) {
                    s2 = tar(s2);
                }
                // 将解压完毕的字符串字符串加到sb后面
                sb.append(s2);
            } else {
                // 如果没有进行压缩的字符串，直接加到末尾就行
                sb.append(a[i]);

            }

        }
        return sb.toString();

    }
}