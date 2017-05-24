/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, ketayao.com
 * Filename:		com.wondersgroup.framework.util.dwz.SpringDataJpaPageConvert.java
 * Class:			SpringDataJpaPageConvert
 * Date:			2012-8-6
 * Author:			<a href="mailto:yang-hui@wondersgroup.com">cms</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.bupt.client.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 解决dwz page 的遗留问题，使程序更易移植和替换
 * 
 * @author <a href="mailto:yang-hui@wondersgroup.com">cms</a> Version 1.1.0
 * @since 2012-8-6 下午10:03:18
 */

public class PageUtils {

	/**
	 * 生成spring data JPA 对象 描述
	 * 
	 * @param page
	 * @return
	 */
	public static Pageable createPageable(DWZPage page) {
		if (StringUtils.isNotBlank(page.getOrderField())) {
			// 忽略大小写
			if (page.getOrderDirection().equalsIgnoreCase(DWZPage.ORDER_DIRECTION_ASC)) {
				return new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage(), Sort.Direction.ASC,
						page.getOrderField());
			} else {
				return new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage(), Sort.Direction.DESC,
						page.getOrderField());
			}
		}
		return new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage());
	}

	/**
	 * 将springDataPage的属性注入page描述
	 * 
	 * @param page
	 * @param springDataPage
	 */
	public static void injectPageProperties(DWZPage page, Page<?> springDataPage) {
		// 暂时只注入总记录数量
		Long totalCount = 0L;
		if (null != springDataPage) {
			totalCount = springDataPage.getTotalElements();
		}
		page.setTotalCount(totalCount);
	}
}
