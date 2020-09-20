package com.icegone.spring.boot.blog.repository.es;

import static org.assertj.core.api.Assertions.assertThat;

import com.icegone.spring.boot.blog.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icegone.spring.boot.blog.repository.BlogRepository;

/**
 * BlogRepository 测试类.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BlogRepositoryTest {
	
	@Autowired
    private EsBlogRepository esBlogRepository;
	
	@Autowired
    private BlogRepository blogRepository;
	
	
	@Before
	public void initData() {
	}

    /**在maven 项目中建立测试类时，基类只用作加载spring配置文件，里面没有任何方法，在打包编译时 报错
     解决方案：  写一个方法（没任何操作），添加@Test注解*/
    @Test
    public void contextLoads() {
    }
}
