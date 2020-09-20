package com.icegone.spring.boot.blog.test;


import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class HelloWorld {
	Logger logger =Logger.getLogger("JAVA SE练习中");
		public static void main(String[] args) {
				//1.1创建一个可以存储多个同学名字的容器
				ArrayList<Student> list = new ArrayList<Student>();
				/*
				 * 1.存储全班同学信息
				 */
				addStudent(list);
				/*
				 * 2.打印全班同学每一个人的信息（姓名、年龄）
				 */
				printStudent(list);
				/*
				 * 3.随机对学生点名，打印学生信息
				 */
				randomStudent(list);
			}
			/**
			 * 1.存储全班同学名字
			 */
			public static void addStudent(ArrayList<Student> list) {
				//键盘输入多个同学名字存储到容器中
				Scanner sc = new Scanner(System.in);
				for (int i = 0; i < 3; i++) {
					//创建学生
					Student s = new Student();
					System.out.println("存储第"+i+"个学生姓名：");
					s.name = sc.next();
					System.out.println("存储第"+i+"个学生年龄：");
					s.age = sc.nextInt();
					//添加学生到集合
					list.add(s);
				}
			}

			/**
			 * 2.打印全班同学每一个人的信息（姓名、年龄）
			 */
			public static void printStudent (ArrayList<Student> list) {
				for (int i = 0; i < list.size(); i++) {
					Student s = list.get(i);
					System.out.println("姓名："+s.name +",年龄："+s.age);
				}
			}
			/**
			 * 3.随机对学生点名，打印学生信息
			 */
			public static void randomStudent (ArrayList<Student> list) {
				//在班级总人数范围内，随机产生一个随机数
				int index = new Random().nextInt(list.size());
				//在容器（ArrayList集合）中，查找该随机数所对应的同学信息（姓名、年龄）
				Student s = list.get(index);
				System.out.println("被随机点名的同学："+s.name + "，年龄:" + s.age);
			}
			/**获取1-99的奇数和*/
			@Test
			public void getSum(){
				int sum=0;
				for(int i=1;i<100;){
					sum+=i;
					i+=2;
				}
				System.out.println(sum);
			}

			/**水仙花数*/
			@Test
			public void getFlower(){
				for(int i=1;i<1000;i++){
					if(i==Math.pow(i/100,3)+Math.pow(i/10%10,3)+Math.pow(i%10,3)){
						System.out.println(i+" ");
					}
				}
			}

			/**ASCII*/
			@Test
			public void getAscii(){
				char da = 'A';
				char xiao = 'a';
				for (int i = 0; i < 26; i++) {
					System.out.println("大写字母 "+da+" ,小写字母 "+xiao);
					da++; //更新大写字母值
					xiao++; //更新小写字母值
				}

			}

			/**9*9乘法表*/
			@Test
			public void getNN(){
				//第一行
				for(int i=1;i<=9;++i){
					//行内的多少列
					for(int j=1;j<=i;++j){
						System.out.print(i+"*"+j+" ");
					}
					System.out.println();
				}
			}

			/**打印数组元素*/
			@Test
			public void getArrayE(){
				int arr[] ={11, 33, 44, 22, 55};
				System.out.print("[");
				for(int i=0;i<arr.length;i++){
					if(i==arr.length-1){
						System.out.println(arr[i]+"]");
					}else {
						System.out.print(arr[i]+",");
					}
				}
			}

			/**数组元素逆序*/
			@Test
			public void getReverse(){
				int arr[] ={11, 33, 44, 22, 55};
				int temp;
				int length = arr.length;
				for(int i=0;i<arr.length/2;i++){
					temp = arr[i];
					arr[i] = arr[length-i-1];
					arr[length-i-1] =temp;
				}
			}

			/**选择排序 大->小*/
			@Test
			public void getChooseSort(){
				int arr[] ={11, 33, 44, 22, 55,43};
				// i 为所选择的 索引
				for(int i =0;i<arr.length;i++){
					//一轮比较
					for(int j=i+1;j<arr.length;j++){
						if(arr[i]<arr[j]){
							swap(arr,i,arr,j);
						}
					}
				}
			}
			/**冒泡排序*/
			@Test
			public void getbubbleSort(){
				int arr[] ={11, 33, 44, 22, 55,43};
				//每一轮 不再排序已经冒泡的 以最大的索引为界
				for(int i =arr.length-1;i>=0;--i){
					//每一轮冒泡
					for(int j=0;j<i;j++){
						if(arr[j]>arr[j+1]){
							swap(arr,j,arr,j+1);
						}
					}
				}
			}

			/**普通查找*/
			@Test
			public void getNormalSearch(){
				int arr[] ={11, 22, 33, 44, 55,66};
				//查找55
				int destination = 55;
				for(int i = 0; i<arr.length;i++){
					if(arr[i]==destination){
						System.out.println(i);
					}
				}
			}

			/**二分查找:移动 mid*/
			@Test
			public void getBinarySearch(){
				int arr[] ={11, 22, 33, 44, 55,66};
				//查找55
				int destination = 55;
				//左中右 索引
				int min =0;
				int mid = -1;
				int max = arr.length-1;
				while(min<max){
					mid= (min+max)/2;
					//等于 ：找到
					if(arr[mid]==destination){
						System.out.println(mid);
						break;
						//大于:在左边
					}else if(arr[mid]>destination){
						max = mid-1;
						//小于：在右边
					}else{
						min = mid+1;
					}
				}
			}
			/**二分查找:移动 mid*/
			@Test
			public void getKeySet(){
				Map<String,String> map = new HashMap<>();
				map.put("a","A");
				map.put("b","B");
				map.put("c","B");
				map.put("d","D");
				Set<String> set =map.keySet();
				//for增强
				for(String s:set){
					System.out.print(map.get(s)+" ");
				}
				System.out.println();
				//迭代器
				Iterator<String> iterator = set.iterator();
				while(iterator.hasNext()){
					System.out.print(map.get(iterator.next())+" ");
				}
			}
			/**entrySet遍历Map*/
			@Test
			public void getEntrySet(){
				Map<String,String> map = new HashMap<>();
				map.put("a","A");
				map.put("b","B");
				map.put("c","B");
				map.put("d","D");

				Set<Map.Entry<String,String>> set = map.entrySet();
				for(Map.Entry<String,String> m:set){
					System.out.println(m.getKey()+" " +m.getValue());
				}
			}
			/**多线程实现：继承Thread类*/
			@Test
			public void getThread(){
				System.out.println("多线程实现：继承Thread类:主");
				new myThread("son").start();
				new myThread("son").start();
				for(int i =0;i<1000;i++){
					System.out.println("多线程实现：继承Thread类:主::"+i);
				}
			}

			class myThread extends Thread{
				//线程名
				public myThread(String name){

				}
				//重写run方法
				@Override
				public void run(){
					System.out.println("多线程实现：继承Thread类:子：："+this.getName());
				}
			}

			/**线程的匿名内部类使用*/
			@Test
			public void getAnousmousInnerClass(){
				//重写Thread 的run方法
				new Thread(){
					@Override
					public void run(){
						System.out.println("重写Thread 的run方法："+this.getName());
					}
				}.start();

				//实现 Runnable接口：需交给Thread管理
				new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("实现 Runnable接口:"+Thread.currentThread().getName());
					}
				}).start();
			}

			/**使用线程池：接口Runnable/Callable*/
			@Test
			public void getFixPool(){
				//接口Runnable
				ExecutorService service =  Executors.newFixedThreadPool(3);
				//实例化接口实现类
				MyRunnable myRunnable = new MyRunnable();
				//交由 线程池 管理并调用 run方法
				service.submit(myRunnable);
				service.submit(myRunnable);
				service.submit(myRunnable);

				//关闭线程池，自动关闭线程
				service.shutdown();
			}

			//接口Runnable 实现类
	class MyRunnable implements Runnable{
				@Override
				public void run(){
					System.out.println("接口Runnable 实现类"+Thread.currentThread().getName());
				}
	}

			/**使用线程池：接口Runnable/Callable*/
			@Test
			public void getFixedPool(){
				//接口Runnable
				ExecutorService service =  Executors.newFixedThreadPool(3);
				//实例化接口实现类
				MyCallable myCallable = new MyCallable();
				//交由 线程池 管理并调用 run方法
				service.submit(myCallable);
				service.submit(myCallable);
				service.submit(myCallable);

				//关闭线程池，自动关闭线程
				service.shutdown();
			}

			//接口Callable  实现类
	class MyCallable implements Callable{
				@Override
				public Object call() throws Exception {
					System.out.println("接口Callable 实现类"+Thread.currentThread().getName());
					return "good";
				}

	}

			/**使用线程池 实现Callable接口*/
			@Test
			public void useExecutorsFixedPool(){
				//创建线程池服务
				ExecutorService service = Executors.newFixedThreadPool(2);
				//实例化 Callable接口实现类
				MyCallableFixed mc_1 =new MyCallableFixed(5,10);
				MyCallableFixed mc_2 =new MyCallableFixed(10,20);

				//提交线程 交由线程池里的线程执行
				//并使用Future 接收返回结果
				try{
					Future<Integer> submit_1 = service.submit(mc_1);
				Future<Integer> submit_2 = service.submit(mc_2);

				//获取 Future 的结果
					logger.info("线程1执行结果......"+submit_1.get());
					logger.info("线程2执行结果......"+submit_2.get());

				}catch (InterruptedException i){
					logger.warning("InterruptedException......");
				}catch (ExecutionException e){
					logger.warning("ExecutionException......");
				}finally {
					service.shutdown();
				}

			}

	class MyCallableFixed implements Callable<Integer>{
				int a = 3;
				int b = 3;
				public MyCallableFixed(){};
				public MyCallableFixed(Integer a,Integer b){
					this.a = a;
					this.b = b;
				}
				//
				@Override
				public Integer call(){
					return a+b;
				}
			}

			/**测试 切割字符串里没有的字符：结果：长度为1，即将 原字符串作为 结果数组的 第一个多音对应的值*/
			@Test
			public void testString(){
				String s = "abc";
				String i = s.split("d")[0];
				System.out.println(i);
			}

			/**测试 try 代码块有 return ，是否会执行 finally里的代码块，执行顺序如何
			 * @test 所注解的方法 只能为 void
			 * */
			@Test
			public void tryReturn(){
				boolean b= getTryReturn();
			}

			public boolean getTryReturn(){
				System.out.println(1);
				//ss未初始化，编译未通过
				/*String ss;
				System.out.println("s="+ss);*/
				try{
					System.out.println(2);
					//System.exit(0);
					return true;
				}catch (Exception e){
				}finally {
					int x = 20;
					int y = 5;
					System.out.println(x+y+""+(x+y)+y);
					return false;
				}
			}
			/**自动装拆箱*/
			@Test
			public void unpackage(){
				int i =0;
				Integer j = new Integer(0);
				//程序无法执行
				//logger.info("自动装拆箱：i==j"+ i==j);
				String s ="7";
				System.out.println(s+i);
				int arr[]=null;
				arr[0]=1;
			}

			/**使用 InetAddress*/
			@Test
			public void testInetAddress() throws UnknownHostException{
				InetAddress local = InetAddress.getLocalHost();
				InetAddress remote = InetAddress.getByName("www.icegone.cn");
				System.out.println("本机的IP地址：" + local.getHostAddress());
				System.out.println("icegone的IP地址：" + remote.getHostAddress());
				System.out.println("icegone的主机名为：" + remote.getHostName());
			}
			/**使用 多线程*/
			@Test
			public void testMultThread(){
				Thread[] threads =new Thread[3];
				for(int i=0;i<threads.length;i++){
					threads[i] =new Thread(new Runnable() {
						@Override
						public void run() {
							for (int j = 0; j <5 ; j++) {
								System.out.print(j);
							}
							System.out.println("|");
						}
					});
				}

				for(Thread thread:threads){
					thread.start();
				}
			}









			//交换数据
			public void swap(int arrO[],int o, int arrT[],int t){
				int temp;
				temp = arrO[o];
				arrO[o] = arrT[t];
				arrT[t] = temp;
			}

	}
	
	class Student {
			String name; //姓名
			int age; //年龄
		}