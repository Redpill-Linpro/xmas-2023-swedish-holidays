/**
 * 
 */
package com.redpilllinpro.xmas.calendar.holidays;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author henka
 *
 */
public class Holiday implements Comparable<Holiday> {
	private final LocalDate date;
	private final String description;
	
	private final static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd (EEEE)", new Locale("sv_SE"));

	public Holiday(LocalDate date, String description) {
		this.date = date;
		this.description = description;		
	}
	
	public Holiday(int year, Month month, int day, String description) {
		this(LocalDate.of(year, month, day), description);
	}
	
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Holiday) {
			return ((Holiday)obj).getDate().equals(getDate());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder(df.format(getDate())).append(" ").append(getDescription()).toString();
	}

	@Override
	public int compareTo(Holiday h) {
		// TODO Auto-generated method stub
		return date.compareTo(h.getDate());
	}
	

	
	
	
}
