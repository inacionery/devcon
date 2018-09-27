
package com.inacionery.devcon.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author Inácio Nery
 */
@Data
public class Day {

	private int day;
	private LocalTime end;
	private LocalTime start;
	private LocalTime current;
	private List<Talk> talks = new ArrayList<>();

	public Day(int day, LocalTime end, LocalTime start) {
		this.day = day;
		this.end = end;
		this.start = start;
		this.current = start;
	}

	public void addTalk(Talk talk) {
		talk.setStartTime(current);

		setCurrent(current.plusMinutes(talk.getDuration()));

		talks.add(talk);
	}

	public void checkMorningCoffeBreak() {
		if (!current.isBefore(morningCoffeBreak.getMinStartTime()) && !current
			.isAfter(morningCoffeBreak.getMaxStartTime())
			&& !talks.contains(morningCoffeBreak)) {

			addTalk(morningCoffeBreak);
		}
	}

	public void checkEveningCoffeBreak() {
		if (!current.isBefore(eveningCoffeBreak.getMinStartTime()) && !current
			.isAfter(eveningCoffeBreak.getMaxStartTime())
			&& !talks.contains(eveningCoffeBreak)) {

			addTalk(eveningCoffeBreak);
		}
	}

	public void checkLunchBreak() {
		if (!current.isBefore(lunchBreak.getMinStartTime()) && !current
			.isAfter(lunchBreak.getMaxStartTime())
			&& !talks.contains(lunchBreak)) {

			addTalk(lunchBreak);
		}
	}

	private final Talk lunchBreak =
		new Talk(90, LocalTime.of(15, 0), LocalTime.of(13, 1), "Lunch Break");
	private final Talk morningCoffeBreak =
		new Talk(30, LocalTime.of(12, 0), LocalTime.of(9, 1), "Coffe Break");
	private final Talk eveningCoffeBreak =
		new Talk(30, LocalTime.of(16, 30), LocalTime.of(14, 31), "Coffe Break");

}
