package verifier;

import java.util.ArrayList;

import ast.*;
import core.Settings;

/**
 * This is the main class and interface of the verifier module. All other
 * classes in this package will be used by this class to verify a given program.
 * 
 * @author Fedor Scholz
 * 
 */

public class FormulaGenerator {

	private final Settings settings;

	private final LoopToIf loopToIf;
	private final InformationFlowTest informationFlowTest;
	private final WPGenerator wpGenerator;
	private final Z3Gate z3Gate;

	public FormulaGenerator(Settings settings) {
		this.settings = settings;
		this.loopToIf = new LoopToIf(settings.getMaxLoopCount());
		this.informationFlowTest = new InformationFlowTest();
		this.wpGenerator = new WPGenerator(null);
		this.z3Gate = new Z3Gate();
	}

	/**
	 * This method can be used to get a formatted result with information about
	 * the given program.
	 * 
	 * @param informationFlow
	 */
	public Result verify(Program program, boolean informationFlow) {

		PrintAST printast = new PrintAST();

//		System.out.println("Original Program:");
//		printArrayList(printast.print(program));

		Program newProgram = loopToIf.visit(program);
		
//		System.out.println("Program without loops:");
//		printArrayList(printast.print(newProgram));

		if (informationFlow) {
			newProgram = informationFlowTest.visit(newProgram);

//			System.out.println("Program with test for information flow:");
//			printArrayList(printast.print(newProgram));
		}

		Term wp = wpGenerator.getWP(newProgram);

//		System.out.println("Weakest Precondition:");
//		printArrayList(printast.print(wp));

//		System.out.println("\n\n\n");

		return z3Gate.callZ3(wp, settings.getZ3Timeout(), settings.getz3Path(), informationFlow);
	}
	
	private void printArrayList(ArrayList<String> list) {
		for (String string : list) {
			System.out.println(string);
		}
	}

}
