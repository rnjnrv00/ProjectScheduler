package com.event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Logger;


/**
 * 
 * @author Ravi
 * 
 * Driver class
 *
 */

public class MainClass{
	
	public static Logger log = Logger.getLogger("log");
	static ArrayList<Event> events = new ArrayList<>();
	
	void creatEventList(Event e) {
		log.info("adding event into list");
		events.add(e);
	}

	void sortEventList(Comparator<Event> comparator) {
		log.info("start sorting the event list");
		Collections.sort(events,comparator);
	}

	void readFile(String fileName) throws IOException, ParseException {
		
		FileReader inputFile = null;
		BufferedReader bufferReader = null;

		inputFile = new FileReader(fileName);
		bufferReader = new BufferedReader(inputFile);

		String line = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			log.info("start reading the file");
			line = bufferReader.readLine();
			while((line = bufferReader.readLine()) != null) {
				String tokanizer[] = null;
				tokanizer = line.split(",");
				Event e = new Event();
				e.setEventName(tokanizer[0]);
				try {
					Date date2 = dateFormat.parse(tokanizer[1]);
					java.sql.Timestamp timeStampDate2 = new Timestamp(date2.getTime());
					e.setEventTime(timeStampDate2);
				} catch (ParseException parseException) {
					parseException.printStackTrace();
				}
				if(tokanizer.length == 3) {
					e.setEventPriority(Integer.parseInt(tokanizer[2]));
				}
				creatEventList(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(bufferReader != null) {
				bufferReader.close();
			}
			if(inputFile != null) {
				inputFile.close();
			}
		}

	}

	private void findTime(String startTime) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyyHH:mm");
		Date date1;
		try {
			date1 = dateFormat.parse(startTime);
			java.sql.Timestamp timeStampDate1 = new Timestamp(date1.getTime());
			Event event = new Event();
			if(timeStampDate1.before(event.getEventTime())){
				timeStampDate1.setTime(timeStampDate1.getTime()+1*60*1000);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String args[]) throws InterruptedException, ParseException, IOException {
		String starttime=" 03/01/2017 10:01";
		log.info("Starting of driverClass");
		log.info("total argumensts passed" + args.length);
		MainClass main = new MainClass();
		if(args != null && args.length >=1) {
			try {
				main.readFile(args[0]);
				//main.findTime(args[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Comparator<Event> comparator = new ComparatorForEvent();
			main.sortEventList(comparator);
			for(Event x : events) {
				System.out.println("Current time [ " + x.getEventTime() + " ]" + "," + " Event" + "\""
						+ x.getEventName() + "\"" + " Processed");
				Thread.sleep(60000);
			}
		}else {
			log.warning("file name is not passed in arguments");
		}
		
	}
	
	

	
}
