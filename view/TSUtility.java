package view;
import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class TSUtility {
    private static Scanner scanner=new Scanner(System.in);


    //返回1 2 3 4中的任意一个，若没有则一直循环
    public static char readMenuSelection(){
        char c;
        for(; ;){
            String str=readKeyBoard(1,false);
            c=str.charAt(0);
            if(c!='1'&&c!='2'&&c!='3'&&c!='4'){
                System.out.println("选择错误，请重新选择：");
            }else break;
        }
        return c;
    }

    //按回车继续键盘输入
    public static void readReturn(){
        System.out.println("按回车键继续...");
        readKeyBoard(100,true);

    }
    //从键盘读取长度不超过2的整数，并将其返回
    public static int readInt(){
        int n;
        for(; ;){
            String str=readKeyBoard(2,false);
            try{
                n=Integer.parseInt(str);//将字符类型变成int类型
                break;
            }catch (NumberFormatException e){
                System.out.println("数字类型错误，请重新输入...");
            }
        }
        return n;
    }

    //从键盘读取 Y/N ，并返回
    public static char readConfirmSelection(){
        char c;
        for(; ;){
            String str=readKeyBoard(1,false).toUpperCase();//变成大写字母
            c=str.charAt(0);
            if(c=='Y'||c=='N'){
                break;
            }else{
                System.out.println("选择错误，请重新输入...");
            }
        }
        return c;
    }


    //从键盘获取数据
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line="";
        while(scanner.hasNextLine()){
            line=scanner.nextLine();
            if(line.length()==0){
                if(blankReturn) return line;
                else continue;
            }
            if(line.length()<1||line.length()>limit){
                System.out.println("输入长度不大于 "+limit+" 的数，请重新输入");
                continue;
            }
            break;
        }
        return line;

    }
}
