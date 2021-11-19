package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class MyCpu{
    Object[] x;
    public MyCpu(){
        x=new Object[20];
    }
}
public class ReadTools {
    void start(String path) throws IOException {
        MyCpu cpu=new MyCpu();
        for (String s:analyseWords(splitFile(path))){
            String[] str=s.split(" ");
                switch (str[0]){
                    case "11":
                        cpu.x[1]=str[1];break;
                    case "21":
                        cpu.x[2]=str[1];break;
                    case "31":
                        cpu.x[3]=str[1];break;
                    case "41":
                        cpu.x[4]=str[1];break;
                    case "12":
                        System.out.println(cpu.x[1]);break;
                    case "22":
                        System.out.println(cpu.x[2]);break;
                    case "32":
                        System.out.println(cpu.x[3]);break;
                    case "42":
                        System.out.println(cpu.x[4]);break;
                    case "99":
                        if (str[1]!=null){
                            System.out.println("程序已经结束");
                        }else {
                            System.err.println("报错！！！报错！！！");
                        }
                        System.exit(0);
                        break;
                }
        }
    }
    byte[] readFile(String path) throws IOException {
        File file=new File(path);
        InputStream fis=new FileInputStream(file);
        //OutputStream fos=new FileOutputStream(file);
        byte[] buffer=new byte[10240];
        fis.read(buffer);
        fis.close();
        return buffer;
    }
    String[] splitFile(String path) throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        byte[] bytes = readFile(path);
        for (byte i:bytes){
            stringBuilder.append((char)i);
        }
        return stringBuilder.toString().split("\n");
    }

    String[] analyseWords(String[] strings){
        StringBuilder stringBuilder=new StringBuilder();
        for (String s:strings){
            stringBuilder.append(analyseWord(s));
        }
        String str=stringBuilder.toString();
        str=str.substring(0,str.length()-1);//把最后一个\n去掉
        return str.split("\n");
    }


    //11 1x 数据
    //12 数据 对1x 进行赋值
    //13 对1x打印
    //11 1x/数据
    //注意！！！！
    //看当前行有没有特殊变量 1x 数据
    String analyseWord(String string) {
        MySyntaxTreeNode temp = MySyntaxTree.head;
        String[] ts = string.split(" ");
        if (temp.left[0] == null) {
            return "99\n";
        } else
            for (String str : ts) {
                for (MySyntaxTreeNode node : temp.left) {
                    if (node == null) {
                        if (str.contains(":")) {
                            String[] sss = str.split(":");
                            if (!sss[0].equals("88")) {
                                return "99\n";
                            }
                            if (temp.no==null){
                                return "99\n";
                            }
                            return temp.no + " " + sss[1]+"\n";
                        }
                        //中间没匹配上
                        return "99\n";
                    } else {
                        if (node.k.equals(str)) {
                            temp = node;
                            break;
                        }
                    }
                }
            }
        //最后 11 88:1x 33 88:11-->11 88:1x 33
        //数据段选择不写
        if (temp.no != null) {
            if (string.contains(":")) {
                String[] ss = string.split(":");
                if (!ss[0].equals("88")) {
                        return temp.no+"\n";
                }
                return temp.no + " " + ss[1] + "\n";
            }
        }
        return "99\n";
    }

}
