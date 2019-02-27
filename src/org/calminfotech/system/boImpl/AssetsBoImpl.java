package org.calminfotech.system.boImpl;

import java.util.List;

import org.calminfotech.system.boInterface.AssetsBoInterface;
import org.calminfotech.system.daoInterface.AssetsDao;
import org.calminfotech.system.models.Assets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssetsBoImpl implements AssetsBoInterface {

	@Autowired
	private AssetsDao assetsDao;
	
	@Override
	public List<Assets> fetchAll() {
		return assetsDao.fetchAll();
	}

	@Override
	public Assets getAssetsId(int id) {
		return assetsDao.getAssetsId(id);
	}

	@Override
	public void save(Assets assets) {
		assetsDao.save(assets);
	}

	@Override
	public void update(Assets assets) {
		assetsDao.update(assets);
	}

	@Override
	public void delete(Assets assets) {
		assetsDao.delete(assets);
	}

}
