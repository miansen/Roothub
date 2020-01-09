package wang.miansen.roothub.modules.tag.service.impl;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class TagServiceImpl extends AbstractBaseServiceImpl<Tag, TagDTO> implements TagService {

	@Autowired
	private ITagDao tagDao;

	@Override
	public Function<? super TagDTO, ? extends Tag> getDTO2DOMapper() {
		return tagDTO -> {
			Tag tag = new Tag();
			tag.setTagId(tagDTO.getTagId());
			tag.setTagName(tagDTO.getTagName());
			tag.setTagAvatar(tagDTO.getTagAvatar());
			tag.setTagContent(tagDTO.getTagContent());
			tag.setTagState(tagDTO.getTagState());
			tag.setCreateDate(DateUtil.string2Date(tagDTO.getCreateDate(), DateUtil.FORMAT_DATETIME));
			tag.setUpdateDate(DateUtil.string2Date(tagDTO.getUpdateDate(), DateUtil.FORMAT_DATETIME));
			return tag;
		};
	}

	@Override
	public Function<? super Tag, ? extends TagDTO> getDO2DTOMapper() {
		return tagDO -> {
			TagDTO tagDTO = new TagDTO();
			tagDTO.setTagId(tagDO.getTagId());
			tagDTO.setTagName(tagDO.getTagName());
			tagDTO.setTagAvatar(tagDO.getTagAvatar());
			tagDTO.setTagContent(tagDO.getTagContent());
			tagDTO.setTagState(tagDO.getTagState());
			tagDTO.setCreateDate(DateUtil.formatDateTime(tagDO.getCreateDate()));
			tagDTO.setUpdateDate(DateUtil.formatDateTime(tagDO.getUpdateDate()));
			return tagDTO;
		};
	}

	@Override
	public BaseDao<Tag> getDao() {
		return tagDao;
	}

}
