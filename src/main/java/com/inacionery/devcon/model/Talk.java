
package com.inacionery.devcon.model;

import java.time.LocalTime;

import lombok.Data;

/**
 * @author Inácio Nery
 */
@Data
public class Talk implements Comparable<Talk> {

	private int duration;
	private LocalTime maxStartTime;
	private LocalTime minStartTime;
	private int priority;
	private LocalTime startTime;
	private String title;

	@Override
	public int compareTo(Talk talk) {
		if (priority > talk.priority) {
			return -1;
		}
		else if (priority < talk.priority) {
			return 1;
		}
		else if (duration > talk.duration) {
			return -1;
		}
		else if (duration < talk.duration) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public Talk() {}

	public Talk(
		int duration, LocalTime maxStartTime, LocalTime minStartTime,
		String title
	) {
		this.duration = duration;
		this.maxStartTime = maxStartTime;
		this.minStartTime = minStartTime;
		this.title = title;
	}

}
