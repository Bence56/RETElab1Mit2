package hu.bme.mit.yakindu.analysis.workhere;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.base.types.Event;
import org.yakindu.base.types.Property;
import org.yakindu.sct.model.sgraph.Scope;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;
public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static int szam = 0;
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		//Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		int i = 0;
		State elozostate=null;
		ArrayList<State> csapdaallapotok=new ArrayList<State>();
		ArrayList<State> nevnelkuliallapotok = new ArrayList<State>();
		ArrayList<State> mindenallapot = new ArrayList<State>();
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			
			if(content instanceof State) {
				State state = (State) content;
				if(i==0) {
					System.out.print("Kezdőállapot : ");
					i++;
				}
				else {
					System.out.print(elozostate.getName() + " -> ");
				}
				System.out.println(state.getName());
				elozostate = state;
				//i++;
				
				if(state.getOutgoingTransitions().isEmpty()) {
					csapdaallapotok.add(state);
				}
				if(state.getName()=="" || state.getName() == null) {
					nevnelkuliallapotok.add(state);
				}
				mindenallapot.add(state);
			}
		}
		if(csapdaallapotok.size() == 0) {
			System.out.println("Nincs csapda állapot");
		}
		else {
			System.out.println("Csapdaállapotok: ");
			for(int j=0; j<csapdaallapotok.size();j++) {
				System.out.println(csapdaallapotok.get(j).getName());
			}
		}
		System.out.println("Nevnelkuli allapotok szama: "+nevnelkuliallapotok.size());
		for(int j=0;j < nevnelkuliallapotok.size();j++) {
			String nev=nevgenerator();
			boolean jonev=false;
			while(!jonev) {
				int cnt=0;
				for(int k=0;k < nevnelkuliallapotok.size();k++) {
					if(mindenallapot.get(k).getName()==nev) {
					cnt++;	
					}
				}
				if(cnt>0) {
					jonev=false;
				}
				else {
					jonev=true;
				}
			}
			System.out.println("Ezt a nevet javaslom az állapotnak: "+nev);
			//nevnelkuliallapotok.get(j).setName(nev); 					//////Ez itt azért felesleges, mert csak a listában írom át nem a modellben
		}
		
		// Transforming the model into a graph representation*/
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
	public static String nevgenerator() {
		String elotag = "NAMELESSSTATE_";
		int kovetkezoszam=szam;
		szam++;
		String ret =elotag;
		String num=String.valueOf(kovetkezoszam);
		ret+=num;
		return ret;
	}
}
