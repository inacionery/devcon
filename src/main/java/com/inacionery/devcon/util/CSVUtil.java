
package com.inacionery.devcon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.inacionery.devcon.model.Day;
import com.inacionery.devcon.model.Talk;

/**
 * @author Inácio Nery
 */
public class CSVUtil {

	public List<Talk> readFile() {
		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource("input.csv");

		try (FileReader fileReader = new FileReader(resource.getPath());
			BufferedReader bufferedReader = new BufferedReader(fileReader);) {

			Stream<String> stream = bufferedReader.lines();

			return stream.skip(
				1
			).map(
				(line) -> {
					String[] parts = line.split(";");

					Talk talk = new Talk();

					talk.setTitle(parts[0]);
					talk.setDuration(Integer.valueOf(parts[1]));
					talk.setPriority(Integer.valueOf(parts[2]));
					talk.setMinStartTime(LocalTime.of(9, 0));
					talk.setMaxStartTime(
						LocalTime.of(19, 0).minusMinutes(
							talk.getDuration())
					);

					return talk;
				}
			).sorted(
			).collect(
				Collectors.toList()
			);

		}
		catch (IOException e) {
			e.printStackTrace();

			return Collections.emptyList();
		}
	}
	
	public void writeFile(List<Day> days) {
		
		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource("output.csv");

		try (FileWriter fileWriter = new FileWriter(resource.getPath());
			PrintWriter printWriter = new PrintWriter(fileWriter);) {

			printWriter.println("day;start;end;title");
			
			for (Day day : days) {
				for (Talk talk : day.getTalks()) {
					StringBuffer sb = new StringBuffer();
					
					sb.append(day.getDay());
					sb.append(";");
					sb.append(talk.getStartTime());
					sb.append(";");
					sb.append(
						talk.getStartTime().plusMinutes(talk.getDuration())
					);
					sb.append(";");
					sb.append(talk.getTitle());
					
					printWriter.println(sb);
				}
			}
			
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
