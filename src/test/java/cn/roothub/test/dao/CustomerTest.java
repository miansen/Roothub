package cn.roothub.test.dao;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import cn.roothub.dao.CustomerDao;
import cn.roothub.entity.Customer;
import cn.roothub.test.base.BaseTest;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public class CustomerTest extends BaseTest {

	@Autowired
	private CustomerDao customerDao;
	
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
}
