package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.service.ServiceDemoService;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	private ServiceDemoService serviceDemo;

	@Test
	public void contextLoads() {

		    System.out.println("开始了");
			String rtn = serviceDemo.queryDemo();
		    System.out.println("返回的结果"+rtn);
	}

}
