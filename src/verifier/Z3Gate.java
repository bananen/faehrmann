package verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ast.ArrayBoolAccess;
import ast.ArrayIntAccess;
import ast.Term;
import ast.VarBool;
import ast.VarInt;

/**
 * This class defines the interface to a Z3 instance.
 * 
 * @author Fedor Scholz
 * 
 */

public class Z3Gate {

	private ArrayList<String> smtProgram;
	private ArrayList<String> z3Output;

	private Result result;

	private final VariableFinder variableFinder;
	private final ResultTranslator resultTranslator;

	public Z3Gate() {
//		smtProgram = new ArrayList<String>();
//		z3Output = new ArrayList<String>();

		variableFinder = new VariableFinder();
		resultTranslator = new ResultTranslator();
	}

	/**
	 * This method can be used to check the given term with z3. It returns a
	 * more or less human readable answer of z3.
	 */
	public Result callZ3(Term term, int z3TimeOut, String z3Parameter, boolean informationFlow) {
		if (term == null || z3TimeOut == 0 || z3Parameter == null) {
			return null;
		}
		
		
		smtProgram = new ArrayList<String>();
		z3Output = new ArrayList<String>();

		// Write declarations of all found variables and the term into
		// smtProgram
		variableFinder.findVariables(term);
		ArrayList<VarInt> intVariables = variableFinder.getIntVariables();
		ArrayList<VarBool> boolVariables = variableFinder.getBoolVariables();
		ArrayList<ArrayIntAccess> intArrays= variableFinder.getIntArrays();
		ArrayList<ArrayBoolAccess> boolArrays= variableFinder.getBoolArrays();
		for (VarInt current : intVariables) {
			smtProgram.add("(declare-const " + current.getName() + " Int)");
		}
		for (VarBool current : boolVariables) {
			smtProgram.add("(declare-const " + current.getName() + " Bool)");
		}
		for (ArrayIntAccess current : intArrays) {
			smtProgram.add("(declare-const " + current.getName() + " (Array Int Int))");
		}
		for (ArrayBoolAccess current : boolArrays) {
			smtProgram.add("(declare-const " + current.getName() + " (Array Int Bool))");
		}
		smtProgram.add("(assert " + term.toSMTString() + ")");
		smtProgram.add("(check-sat)");
		smtProgram.add("(get-model)");

		// Write smtProgram into a file
		FileWriter writer;
		// Windows
		String settingsPath = System.getenv("APPDATA");
		// Unix
		if (settingsPath == null) {
			settingsPath = System.getProperty("user.home");
		}
		try {
			writer = new FileWriter(settingsPath + File.separatorChar
					+ ".formula.smt2");
			for (String current : smtProgram) {
				writer.write(current + "\n");
			}
			writer.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}

		// Run z3 with the file as a parameter
		try {
			Process p = Runtime.getRuntime().exec(
					z3Parameter + " -T:" + z3TimeOut + " " + settingsPath
							+ File.separatorChar + ".formula.smt2");
			BufferedReader z3Reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line;
			System.out.println("Z3 Output: ");
			while ((line = z3Reader.readLine()) != null) {
				z3Output.add(line);
				System.out.println(line);
			}
			System.out.println("End Z3 Output.\n\n\n");
			z3Reader.close();
			p.waitFor();
		} catch (Exception e) {
			// e.printStackTrace();
			ArrayList<String> list = new ArrayList<String>();
			list.add("Wrong Z3 path.\n");
			return new Result(list);
		}

		result = new Result(z3Output);
		result = resultTranslator.translateResult(result, informationFlow);

		return result;
	}

}