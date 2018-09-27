
package com.inacionery.devcon.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.inacionery.devcon.model.Day;
import com.inacionery.devcon.model.Talk;

public class CalculateService {

	public List<Day> calculate(List<Talk> talks) {

		List<Day> days = new ArrayList<>();

		days.add(new Day(1, LocalTime.of(19, 0), LocalTime.of(9, 0)));
		days.add(new Day(2, LocalTime.of(19, 0), LocalTime.of(9, 0)));

		for (Talk talk : talks) {
			Day day = chooseDay(talk, days);
			if (day != null) {
				day.addTalk(talk);
			}
			else {
				System.out.println(talk);
			}

		}
		return days;
	}

	private Day chooseDay(Talk talk, List<Day> days) {
		for (Day day : days) {
			day.checkMorningCoffeBreak();
			day.checkLunchBreak();
			day.checkEveningCoffeBreak();
			if (!day.getCurrent().isBefore(talk.getMinStartTime())
				&& !day.getCurrent().isAfter(talk.getMaxStartTime())) {

				return day;
			}
		}
		return null;
	}

}
