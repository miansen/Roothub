package priv.sen.root.serviceImpl.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.roothub.base.BaseEntity;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.dto.RootTopicExecution;
import cn.roothub.entity.RootSection;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.Tab;
import cn.roothub.entity.Tag;
import cn.roothub.service.RootSectionService;
import cn.roothub.service.RootTopicService;
import cn.roothub.service.TabService;
import priv.sen.root.dao.test.BaseTest;

public class RootTopicServiceImplTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RootTopicService rootTopicService;
	@Autowired
	RootSectionService rootSectionService;
	@Autowired
	TabService tabService;
	/**
	 * 测试分页查询所有话题
	 * @throws Exception
	 */
	@Test
	public void pageTest() throws Exception{
		PageDataBody<RootTopic> page = rootTopicService.page(1, 20, "all","all");
		String formatDate = new BaseEntity().formatDate(page.getList().get(0).getCreateDate());
		System.out.println(formatDate);
	}
	
	/**
	 * 测试根据昵称分页查询用户的所有话题
	 * @throws Exception
	 */
	@Test
	public void pageByAuthor() throws Exception{
		PageDataBody<RootTopic> page = rootTopicService.pageByAuthor(1, 20, "public");
		Result<PageDataBody<RootTopic>> result = new Result<>(true, page);
		System.out.println(result);
	}
	
	/**
	 * 测试查询所有话题
	 * @throws Exception
	 */
	@Test
	public void findAllTest() throws Exception{
		List<RootTopic> findAll = rootTopicService.findAll();
		System.out.println(findAll);
	}
	/**
	 * 测试通过ID查询话题
	 * @throws Exception
	 */
	@Test
	public void findById() throws Exception{
		RootTopic findByTopicId = rootTopicService.findByTopicId(30);
		System.out.println(findByTopicId);
	}
	
	/**
	 * 测试发布话题
	 * @throws Exception
	 */
	@Test
	public void saveTopicTest() throws Exception{
		RootTopic topic = new RootTopic();
		topic.setTab("all");
		topic.setTitle("测试发布话题29");
		topic.setTag("delete");
		topic.setCreateDate(new Date());
		topic.setUpdateDate(new Date());
		topic.setLastReplyTime(new Date());
		topic.setLastReplyAuthor("public");
		topic.setViewCount(10);
		topic.setAuthor("public");
		topic.setTop(true);
		topic.setGood(true);
		topic.setShowStatus(true);
		topic.setReplyCount(100);
		topic.setIsDelete(false);
		topic.setTagIsCount(true);
		topic.setContent("哈哈哈哈哈哈哈");
		RootTopicExecution saveTopic = rootTopicService.saveTopic(topic);
		System.out.println(saveTopic);
	}
	/**
	 * 测试-分页查询所有标签
	 * @throws Exception
	 */
	@Test
	public void findByTagTest() throws Exception {
		PageDataBody<Tag> tag = rootTopicService.findByTag(1, 20);
		System.out.println(tag);
	}
	
	/**
	 * 测试-根据标签查询话题
	 * @throws Exception
	 */
	@Test
	public void pageByTagTest() throws Exception{
		PageDataBody<RootTopic> pageByTag = rootTopicService.pageByTag("delete", 1, 20);
		System.out.println(pageByTag);
	}
	
	/**
	 * 分页模糊查询
	 * @throws Exception
	 */
	@Test
	public void pageLikeTest() throws Exception{
		PageDataBody<RootTopic> pageLike = rootTopicService.pageLike(1, 20, "测试");
		System.out.println(pageLike);
	}
	
	/**
	 * 根据板块和昵称分页查询话题
	 * @throws Exception
	 */
	@Test
	public void pageAllByAuthorTest() throws Exception{
		PageDataBody<RootTopic> page = rootTopicService.pageAllByPtabAndAuthor(1, 20, "qna", "Tyche");
		logger.info(page.toString());
	}
	
	@Test
	public void test01() throws Exception{
		List<RootTopic> row1 = rootTopicService.findAll();
		List<RootTopic> rows = CollUtil.newArrayList(row1);
		ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
		writer.addHeaderAlias("ptab", "父板块标识");
		writer.addHeaderAlias("tab", "tab");
		writer.addHeaderAlias("title", "话题标题");
		writer.addHeaderAlias("tag", "话题内容标签");
		writer.addHeaderAlias("content", "话题内容");
		writer.write(rows);
		//关闭writer，释放内存
		writer.close();
	}
	
	@Test
	public void test02() throws Exception{
		Map<String, Object> row1 = new LinkedHashMap<>();
		row1.put("姓名", "张三");
		row1.put("年龄", 23);
		row1.put("成绩", 88.32);
		row1.put("是否合格", true);
		row1.put("考试日期", DateUtil.date());

		Map<String, Object> row2 = new LinkedHashMap<>();
		row2.put("姓名", "李四");
		row2.put("年龄", 33);
		row2.put("成绩", 59.50);
		row2.put("是否合格", false);
		row2.put("考试日期", DateUtil.date());

		ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);
		
		// 通过工具类创建writer
		ExcelWriter writer = ExcelUtil.getWriter("d:/writeMapTest.xlsx");
		// 合并单元格后的标题行，使用默认标题样式
		writer.merge(row1.size() - 1, "一班成绩单");
		// 一次性写出内容，使用默认样式
		writer.write(rows);
		// 关闭writer，释放内存
		writer.close();
	}
	
	@Test
	public void test03() throws Exception{
		List<RootTopic> row1 = rootTopicService.findAll();
		//List<RootTopic> row2 = rootTopicService.findHot(1, 50);
		List<Tab> row2 = tabService.selectAll();
    	List<RootSection> row3 = rootSectionService.findAll();
		List<RootTopic> rows1 = CollUtil.newArrayList(row1);
		List<Tab> rows2 = CollUtil.newArrayList(row2);
		List<RootSection> rows3 = CollUtil.newArrayList(row3);
		//List<List<? extends Object>> rows3 = CollUtil.newArrayList(row1,row2,row3);
		ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest04.xlsx","话题");
		writer.addHeaderAlias("topicId", "话题标识");
		writer.addHeaderAlias("ptab", "父板块标识");
		writer.addHeaderAlias("tab", "tab");
		writer.addHeaderAlias("title", "话题标题");
		writer.addHeaderAlias("tag", "话题内容标签");
		writer.addHeaderAlias("content", "话题内容");
		writer.addHeaderAlias("createDate", "创建时间");
		writer.addHeaderAlias("updateDate", "更新时间");
		writer.addHeaderAlias("lastReplyTime", "最后回复话题时间");
		writer.addHeaderAlias("lastReplyAuthor", "最后回复话题的用户");
		writer.addHeaderAlias("viewCount", "浏览量");
		writer.addHeaderAlias("author", "话题作者");
		writer.addHeaderAlias("top", "1置顶 0默认");
		writer.addHeaderAlias("good", "1精华 0默认");
		writer.addHeaderAlias("showStatus", "1显示 0不显示");
		writer.addHeaderAlias("replyCount", "回复数量");
		writer.addHeaderAlias("isDelete", "1删除 0默认");
		writer.addHeaderAlias("tagIsCount", "话题内容标签是否被统计过 1是 0否默认");
		writer.addHeaderAlias("postGoodCount", "点赞");
		writer.addHeaderAlias("postBadCount", "踩数");
		writer.addHeaderAlias("statusCd", "话题状态 1000:有效 1100:无效 1200:未生效");
		writer.addHeaderAlias("nodeSlug", "所属节点");
		writer.addHeaderAlias("nodeTitle", "节点名称");
		writer.addHeaderAlias("remark", "备注");
		writer.addHeaderAlias("avatar", "话题作者头像");
		writer.write(rows1);
		writer.setSheet("父板块");
		writer.addHeaderAlias("id", "父板块标识");
		writer.addHeaderAlias("tabName", "父板块名称");
		writer.addHeaderAlias("tabDesc", "父板块描述");
		writer.addHeaderAlias("isDelete", "是否删除 0：否 1：是");
		writer.addHeaderAlias("createDate", "创建时间");
		writer.addHeaderAlias("tabOrder", "排列顺序");
		writer.write(rows2);
		writer.setSheet("子板块");
		writer.addHeaderAlias("sectionId", "子板块标识");
		writer.addHeaderAlias("sectionName", "子板块名称");
		writer.addHeaderAlias("sectionTab", "子板块标签");
		writer.addHeaderAlias("sectionDesc", "子板块描述");
		writer.addHeaderAlias("sectionTopicNum", "板块帖子数目");
		writer.addHeaderAlias("showStatus", "是否显示，0:不显示 1:显示");
		writer.addHeaderAlias("displayIndex", "子板块排序");
		writer.addHeaderAlias("defaultShow", "默认显示板块 0:默认 1:显示");
		writer.addHeaderAlias("pid", "模块父节点");
		writer.addHeaderAlias("createDate", "创建时间");
		writer.addHeaderAlias("updateDate", "更新时间");
		writer.addHeaderAlias("statusCd", "板块状态 1000:有效 1100:无效 1200:未生效");
		writer.write(rows3);
		//关闭writer，释放内存
		writer.close();
	}
	
	/**
	 * 查询用户发布主题的数量
	 * @throws Exception
	 */
	@Test
	public void countByUserNameTest() throws Exception{
		int countByUserName = rootTopicService.countByUserName("public");
		System.out.println(countByUserName);
	}
	
	@Test
	public void findIndexHotTest() throws Exception{
		PageDataBody<RootTopic> page = rootTopicService.findIndexHot(1, 50, "good");
		logger.debug(page.toString());
	}
}
