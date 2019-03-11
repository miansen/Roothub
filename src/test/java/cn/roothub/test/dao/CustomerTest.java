package cn.roothub.test.dao;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.roothub.base.BaseEntity;
import cn.roothub.dao.CustomerDao;
import cn.roothub.entity.Customer;
import cn.roothub.test.base.BaseTest;
import com.sun.management.OperatingSystemMXBean;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public class CustomerTest extends BaseTest {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private BaseEntity baseEntity;
	
	@Test
	public void insertBatchTest() {
		List<Customer> list = new ArrayList<>();
		for(int i = 0;i < 10;i++) {
			Customer customer = new Customer();
			customer.setName("张三");
			customer.setAge(i);
			list.add(customer);
		}
		int insertBatch = customerDao.insertBatch(list);
		System.out.println(insertBatch);
	}
	
	@Test
	public void test01() {
		// 获取操作系统的名字
		String property = System.getProperty("os.name");
		
		// 内存
	    int kb = 1024;
	    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
	        .getOperatingSystemMXBean();
	    // 总的物理内存
	    long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
	    //已使用的物理内存
	    long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
	    // 获取系统cpu负载
	    double systemCpuLoad = osmxb.getSystemCpuLoad();
	    // 获取jvm线程负载
	    double processCpuLoad = osmxb.getProcessCpuLoad();
	    
	    
	    
	    double a = (double)(Math.round(totalMemorySize/1024/1024 * 10000)/10000.0);
	    
	    System.out.println(a);
	    
	    float b = (float)totalMemorySize/1024/1024;
	    DecimalFormat df = new DecimalFormat("0.0");
	    System.out.println(df.format(b));
	    System.out.println("操作系统的名字: "+property);
	    System.out.println("总的物理内存: "+totalMemorySize+"KB");
	    System.out.println("总的物理内存: "+totalMemorySize/1024+"MB");
	    System.out.println("总的物理内存: "+totalMemorySize/1024/1024+"G");
	    System.out.println((double)(Math.round((0.7844546131144321) * 10000)/10000.0));
	    System.out.println("已使用的物理内存: "+usedMemory);
	    System.out.println("获取系统cpu负载: "+systemCpuLoad);
	    System.out.println("获取jvm线程负载: "+processCpuLoad);
	    
	    
	}
	
	@Test
	public void test02() {
		//cn.roothub.base.BaseEntity@53f6fd09
		//cn.roothub.base.BaseEntity@8c3619e
		System.out.println(baseEntity);//cn.roothub.base.BaseEntity@8c3619e
	}
	
	@Test
	public void test03() {
		BaseEntity baseEntity2 = new BaseEntity();
		System.out.println("baseEntity2: "+baseEntity2);//cn.roothub.base.BaseEntity@8c3619e
		System.out.println("baseEntity: "+baseEntity);//cn.roothub.base.BaseEntity@4331d187
	}
	
	@Test
	public void test04() {
		BaseEntity baseEntity2 = new BaseEntity();
		System.out.println("baseEntity2: "+baseEntity2);//cn.roothub.base.BaseEntity@8c3619e
		System.out.println("baseEntity: "+baseEntity);//cn.roothub.base.BaseEntity@4331d187
	}
	
}
