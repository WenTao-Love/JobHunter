package org.codingwater.dao;

import java.util.List;

import org.codingwater.BaseTest;
import org.codingwater.model.BaseJobInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by water on 4/24/16.
 */
public class BaseJobInfoDAOTest extends BaseTest {

	@Autowired
	private BaseJobInfoDAO baseJobInfoDAO;

	@Test
	public void testSavePosition() throws Exception {
		BaseJobInfo baseJobInfo = new BaseJobInfo();
		baseJobInfo.setPositionId("L123");

		// System.out.println(baseJobInfoDAO.savePosition(baseJobInfo));

	}

	@Test
	public void testFindById() {
		StringBuilder sb = new StringBuilder();
		sb.append("select position_id from WHERE position_id = ?");
		String positionId = "L123";
		Assert.assertNotNull(baseJobInfoDAO.execute(sb.toString(), positionId));
		positionId = "L1";
		Assert.assertNotNull(baseJobInfoDAO.execute(sb.toString(), positionId));

	}

	@Test
	public void testQueryWithCondition() {
		String city = "上海";
		String keyword = "Java";
		String workYear = "1-3年";
		 StringBuilder sb = new StringBuilder();
		    sb.append("select * from t_position where city=? and work_year=? and position_name like ?");
		    List<BaseJobInfo> baseJobInfoList =
		        baseJobInfoDAO.execute(sb.toString(), city,workYear,keyword);

		System.out.println(baseJobInfoList.size());
		Assert.assertNotEquals(0, baseJobInfoList.size());

	}
}