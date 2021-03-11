[13:20] Somogyi Bence
    
package hu.bme.mit.yakindu.analysis.workhere;


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
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;


import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.*;


import org.yakindu.base.types.Event;
import org.yakindu.base.types.Property;
import org.yakindu.sct.model.sgraph.Scope;


public class CodeGenerator {​​​​​
    @Test
    public void test() {​​​​​
        main(new String[0]);
    }​​​​​
    
    public static void main(String[] args) {​​​​​
        ModelManager manager = new ModelManager();
        Model2GML model2gml = new Model2GML();


        EObject root = manager.loadModel("model_input/example.sct");
        Statechart s = (Statechart) root;    
        TreeIterator<EObject> iterator = s.eAllContents();        
        while (iterator.hasNext()) {​​​​​
            EObject content = iterator.next();
            
            if(content instanceof State) {​​​​​
                
                State state= (State) content;
                System.out.println(state.getName());
            }​​​​​
            
            
        }​​​​​
        iterator = s.eAllContents();
        while(iterator.hasNext()) {​​​​​
            EObject content = iterator.next();
            
            if(content instanceof Event) {​​​​​
                
                Event ev= (Event) content;
                System.out.println(ev.getName());
            }​​​​​
        }​​​​​
        
        iterator = s.eAllContents();
        while(iterator.hasNext()) {​​​​​
            EObject content = iterator.next();
            
            if(content instanceof VariableDefinition) {​​​​​
                
                VariableDefinition ev= (VariableDefinition) content;
                System.out.println(ev.getName());
            }​​​​​
        }​​​​​
        
        // Transforming the model into a graph representation
        String content = model2gml.transform(root);
        // and saving it
        manager.saveFile("model_output/graph.gml", content);
    }​​​​​
}​​​​​








