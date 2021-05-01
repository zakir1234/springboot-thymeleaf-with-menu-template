package com.zakir.util.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/menu")
public class MenuController {
	

	@GetMapping("/list")
	public String loadIndex() {
		
		return "/index";
	}
	
	
	@GetMapping("/load")
	@ResponseBody
    public List<Node> nodes() {
		
		List<Node> nodes =  getSampleNodeList();
	
    	return nodes;
    }

    private List<Node> getSampleNodeList() {
        List<Node> nodes = new ArrayList<>();
        
       Node module1 = new Node();
       module1.setId(1L);
       module1.setName("Module 5");
       module1.setSubNodes(getNodes(2, 1));
       
       
       for(Node nd : module1.getSubNodes()) {
    	   nd.setName("Submodule");
    	   nd.setSubNodes(getNodes(3, 2));
       }
       
       nodes.add(module1);
       
  
        return nodes;
    }
    
    
    private List<Node> getNodes(int numOfChildren, int childLevel){
    	
    	List<Node> nodes = new ArrayList<>();
    	
    	for(int i = 0; i< numOfChildren ; i++) {
    		Node node = new Node();
    		node.setId(Long.valueOf(i));
    		
    		String name = "";
    		
    		if(childLevel == 1) {
    			name="Submodule";
    		}else if (childLevel == 2) {
    			name = "Item";
    		}
    		
    		node.setName(name);
    		
    		nodes.add(node);
    		
    	}
    	
    	return nodes;
    	
    }


}
