package priv.sen.root.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import priv.sen.root.entity.RootSection;

public interface RootSectionDao {

	/**
	 * 查询所有板块
	 * @return
	 */
    List<RootSection> selectAll();

    /**
     * 根据ID查询板块
     * @param sectionId
     * @return
     */
    RootSection selectBySectionId(@Param("sectionId") Integer sectionId);
    
    /**
     * 根据名称查询板块
     * @param sectionId
     * @return
     */
    RootSection selectBySectionName(@Param("sectionName") String sectionName);

    /**
     * 根据是否显示状态查询板块
     * @param showStatus
     * @return
     */
    List<RootSection> selectByShowStatus(@Param("showStatus") boolean showStatus);

    /**
     * 根据板块标签查询板块
     * @param sectionTab
     * @return
     */
    RootSection selectByShowTab(@Param("sectionTab") String sectionTab);
    
    /**
     * 添加板块
     * @param rootSection
     * @return
     */
    int insert(RootSection rootSection);

    /**
     * 根据ID更新板块
     * @param rootSection
     * @return
     */
    int updateBySectionId(RootSection rootSection);

    /**
     * 根据ID删除板块
     * @param id
     * @return
     */
    int deleteBySectionId(@Param("sectionId") Integer sectionId);

}
