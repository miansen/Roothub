package wang.miansen.roothub.modules.topic.controller.front;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import wang.miansen.roothub.common.beans.BaseEntity;
import wang.miansen.roothub.common.beans.Page;
import wang.miansen.roothub.common.beans.Result;
import wang.miansen.roothub.common.controller.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.miansen.roothub.common.dto.TopicExecution;
import wang.miansen.roothub.common.util.ApiAssert;
import wang.miansen.roothub.modules.node.model.Node;
import wang.miansen.roothub.modules.reply.model.Reply;
import wang.miansen.roothub.modules.topic.model.Topic;
import wang.miansen.roothub.modules.user.model.User;
import wang.miansen.roothub.modules.tab.model.Tab;
import wang.miansen.roothub.modules.collect.service.CollectService;
import wang.miansen.roothub.modules.node.service.NodeService;
import wang.miansen.roothub.modules.notice.service.NoticeService;
import wang.miansen.roothub.modules.reply.service.ReplyService;
import wang.miansen.roothub.modules.topic.service.TopicService;
import wang.miansen.roothub.modules.topic.service.TabService;

@Controller
public class TopicController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private TabService tabService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private BaseEntity baseEntity;

    /**
     * 话题详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    private String detail(@PathVariable Integer id, Model model, @RequestParam(value = "p", defaultValue = "1") Integer p, HttpServletRequest request) {
        Topic topic = topicService.findByTopicId(id);
        User user = getUser(request);
        ApiAssert.notNull(topic, "话题消失了~");
        // 浏览量+1
        topic.setViewCount(topic.getViewCount() + 1);
        //更新话题
        topicService.updateTopic(topic);
        // 查询回复
        Page<Reply> replyPage = replyService.page(p, 50, id);

        String tag = topic.getTag();
        // 将标签封装成list
        List<String> tags = Arrays.asList(tag.split("\\s+"));

        // 话题被收藏的数量
        int countByTid = collectService.countByTid(id);
        // 发布的主题的数量
        int countTopicByUserName = 0;
        // 收藏话题的数量
        int countCollect = 0;
        // 未读通知的数量
        int notReadNotice = 0;
        if (user != null) {
            countTopicByUserName = topicService.countByUserName(user.getUserName());
            countCollect = collectService.count(user.getUserId());
            notReadNotice = noticeService.countNotReadNotice(user.getUserName());
        }
        model.addAttribute("topic", topic);
        model.addAttribute("replyPage", replyPage);
        model.addAttribute("tags", tags);
        model.addAttribute("user", user);
        model.addAttribute("countByTid", countByTid);
        request.setAttribute("countTopicByUserName", countTopicByUserName);
        request.setAttribute("countCollect", countCollect);
        request.setAttribute("notReadNotice", notReadNotice);
        return "/default/front/topic/view";
    }

    /**
     * 发布话题页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/topic/create", method = RequestMethod.GET)
    private String create(String n, HttpServletRequest request) {
        List<Tab> tabList = tabService.selectAll();
        List<Node> nodeList = nodeService.findAll(null, null);
        request.setAttribute("tabList", tabList);
        request.setAttribute("nodeList", nodeList);
        request.setAttribute("node", n);
        return "/default/front/topic/add";
    }

    /**
     * 发布话题接口
     *
     * @param title：标题
     * @param content：正文
     * @param nodeTitle：节点
     * @param tag：标签，暂时只能输入一个
     * @param type：类型         0：富文本 1：Markdown
     * @param request
     * @return
     */
    @RequestMapping(value = "/topic/save", method = RequestMethod.POST)
    @ResponseBody
    private Result<TopicExecution> save(String title, String content,
                                        String nodeTitle, String tag,
                                        String type, HttpServletRequest request) {
        User user = getUser(request);
        ApiAssert.notNull(user, "请先登录");
        ApiAssert.notNull(title, "标题不能为空");
        if (StringUtils.isEmpty(tag)) tag = null;

        // 如果是 Markdown 格式的正文，则先渲染再保存进数据库
        if ("1".equals(type)) {
            content = baseEntity.formatContent(content);
        }
        TopicExecution saveTopic = topicService.createTopic(title, content, null, null, nodeTitle, tag, user);
        return new Result<TopicExecution>(true, saveTopic);
    }

    /**
     * 根据标签分页查找话题
     *
     * @param name
     * @param model
     * @param p
     * @return
     */
    @RequestMapping(value = "/tag/{name}", method = RequestMethod.GET)
    private String tag(@PathVariable String name, Model model, @RequestParam(value = "p", defaultValue = "1") Integer p) {
        Page<Topic> pageByTag = topicService.pageByTag(name, p, 20);
        model.addAttribute("tagName", name);
        model.addAttribute("pageByTag", pageByTag);
        return "/default/front/tag/view";
    }
}
