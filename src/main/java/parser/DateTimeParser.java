package parser;

import java.time.format.DateTimeFormatter;

public class DateTimeParser {
	public DateTimeFormatter dateKey;
	public DateTimeFormatter timeKey;
	
	public DateTimeParser() {
		this.dateKey = DateTimeFormatter.ofPattern("[dd/MM/yyyy][d/M/yyyy][dd/MM/yy][d/M/yy]"
				+ "[yyyy/MM/dd][yyyy-MM-dd][yyyy-M-d][dd-MM-yyyy][d-M-yyyy][dd-MM-yy][d-M-yy]"
				+ "[dd.MM.yy][d.M.yy][dd.MM.yyyy][d.M.yyyy][dd-MMM-yyyy][d-MMM-yyyy][d-MMM-yy]");
		this.timeKey = DateTimeFormatter.ofPattern("[hh:mma][hh:mm:ssa]"
				+ "[HH:mm][HHmm][HH:mm:ss][hha][ha]");
	}
	
}
