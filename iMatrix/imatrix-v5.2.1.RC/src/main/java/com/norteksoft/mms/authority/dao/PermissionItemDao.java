package com.norteksoft.mms.authority.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.norteksoft.mms.authority.entity.PermissionItem;
import com.norteksoft.mms.authority.enumeration.ItemType;
import com.norteksoft.mms.authority.enumeration.LeftBracket;
import com.norteksoft.mms.authority.enumeration.LogicOperator;
import com.norteksoft.mms.authority.enumeration.RightBracket;
import com.norteksoft.mms.authority.enumeration.UserOperator;
import com.norteksoft.product.orm.Page;
import com.norteksoft.product.orm.hibernate.HibernateDao;
import com.norteksoft.product.util.ContextUtils;
@Repository
public class PermissionItemDao extends HibernateDao<PermissionItem,Long>{

	public void getPermissionItems(Page<PermissionItem> page,Long permissionId){
		this.searchPageSubByHql(page, "from PermissionItem pi where pi.permission.id=?", permissionId);
	}
	public List<PermissionItem> getAllPermissionItems(Long permissionId){
		return this.find("from PermissionItem pi where pi.permission.id=? and pi.companyId=?", permissionId,ContextUtils.getCompanyId());
	}
	public PermissionItem getPermissionItem(ItemType itemType,UserOperator operator, LogicOperator joinType, String conditionValue,Long permissionId,LeftBracket leftBracket,RightBracket rightBracket) {
		List<PermissionItem> permissionItems=this.find("from PermissionItem p where p.companyId=? and p.itemType=? and p.operator=? and p.joinType=? and p.conditionValue=? and p.permission.id=? and p.leftBracket=? and p.rightBracket=?",ContextUtils.getCompanyId(),itemType,operator,joinType,conditionValue,permissionId,leftBracket,rightBracket);
		if(permissionItems!=null&&permissionItems.size()>0){
			return permissionItems.get(0);
		}else{
			return null;
		}
	}
}
