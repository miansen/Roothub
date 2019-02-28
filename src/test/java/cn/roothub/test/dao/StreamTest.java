package cn.roothub.test.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-02-28
 */
public class StreamTest {

	@Test
	public void test01() {
		Stream<Integer> stream = Stream.iterate(0, x -> x + 2).limit(10);
		// stream.forEach( a -> System.out.println(a));
		stream.forEach(System.out::println);
	}
	
	@Test
	public void test02() {
		Runnable r = () -> System.out.println("hello lambda!");
		r.run();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello thread!");
			}
		});
		thread.run();
	}
	
	@Test
	public void test03() {
		Consumer<String> con = (x) -> System.out.println(x);
	}
	
	@Test
	public void test04() {
		List<Double> list = Arrays.asList(10.0, 20.0, 30.0);
		Stream<Double> map = list.stream().map(x -> x + x * 0.5);
		map.forEach(a -> System.out.println(a));
	}
	
	@Test
	public void test05() {
		String a = "abc";
		String substring = a.substring(1);
		System.out.println(a);
		System.out.println(substring);
	}
	
	@Test
	public void test06() {
		List<Double> list = Arrays.asList(10.0, 20.0, 30.0);
		Double double1 = list.stream().map(x -> x + x * 0.5).reduce((a,b) -> a + b).get();
		System.out.println(double1);
	}

	@Test
	public void test07() {
		List<String> asList = Arrays.asList("a","b");
		 asList.stream().map(String :: toUpperCase).forEach(System.out :: println);
	}
}
