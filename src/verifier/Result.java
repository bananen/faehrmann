package verifier;

import java.util.ArrayList;

/**
 * This class defines a result which will be created out of the answer from Z3.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class Result {

	private final ArrayList<String> messages;

	public Result(ArrayList<String> messages) {
		this.messages = messages;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}
	
	public boolean equals(Object obj) {
		Result result = (Result) obj;
		return messages.equals(result.getMessages());
	}

}