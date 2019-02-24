package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviceMonitor.intf.dao.SysParamMapper;
import com.deviceMonitor.intf.service.ISysParamService;
import com.deviceMonitor.model.SysParam;
import com.deviceMonitor.util.ListRange;

@Service
public class SysParamService implements ISysParamService {
	@Autowired
	private SysParamMapper sysParamMapper;
	
	@Override
	public int insert(SysParam sysParam) {
		return sysParamMapper.insert(sysParam);
	}

	@Override
	public int update(SysParam sysParam) {
		return sysParamMapper.update(sysParam);
	}

	@Override
	public int replace(SysParam sysParam) {
		return sysParamMapper.replace(sysParam);
	}

	@Override
	public int delete(String id) {
		return sysParamMapper.delete(id);
	}

	@Override
	public SysParam query(String id) {
		return sysParamMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = sysParamMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<SysParam> list = sysParamMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<SysParam> listAll() {
        return sysParamMapper.listAll();
    }
    
    @Override
    public List<SysParam> list(int start, int limit) {
        return sysParamMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return sysParamMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return sysParamMapper.isExists(params);
	}
    
    @Override
    public List<SysParam> listByParams(Map params) {
        return sysParamMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<SysParam> listSysParamInsert) {
        return sysParamMapper.batchInsert(listSysParamInsert);
    }

    @Override
    public int batchUpdate(List<SysParam> listSysParamUpdate) {
        return sysParamMapper.batchUpdate(listSysParamUpdate);
    }
    
    @Override
    public int batchReplace(List<SysParam> listSysParamReplace) {
        return sysParamMapper.batchReplace(listSysParamReplace);
    }

	@Override
	public int batchDelete(String[] delIds) {
		return sysParamMapper.batchDelete(delIds);
	}
}