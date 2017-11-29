package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

public class Email {

	
	private String name;
	private List<String> tags;
	private String type;
	
	public Email(String name) {
		this.name = name;
		tags = new ArrayList<String>();
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}

	public List<String> getTags() {
		return tags;
	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	
}
