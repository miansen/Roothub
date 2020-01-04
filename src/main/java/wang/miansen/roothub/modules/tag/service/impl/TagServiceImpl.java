package wang.miansen.roothub.modules.tag.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import wang.miansen.roothub.common.dao.BaseDao;
import wang.miansen.roothub.common.service.impl.AbstractBaseServiceImpl;
import wang.miansen.roothub.common.util.DateUtil;
import wang.miansen.roothub.modules.tag.dao.ITagDao;
import wang.miansen.roothub.modules.tag.dto.TagDTO;
import wang.miansen.roothub.modules.tag.model.Tag;
import wang.miansen.roothub.modules.tag.service.TagService;

/**
 * @author miansen.wang
 * @date 2020-01-04
 */
public class TagServiceImpl extends AbstractBaseServiceImpl<Tag, TagDTO> implements TagService {

	@Autowired
	private ITagDao tagDao;
	
	@Override
	public Tag getBaseDO(TagDTO dto) {
		Tag tag = new Tag();
		tag.setTagId(dto.getTagId());
		tag.setTagName(dto.getTagName());
		tag.setTagAvatar(dto.getTagAvatar());
		tag.setTagContent(dto.getTagContent());
		tag.setTagState(dto.getTagState());
		tag.setCreateDate(DateUtil.string2Date(dto.getCreateDate(), DateUtil.FORMAT_DATETIME));
		tag.setUpdateDate(DateUtil.string2Date(dto.getUpdateDate(), DateUtil.FORMAT_DATETIME));
		return tag;
	}

	/* (non-Javadoc)
	 * @see wang.miansen.roothub.common.service.BaseService#getBaseDao()
	 */
	@Override
	public BaseDao<Tag> getBaseDao() {
		return tagDao;
	}

	/* (non-Javadoc)
	 * @see wang.miansen.roothub.common.service.BaseService#getBaseDTO(wang.miansen.roothub.common.entity.BaseDO)
	 */
	@Override
	public TagDTO getBaseDTO(Tag entity) {
		TagDTO tagDTO = new TagDTO();
		tagDTO.setTagId(entity.getTagId());
		tagDTO.setTagName(entity.getTagName());
		tagDTO.setTagAvatar(entity.getTagAvatar());
		tagDTO.setTagContent(entity.getTagContent());
		tagDTO.setTagState(entity.getTagState());
		tagDTO.setCreateDate(DateUtil.formatDateTime(entity.getCreateDate()));
		tagDTO.setUpdateDate(DateUtil.formatDateTime(entity.getUpdateDate()));
		return tagDTO;
	}

	

}
