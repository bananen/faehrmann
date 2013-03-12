package verifier;

import java.util.ArrayList;

/**
 * This class provides a method to translate a given result into human language.
 * 
 * @author Manuel Kohnen
 * @author Fedor Scholz
 * 
 */

public class ResultTranslator {

	/**
	 * This method transforms a given result with information right from z3 to a
	 * result with more human readable information.
	 */
	public Result translateResult(Result result, boolean informationFlow) {
		ArrayList<String> oldMessages = result.getMessages();
		ArrayList<String> newMessages = new ArrayList<String>();

		if (oldMessages.get(0).equals("unsat")) {
			String message = "Congratulations, your program was verified successfully";
			if (informationFlow) {
				message = message
						+ " and no illegal information flow was found";
			}
			newMessages.add(message + ".\n");
		} else if (oldMessages.get(0).equals("sat")) {
			if (!informationFlow) {
				newMessages.add("Sorry, your program could not be verified.");
				if (oldMessages.get(1).equals("(model ")
						&& oldMessages.get(2).equals(")")) {
					newMessages
							.add("Your program is never correct, regardless of the values chosen for undefined variables.");
				} else {
					newMessages.add("You can see a counterexample here:\n");
					int i = 0;
					while (i < oldMessages.size()) {
						if (oldMessages.get(i).contains("define-fun")) {
							newMessages.add("\t"
									+ oldMessages.get(i).split(" ")[3]
									+ " = "
									+ oldMessages.get(i + 1)
											.substring(
													4,
													oldMessages.get(i + 1)
															.length() - 1));
							i += 2;
						} else {
							i++;
						}
					}
				}
				newMessages.add("\n");
			} else {
				newMessages.add("Sorry, the program could not be verified and/or illegal information flow was found.");
				newMessages
						.add("Information flowed from the following variables into low variables:\n");
				int i = 0;
				while (i < oldMessages.size()) {
					if (oldMessages.get(i).contains("define-fun")
							&& !oldMessages.get(i).contains("--")) {
						newMessages
								.add("\t" + oldMessages.get(i).split(" ")[3]);
						i += 2;
					} else {
						i++;
					}
				}
				newMessages.add("\n");
			}
		} else {
			newMessages
					.add("Z3 told me something strange. Here you can see its output:\n");
			for (int i = 0; i < oldMessages.size(); i++) {
				newMessages.add("\t" + oldMessages.get(i));
			}
			newMessages.add("\n");
			newMessages
					.add("Keep in mind that the program was negated first and then tested.");
		}

		return new Result(newMessages);
	}

}