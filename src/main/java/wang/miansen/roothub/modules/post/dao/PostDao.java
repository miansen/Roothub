package wang.miansen.roothub.modules.post.dao;

import java.util.Date;
import java.util.List;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.modules.post.model.Post;
import wang.miansen.roothub.modules.tag.model.Tag;
import wang.miansen.roothub.modules.user.model.User;
import org.apache.ibatis.annotations.Param;

public interface PostDao extends BaseDao<Post> {

	/**
	 * 查询所有话题
	 * @return
	 */
	List<Post> selectAll();
	
	/**
	 * 根据板块查询所有话题
	 * @param start
	 * @param limit
	 * @return
	 */
    List<Post> selectAllByTab(@Param("start") Integer start, @Param("limit") Integer limit,@Param("tab") String tab);
    
    /**
     * 根据节点查询所有话题
     * @param start
     * @param limit
     * @param nodeTitle
     * @return
     */
    List<Post> selectAllByNode(@Param("start") Integer start, @Param("limit") Integer limit,@Param("nodeTitle") String nodeTitle);
    
    /**
     * 根据昵称和板块查询话题
     * @param start
     * @param limit
     * @param ptab：板块
     * @param author：用户昵称
     * @return
     */
    List<Post> selectAllByPtabAndAuthor(@Param("start") Integer start, @Param("limit") Integer limit,@Param("ptab") String ptab,@Param("author") String author);
    
    /**
	 * 模糊查询所有话题
	 * @param start
	 * @param limit
	 * @return
	 */
    List<Post> selectByLike(@Param("like") String like, @Param("start") Integer start, @Param("limit") Integer limit);
    
    /**
	 * 根据节点查询所有最新话题
	 * @param start
	 * @param limit
	 * @return
	 */
    List<Post> selectAllNewest(@Param("start") Integer start, @Param("limit") Integer limit,@Param("nodeTitle") String nodeTitle);

    /**
     * 根据板块查询话题
     * @param tab
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectByTab(@Param("tab") String tab, @Param("start") Integer start, @Param("limit") Integer limit);
    
    /**
     * 根据节点查询精华话题
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectAllGood(@Param("start") Integer start, @Param("limit") Integer limit,@Param("nodeTitle") String nodeTitle);

    /**
     * 根据节点查询无人评论的话题
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectAllNoReply(@Param("start") Integer start, @Param("limit") Integer limit,@Param("nodeTitle") String nodeTitle);
    
    /**
     * 侧边栏-今日热门话题
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectHot(@Param("start") Integer start, @Param("limit") Integer limit);
    
    /**
     * 侧边栏-今日等待回复的话题
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectTodayNoReply(@Param("start") Integer start, @Param("limit") Integer limit);
    
    /**
     * 首页-热门话题
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectIndexHot(@Param("start") Integer start, @Param("limit") Integer limit,@Param("tab")String tab);
    
    /**
     * 根据作者昵称分页查询所有话题
     * @param author
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectByAuthor(@Param("author") String author, @Param("start") Integer start, @Param("limit") Integer limit);

    /**
     * 根据话题ID查询话题
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    Post selectByTopicId(@Param("topicId") Integer topicId);

    /**
     * 根据话题名称查询话题
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    Post selectByTopicName(@Param("title") String title);
    
    /**
     * 根据话题名称、话题作者、话题标签、话题内容查询话题
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    Post selectByNameAndAuthorAndTagAndContent(@Param("title") String title,@Param("author") String author,@Param("tag") String tag,@Param("content") String content);
    
    /**
     * 根据标题和时间查询话题
     * @param title
     * @param date
     * @return
     */
    Post selectByTitleAndDate(@Param("title") String title,@Param("date") Date date);
    
    /**
     * 发布话题
     * @param rootTopic
     * @return
     */
    // int insert(Topic rootTopic);
    
    /**
     * 根据ID更新话题
     * @param topic
     * @return
     */
    int updateByTopicId(Post topic);
    
    /**
     * 更新主题的头像
     * @param user
     * @return
     */
    int updateTopicAvatar(User user);
    
    /**
     * 更新节点名称
     * @param oldNodeTitle:老的节点名称
     * @param newNodeTitle:新的节点名称
     * @return
     */
    int updateNodeTitile(@Param("oldNodeTitle") String oldNodeTitle, @Param("newNodeTitle") String newNodeTitle);
    
    /**
     * 根据ID删除话题
     * @param topicId
     * @return
     */
    int deleteById(@Param("topicId") Integer topicId);
    
    /**
     * 根据作者删除话题
     * @param author
     * @return
     */
    int deleteByAuthor(@Param("author") String author);
    
    /**
     * 根据节点统计所有话题
     * @return
     */
    int countTopicByNode(@Param("nodeTitle") String nodeTitle);

    /**
     * 根据节点统计精华话题
     * @return
     */
    int countTopicGoodByNode(@Param("nodeTitle") String nodeTitle);

    /**
     * 根据板块统计话题
     * @param tab
     * @return
     */
    int countTopicByTab(@Param("tab") String tab);

    /**
     * 根据节点统计无人评论的话题
     * @return
     */
    int countTopicNoReplyByNode(@Param("nodeTitle") String nodeTitle);
    
    /**
     * 根据昵称统计所有话题
     * @return
     */
    int countAllByName(@Param("name") String name);
    
    /**
     * 根据昵称和板块统计话题
     * @return
     */
    int countAllByNameAndPtab(@Param("name") String name,@Param("ptab") String ptab);
    
    /**
     * 分组统计所有标签
     * @return
     */
    int countTag();
    
    /**
     * 统计所有模糊查询
     * @return
     */
    int countLike(@Param("like") String like);
    
    /**
     * 根据标签统计话题的数量
     * @param tag
     * @return
     */
    int countByTag(String tag);
    
    /**
     * 首页-热门话题
     * @return
     */
    int countIndexHot(@Param("tab")String tab);
    
    /**
	 * 分页查询所有标签
	 * @param start
	 * @param limit
	 * @return
	 */
    List<Tag> selectAllTag(@Param("start") Integer start, @Param("limit") Integer limit);
    
    /**
     * 根据标签查询话题
     * @param tab
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectByTag(@Param("tag") String tag, @Param("start") Integer start, @Param("limit") Integer limit);
    
    /**
     * 作者的其他话题
     * @param userName
     * @return
     */
    List<Post> selectOther(@Param("userName")String userName,@Param("topicId") Integer topicId);
    
    /**
     * 统计当天的话题数
     * @return
     */
    int countToday();

    /**
     * 后台话题列表
     * @param author
     * @param startDate
     * @param endDate
     * @param start
     * @param limit
     * @return
     */
    List<Post> selectAllForAdmin(@Param("author") String author,@Param("startDate") String startDate,
    						      @Param("endDate") String endDate, @Param("start") Integer start, @Param("limit") Integer limit);
    
    /**
     * 统计后台话题
     * @param author
     * @param startDate
     * @param endDate
     * @return
     */
    int countAllForAdmin(@Param("author") String author,@Param("startDate") String startDate,@Param("endDate") String endDate);
}
