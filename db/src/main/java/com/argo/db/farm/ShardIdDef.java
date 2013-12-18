package com.argo.db.farm;

/**
 * 
 * 分布式Id结构定义类
 * 
 * @author yaming_deng
 * @date 2013-1-17
 */
public class ShardIdDef implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5552278534878280679L;
	
	
	private Integer shardId;
	private Integer objectTypeId;
	private Long localId;
	private Long fullId;
	
	/**
	 * @return the shardId
	 */
	public Integer getShardId() {
		return shardId;
	}
	/**
	 * @param shardId the shardId to set
	 */
	public void setShardId(Integer shardId) {
		this.shardId = shardId;
	}
	/**
	 * @return the objectTypeId
	 */
	public Integer getObjectTypeId() {
		return objectTypeId;
	}
	/**
	 * @param objectTypeId the objectTypeId to set
	 */
	public void setObjectTypeId(Integer objectTypeId) {
		this.objectTypeId = objectTypeId;
	}
	/**
	 * @return the localId
	 */
	public Long getLocalId() {
		return localId;
	}
	/**
	 * @param localId the localId to set
	 */
	public void setLocalId(Long localId) {
		this.localId = localId;
	}
	
	/**
	 * @param shardId
	 * @param objectTypeId
	 * @param localId
	 */
	public ShardIdDef(Integer shardId, Integer objectTypeId, Long localId, Long fullId) {
		super();
		this.shardId = shardId;
		this.objectTypeId = objectTypeId;
		this.localId = localId;
		this.fullId = fullId;
	}
	
	public ShardIdDef(Integer objectTypeId, Long localId) {
		super();
		this.shardId = 1;
		this.objectTypeId = objectTypeId;
		this.localId = localId;
		this.fullId = localId;
	}
	
	public void setFullId(Long fullId) {
		this.fullId = fullId;
	}
	public Long getFullId() {
		return fullId;
	}
}
