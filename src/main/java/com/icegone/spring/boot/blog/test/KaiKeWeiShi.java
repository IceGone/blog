package com.icegone.spring.boot.blog.test;



import java.util.Scanner;

/**
 * @author Icegone
 * @date 2019-7-15
 */
public class KaiKeWeiShi {
    /**用户输入某年某月，某天是这一年中的第几天*/
    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入年");
        int year = scanner.nextInt();
        System.out.println("请输入月");
        int month = scanner.nextInt()-1;
        System.out.println("请输入天");
        int day = scanner.nextInt();
        scanner.close();
        //年 的月的天数:默认为 平年
        int []daysOfMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        //为闰年，修改2月为闰月
        if(isleap(year)){
            daysOfMonth[1]=29;
        }
        //获取总天数
        int days =day;
        for(int i=0;i<month;i++){
            days+=daysOfMonth[i];
        }
        System.out.println("是第"+days+"天");
        getTriAngle(2);

    }
    /**判断是否为闰年*/
    public static boolean isleap(int year){
        /**为闰年*/
        if((year%4==0&&year%100!=0)||year%400==0){
            return true;
        }
        else{
            /**为平年*/
            return false;
        }
    }

    /**对数组进行排序*/
    /**杨辉三角:count>=2*/
    public static void getTriAngle( int count){
        //大于二 才输出
        if(count>=2){
        int[][] ta=new int[count][count];
        //初始化左右边缘的1
        ta[0][0] =1;
        for(int i=1;i<count;i++){
            ta[i][0] = ta[i][i] = 1;
        }
        //对每行数判断 进行赋值,从第三行开始
        for(int i=2;i<count;i++){
            //从每行第二个位置开始,索引为1
            for(int j=1;j<i;j++){
                ta[i][j]=ta[i-1][j-1]+ta[i-1][j];
            }
        }
        //遍历
        for(int i=0;i<count;i++){
            //输出数字
            for(int k=0;k<=i;k++){
                System.out.print(ta[i][k]+" ");
            }
            System.out.println();
        }
    }else{//小于二
            System.out.println(1);
        }
    }

    /**按照格式输出数组相同的内容以及给内容的位置*/
}
