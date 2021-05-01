package com.zakir.util.menu;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
		
	private Long id;
	private String name;
	private String url;	
	private String icon;
	private List<Node> subNodes;		

}