package com.company;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
	// write your code here
        MySyntaxTree tree=new MySyntaxTree();
        ReadTools tools=new ReadTools();
        tree.createSyntax();
        tools.start(args[0]);
    }
}
