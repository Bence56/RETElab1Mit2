package hu.bme.mit.yakindu.analysis.workhere;

 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.lang.model.element.VariableElement;

 

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.stext.stext.impl.VariableDefinitionImpl;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.VariableDefinition;
import org.yakindu.sct.model.stext.stext.impl.EventDefinitionImpl;
import org.yakindu.sct.model.sgraph.Statechart;
import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

 

import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.*;

 

import org.yakindu.base.types.Event;
import org.yakindu.base.types.Property;
import org.yakindu.sct.model.sgraph.Scope;

 

public class CodeGenerator {
    @Test
    public void test() throws IOException {
        main(new String[0]);
    }
    
    public static void main(String[] args) throws IOException {
        ModelManager manager = new ModelManager();
        Model2GML model2gml = new Model2GML();

 

        EObject root = manager.loadModel("model_input/example.sct");
        Statechart s = (Statechart) root;    
        TreeIterator<EObject> iterator = s.eAllContents();        
        /*System.out.println("Állapotok:");
        while (iterator.hasNext()) {
            EObject content = iterator.next();
            
            if(content instanceof State) {
                
                State state= (State) content;
                System.out.println("\t"+state.getName());
            }
            
            
        }
        System.out.println("Események: ");
        iterator = s.eAllContents();
        while(iterator.hasNext()) {
            EObject content = iterator.next();
            
            if(content instanceof Event) {
                
                Event ev= (Event) content;
                System.out.println("\t"+ev.getName());
            }
        }
        System.out.println("Változók: ");
        iterator = s.eAllContents();
        while(iterator.hasNext()) {
            EObject content = iterator.next();
            
            if(content instanceof VariableDefinition) {
                
                VariableDefinition ev= (VariableDefinition) content;
                System.out.println("\t"+ev.getName());
            }
        }*/
	    Writer writer =new BufferedWriter(new FileWriter("GeneratedCode.java"));       
        
        //System.out.println(
        writer.write(""
        		+ "import java.io.BufferedReader;\n" + 
        		"import java.io.IOException;\n" + 
        		"import java.io.InputStreamReader;\n" + 
        		"\n" + 
        		"import javax.lang.model.element.VariableElement;\n" + 
        		"\n" + 
        		" \n" + 
        		"\n" + 
        		"import org.eclipse.emf.common.util.TreeIterator;\n" + 
        		"import org.eclipse.emf.ecore.EObject;\n" + 
        		"import org.junit.Test;\n" + 
        		"import org.yakindu.sct.model.stext.stext.impl.VariableDefinitionImpl;\n" + 
        		"import org.yakindu.sct.model.stext.stext.EventDefinition;\n" + 
        		"import org.yakindu.sct.model.stext.stext.VariableDefinition;\n" + 
        		"import org.yakindu.sct.model.stext.stext.impl.EventDefinitionImpl;\n" + 
        		"import org.yakindu.sct.model.sgraph.Statechart;\n" + 
        		"import hu.bme.mit.model2gml.Model2GML;\n" + 
        		"import hu.bme.mit.yakindu.analysis.RuntimeService;\n" + 
        		"import hu.bme.mit.yakindu.analysis.TimerService;\n" + 
        		"import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;\n" + 
        		"import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;\n" + 
        		"import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;\n" + 
        		"\n" + 
        		" \n" + 
        		"\n" + 
        		"import org.yakindu.sct.model.sgraph.State;\n" + 
        		"import org.yakindu.sct.model.*;\n" + 
        		"\n" + 
        		" \n" + 
        		"\n" + 
        		"import org.yakindu.base.types.Event;\n" + 
        		"import org.yakindu.base.types.Property;\n" + 
        		"import org.yakindu.sct.model.sgraph.Scope;\n" + 
        		"");
        //System.out.println(
        		writer.write("public class GeneratedCode {\n");
        		writer.write("public static void main(String[] args) throws IOException {\n" + 
        		"		// TODO Auto-generated method stub\n" + 
        		"		ExampleStatemachine stm = new ExampleStatemachine();\n" + 
        		"		stm.setTimer(new TimerService());\n" + 
        		"		RuntimeService.getInstance().registerStatemachine(stm, 200);\n" + 
        		"		stm.init();\n" + 
        		"		stm.enter();\n" + 
        		"		stm.runCycle();\n" + 
        		"		boolean exit=false;\n" + 
        		"		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));\n" + 
        		"		while(exit == false) {\n" + 
        		"			System.out.print(\"Write a transition: \");\n" + 
        		"			\n" + 
        		"			String line = reader.readLine();\n" + 
        		"			\n" + 
        		"			exit = transition(line,stm);\n" + 
        		"			if(exit==false)\n" + 
        		"				exit = isEnoughTimeLeft(stm);\n" + 
        		"		}\n" + 
        		"		\n" + 
        		"		System.out.println(\"End of game!\");\n" + 
        		"		System.exit(0);\n" + 
        		"	}\n" + 
        		"	\n" + 
        		"	private static boolean transition(String line,ExampleStatemachine stm) {\n");
        
			        iterator = s.eAllContents();
        					int i=0;
			        while(iterator.hasNext()) {
			            EObject content = iterator.next();
			            
			            if(content instanceof Event) {
			                
			                Event ev= (Event) content;
			                String str=ev.getName().substring(0,1).toUpperCase()+ev.getName().substring(1, ev.getName().length());
			                if(i==0) {
			                	
			                	//System.out.println(
			                			writer.write(""
			                			+ "		if(line.equals(" + ev.getName() +")) {\n" + 
			        					"			stm.raise"+str+"();\n" + 
			        					"			stm.runCycle();\n" + 
			        					"			print(stm);\n" + 
			        					"		}\n"
			                	);
			                }
			                else {
			                	
			                	//System.out.println(
			                			writer.write(""
			                			+ "		else if(line.equals("+ev.getName() +")) {\n" + 
			        					"			stm.raise"+str+"();\n" + 
			        					"			stm.runCycle();\n" + 
			        					"			print(stm);\n" + 
			        					"		}\n"
			                	);
			                }
			                i++;
			            }
			        }
					//System.out.println(
							writer.write("		else {\n" + 
					"			System.out.println(\"No transition like this\");\n" + 
					"		}\n" + 
					"		return false;\n" + 
					"		\n" + 
					"	} \n"+ 
        		"	public static boolean isEnoughTimeLeft(ExampleStatemachine stm) {\n" + 
        		"		if(stm.getSCInterface().getBlackTime()<=0 || stm.getSCInterface().getWhiteTime()<=0) {\n" + 
        		"			return true;\n" + 
        		"		}\n" + 
        		"		else {\n" + 
        		"			return false;\n" + 
        		"		}\n" + 
        		"	}\n" + 
        		"\n" + 
        		"	public static void print(IExampleStatemachine stm) {\n" + 
        		"		\n"); 
        		iterator = s.eAllContents();
                while(iterator.hasNext()) {
                    EObject content = iterator.next();
                    
                    if(content instanceof VariableDefinition) {
                        
                        VariableDefinition var= (VariableDefinition) content;
                        String st=var.getName().substring(0,1).toUpperCase()+var.getName().substring(1, var.getName().length());
                        //System.out.println(
                        		writer.write("		System.out.println(\""+var.getName().substring(0,1).toUpperCase()+ " = \" + s.getSCInterface().get" +st+"());\n" );}
                }
              //System.out.println(
        		writer.write("	}\n");
        		//System.out.println(
        		writer.write("}");
        writer.close();
        // Transforming the model into a graph representation
        String content = model2gml.transform(root);
        // and saving it
        manager.saveFile("model_output/graph.gml", content);
    }
}