package org.codingwater.service;

import java.io.IOException;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * Created by water on 4/14/16.
 */
public class LagouSpiderServiceTest {

	@Test
	public void testRun() {
		System.out.println("let's start");
	}

	// 拉勾
	@Test
	public void testLagouWithJsoup() {

		try {
			// fix UnsupportedMimeTypeException by using ignoreContentType(true)
			Document doc = Jsoup
					.connect("http://www.lagou.com/jobs/positionAjax" + ".json?city=上海&first=false&pn=2&kd=Java")
					.ignoreContentType(true).get();
			System.out.println(doc);
			System.out.println(doc.body());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 内推,通过解析html页面获取数据
	@Test
	public void testNeituiWithJsoup() {
		// http://www.neitui.me/?name=neitui&handle=lists&kcity=%E5%85%A8%E5%9B%BD&keyword=Java
		String neiTuiUrl = "http://www.neitui" + ".me/?name=neitui&handle=lists&keyword=Java&kcity=全国&page=1";
		try {
			Document doc = Jsoup.connect(neiTuiUrl).ignoreContentType(true).timeout(10 * 1000).get();
			// System.out.println(doc);
			System.out.println("________________________________________________");
			// System.out.println(doc.getElementById("joblist"));
			// System.out.println(doc.select(".commentjobs")); //通过css
			// 类选择器,选择最具有唯一性标志的css类,来解析joblist.

			// Elements jobList = doc.select(".commentjobs");
			// System.out.println(jobList.size());

			Element job = doc.getElementsByAttributeValue("class", "jobinfo brjob clearfix").get(4);

			String createTime = job.getElementsByAttributeValue("class", "createtime").text();
			System.out.println("createTime : " + createTime);

			Element elementLeft = job.getElementsByAttributeValue("class", "jobnote-l").get(0);
			// System.out.println(elements1.text());
			String positionId = elementLeft.getElementsByTag("a").get(0).attr("href").split("/")[2];
			System.out.println("positionId: " + positionId);
			String positionName = elementLeft.getElementsByAttributeValue("class", "padding-r10").get(0).text();
			System.out.println("positionName:" + positionName);
			String city = elementLeft.getElementsByAttributeValue("class", "padding-r10").get(1).text();
			System.out.println("city:" + city);
			String monthSalary = elementLeft.getElementsByAttributeValue("class", "padding-r10").get(2).text();
			System.out.println("monthSalary:" + monthSalary);
			String workYear = elementLeft.getElementsByAttributeValue("class", "padding-r10").get(3).text();
			System.out.println("workYear:" + workYear);
			Element elementRight = job.getElementsByAttributeValue("class", "jobnote-r").get(0);
			String companyName = elementRight.getElementsByAttributeValue("class", "padding-r10").get(0).text();
			System.out.println("companyName:" + companyName);

			// 通过attribute的K-V查找模式.

			System.out.println("________________________________________________");
			// System.out.println(doc.getElementById("J_Screen_Workage"));

			// System.out.println("________________________________________________");
			// System.out.println(doc.select("content commentjobs brjobs topjobs"));
			// System.out.println("________________________________________________");
			// System.out.println(doc.getElementsByClass("content commentjobs brjobs
			// topjobs"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testStringToTimeStamp() {
		String time = "2016-04-28 13:00:00";
		System.out.println(Timestamp.valueOf(time).getTime());

	}

}