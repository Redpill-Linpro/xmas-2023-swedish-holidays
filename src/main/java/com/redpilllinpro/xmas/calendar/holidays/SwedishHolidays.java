/**
 * 
 */
package com.redpilllinpro.xmas.calendar.holidays;

import java.io.StringWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author henka
 *
 */
public class SwedishHolidays {
	private final int year;
	private final List<Holiday> holidays;
	private final Map<LocalDate, String> holidayMap;
	private static final Map<Integer, SwedishHolidays> instances = new HashMap<>();

	public static SwedishHolidays getInstance(int year) {
		SwedishHolidays sh = instances.get(year);
		if (sh == null) {
			sh = new SwedishHolidays(year);
			instances.put(year, sh);
		}
		return sh;
	}
	
	private SwedishHolidays(int year) {
		this.year = year;		
		 
		LocalDate easter = getEasterDay();		
		// midsommardagen den lördag som infaller under tiden den 20--26 juni
		LocalDate msd = getDayOfWeekDate(Month.JUNE, 20, DayOfWeek.SATURDAY);
		
		// alla helgons dag den lördag som infaller under tiden den 31
		// oktober--den 6 november
		LocalDate ah = getDayOfWeekDate(Month.OCTOBER, 31, DayOfWeek.SATURDAY);
		
		holidays = new ArrayList<Holiday>();
		
		holidays.add(new Holiday(year, Month.JANUARY, 1, "Nyårsdagen"));
		holidays.add(new Holiday(year, Month.JANUARY, 6, "Trettondagen"));
		holidays.add(new Holiday(year, Month.MAY, 1, "Första maj"));
		holidays.add(new Holiday(easter.minusDays(2), "Långfredagen"));
		holidays.add(new Holiday(easter, "Påskdagen"));
		holidays.add(new Holiday(easter.plusDays(1), "Annandag påsk"));
		holidays.add(new Holiday(easter.plusDays(5*7+4), "Kristi himmelsfärdsdag"));
		holidays.add(new Holiday(easter.plusDays(7*7), "Pingstdagen"));
		holidays.add(new Holiday(year, Month.JUNE, 6, "Sveriges nationaldag och svenska flaggans dag"));
		holidays.add(new Holiday(msd.minusDays(1), "Midsommarafton"));
		holidays.add(new Holiday(msd, "Midsommardagen"));
		holidays.add(new Holiday(ah, "Alla helgons dag"));
		holidays.add(new Holiday(year, Month.DECEMBER, 24, "Julafton"));
		holidays.add(new Holiday(year, Month.DECEMBER, 25, "Juldagen"));
		holidays.add(new Holiday(year, Month.DECEMBER, 26, "Annandag jul"));
		holidays.add(new Holiday(year, Month.DECEMBER, 31, "Nyårsafton"));
		
		holidayMap = new LinkedHashMap<>();
		
		Collections.sort(holidays);
		for (Holiday h : holidays) {
			holidayMap.put(h.getDate(), h.getDescription());
		}
		
	}
	
	private LocalDate getDayOfWeekDate(Month month, int day, DayOfWeek dow) {
		LocalDate date = LocalDate.of(year, month, day);
		while (date.getDayOfWeek() != dow) {
			date = date.plusDays(1);
		}
		return date;
		
	}
	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/*
	 * Gauss Påskformel
	 * https://www.eit.lth.se/fileadmin/eit/courses/edi021/DP_Gauss.htm
	 */
	protected LocalDate getEasterDay() {
		// Constants for 2000-2099
		int M = 24; 
		int N = 5;
		
		int a = year % 19;
		int b = year % 4;
		int c = year % 7;
		int d = ((19*a) + M) % 30;
		int e = ((2*b) + (4*c) + (6*d) + N) % 7;

		int f = 22 + d + e;
		
		if (f <= 31) {
			return LocalDate.of(year, Month.MARCH, f);
		}
		else {
			int g = f-31;
			if (g == 26 || (g == 25 && d == 28 && e == 6 && a > 10)) {
				g -= 7;
			}
			return LocalDate.of(year, Month.APRIL, g);
		}
	}



	/**
	 * @return the holidayMap
	 */
	public Map<LocalDate, String> getHolidayMap() {
		return holidayMap;
	}

	public boolean isHoliday(LocalDate date) {
		return holidayMap.containsKey(date);
	}

}
