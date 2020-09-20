package com.icegone.spring.boot.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	/**在maven 项目中建立测试类时，基类只用作加载spring配置文件，里面没有任何方法，在打包编译时 报错
	 解决方案：  写一个方法（没任何操作），添加@Test注解*/
	@Test
	public void contextLoads() {
	}

}
