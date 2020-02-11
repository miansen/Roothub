package wang.miansen.roothub.common.enums;

/**
 * @author miansen.wang
 * @date 2020-02-10
 */
public interface BaseMasterDataEnum {

	Integer getCode();
	
	String getDesc();
	
	BaseMasterDataEnum codeOf(Integer code);
}
