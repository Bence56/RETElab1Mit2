package hu.bme.mit.yakindu.analysis.workhere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;

public class ExcerciseToWriteAnApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ExampleStatemachine stm = new ExampleStatemachine();
		stm.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(stm, 200);
		stm.init();
		stm.enter();
		stm.runCycle();
		boolean exit=false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(exit == false) {
			System.out.print("Write a transition: ");
			
			String line = reader.readLine();
			
			exit = transition(line,stm);
			if(exit==false)
				exit = isEnoughTimeLeft(stm);
		}
		
		System.out.println("End of game!");
		System.exit(0);
	}
	
	private static boolean transition(String line,ExampleStatemachine stm) {
		if(line.equals("start")) {
			stm.raiseStart();
			stm.runCycle();
			print(stm);
		}
		else if(line.equals("black")) {
			stm.raiseBlack();
			stm.runCycle();
			print(stm);
		}
		else if(line.equals("white")) {
			stm.raiseWhite();
			stm.runCycle();
			print(stm);
		}
		else if(line.equals("exit")) {
			return true;
		}
		else {
			System.out.println("No transition like this");
		}
		return false;
		
	}
	public static boolean isEnoughTimeLeft(ExampleStatemachine stm) {
		if(stm.getSCInterface().getBlackTime()<=0 || stm.getSCInterface().getWhiteTime()<=0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void print(IExampleStatemachine stm) {
		
		System.out.println("\nW = " + stm.getSCInterface().getWhiteTime());
		System.out.println("B = " + stm.getSCInterface().getBlackTime());
	}

}
