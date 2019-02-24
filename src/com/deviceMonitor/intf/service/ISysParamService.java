package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.SysParam;
import com.deviceMonitor.util.ListRange;

import java.util.List;
import java.util.Map;

public interface ISysParamService {
	int insert(SysParam obj);
	int update(SysParam obj);
	int replace(SysParam obj);
	int delete(String id);
	SysParam query(String id);
	void list(ListRange listRange);
	List<SysParam> list(int start, int limit);
	List<SysParam> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<SysParam> listByParams(Map params);
	int batchInsert(List<SysParam> listSysParamInsert);
	int batchUpdate(List<SysParam> listSysParamUpdate);
	int batchReplace(List<SysParam> listSysParamReplace);
	int batchDelete(String[] delIds);	
}