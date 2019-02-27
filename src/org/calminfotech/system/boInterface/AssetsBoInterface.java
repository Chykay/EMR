package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.Assets;

public interface AssetsBoInterface {
	
	public List<Assets> fetchAll();	

	public Assets getAssetsId(int id);
	
	public void save(Assets assets);
	
	public void update(Assets assets);
	
	public void delete(Assets assets);

}
