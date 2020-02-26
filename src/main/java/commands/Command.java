package commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import common.exceptions.DukeException;

public class Command {
	private final String commandType;
	private final Optional<String> description;
	private final Optional<String> check;
	private final Optional<LocalDate> date;
	private final Optional<LocalTime> time;
	private final Optional<DukeException> e;
	
	public Command(String commandType) {
		this.commandType = commandType;
		this.description = Optional.empty();
		this.check = Optional.empty();
		this.date = Optional.empty();
		this.time = Optional.empty();
		this.e = Optional.empty();
	}
	
	public Command(String commandType, Optional<String> description) {
		this.commandType = commandType;
		this.description = description;
		this.check = Optional.empty();
		this.date = Optional.empty();
		this.time = Optional.empty();
		this.e = Optional.empty();
	}
	
	public Command(String commandType, Optional<String> description, Optional<String> check, 
			Optional<LocalDate> date, Optional<LocalTime> time) {
		this.commandType = commandType;
		this.description = description;
		this.check = check;
		this.date = date;
		this.time = time;
		this.e = Optional.empty();
	}
	
	public Command(DukeException e) {
		this.commandType = "exception";
		this.description = Optional.empty();
		this.check = Optional.empty();
		this.date = Optional.empty();
		this.time = Optional.empty();
		this.e = Optional.of(e);
	}
	
	public String getCommandType() {
		return this.commandType;
	}

	public Optional<String> getDescription() {
		return this.description;
	}
	
	public Optional<String> getCheck() {
		return this.check;
	}

	public Optional<LocalDate> getDate() {
		return this.date;
	}

	public Optional<LocalTime> getTime() {
		return this.time;
	}
	
	public DukeException getException() {
		return this.e.get();
	}

}
