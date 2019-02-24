package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.SysParam;

@Repository
public interface SysParamMapper {
	int insert(SysParam obj);
	int update(SysParam obj);
	int replace(SysParam obj);
	int delete(String id);
	SysParam query(String id);
	List<SysParam> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<SysParam> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	List<SysParam> listByParams(Map params);
	int batchInsert(@Param(value = "listInsertSysParam") List<SysParam> listSysParamInsert);
	int batchUpdate(@Param(value = "listUpdateSysParam") List<SysParam> listSysParamUpdate);
	int batchReplace(@Param(value = "listReplaceSysParam") List<SysParam> listSysParamReplace);
	int batchDelete(@Param(value = "delIds") String[] delIds);	
}