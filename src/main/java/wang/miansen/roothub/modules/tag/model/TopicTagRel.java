package wang.miansen.roothub.modules.tag.model;

/**
 * @Author: miansen.wang
 * @Date: 2019/8/19 22:37
 */
public class TopicTagRel {

    /**
     * 话题标签关联ID
     */
    private Integer topicTagRelId;

    /**
     * 话题ID
     */
    private Integer topicId;

    /**
     * 标签ID
     */
    private Integer tagId;

    public Integer getTopicTagRelId() {
        return topicTagRelId;
    }

    public void setTopicTagRelId(Integer topicTagRelId) {
        this.topicTagRelId = topicTagRelId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "TopicTagRel{" +
                "topicTagRelId=" + topicTagRelId +
                ", topicId=" + topicId +
                ", tagId=" + tagId +
                '}';
    }
}
