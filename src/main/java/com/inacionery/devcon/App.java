/**
 * 
 */

package com.inacionery.devcon;

import java.util.List;

import com.inacionery.devcon.model.Day;
import com.inacionery.devcon.model.Talk;
import com.inacionery.devcon.service.CalculateService;
import com.inacionery.devcon.util.CSVUtil;

/**
 * @author Inácio Nery
 */
public class App {

	public static void main(String[] args) {

		List<Talk> talks = csvUtil.readFile();

		List<Day> days = calculateService.calculate(talks);

		csvUtil.writeFile(days);
	}

	private static CSVUtil csvUtil = new CSVUtil();
	private static CalculateService calculateService = new CalculateService();
}
