package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import tp6.analysis.Analysis;
import tp6.node.AAssignment;
import tp6.node.ABinexpression;
import tp6.node.ABlock;
import tp6.node.AConditional;
import tp6.node.ADiviserOperatorarith;
import tp6.node.AEqualOperatorlogique;
import tp6.node.AInfOperatorlogique;
import tp6.node.AIntegervalue;
import tp6.node.AMinusOperatorarith;
import tp6.node.AModuloOperatorarith;
import tp6.node.AMultiplierOperatorarith;
import tp6.node.APlusOperatorarith;
import tp6.node.APrintS;
import tp6.node.AReadS;
import tp6.node.ASupOperatorlogique;
import tp6.node.AVariableref;
import tp6.node.AWhileS;
import tp6.node.PStatement;


public class PrettyPrinterInterpreter extends AbstractInterpreter implements Analysis {
	
	int nb_indent = 0;
	String ident = "\t";
	
	public String repeat(String s, int i) {
		String sum = "";
		for (int j = 1; j < i; j++) {
			sum = sum + s;
		}
		
		return sum;
	}
	
	public void print(String s) {

		System.out.print(repeat(ident, nb_indent) + s);
	}
	
	public void println(String s) {
		System.out.println(repeat(ident, nb_indent) + s);
	}

	
	@Override
	public void caseAAssignment(AAssignment node) {
		node.getVar().apply(this);
		System.out.print(" = ");
		node.getRhs().apply(this);		
	}



	@Override
	public void caseABinexpression(ABinexpression node) {
		node.getLExp().apply(this);
		node.getOperator().apply(this);
		node.getRExp().apply(this);
		
	}


	@Override
	public void caseABlock(ABlock node) {
		println("{");
		nb_indent++;
		for (PStatement st :node.getStatement()) {
			st.apply(this);
		}
		nb_indent--;
		println("\n}");
	}


	

	@Override
	public void caseAConditional(AConditional node) {
		print("\nif (");
		node.getCond().apply(this);
		System.out.print(") : ");
		node.getThenpart().apply(this);
		
		if(node.getElse() != null) {
			print("else : ");
			node.getElsepart().apply(this);
		}
	
	}


	@Override
	public void caseAEqualOperatorlogique(AEqualOperatorlogique node) {
		System.out.print(" == ");
	}



	@Override
	public void caseAInfOperatorlogique(AInfOperatorlogique node) {
		System.out.print(" < ");
	
	}

	@Override
	public void caseAIntegervalue(AIntegervalue node) {
		System.out.print(node.getNumber());
	
	}



	@Override
	public void caseAMinusOperatorarith(AMinusOperatorarith node) {
		System.out.print(" - ");
	
	}

	@Override
	public void caseAMultiplierOperatorarith(AMultiplierOperatorarith node) {
		System.out.print(" * ");
	}



	@Override
	public void caseAPlusOperatorarith(APlusOperatorarith node) {
		System.out.print(" + ");
		
	}

	@Override
	public void caseAPrintS(APrintS node) {
		print("print ");
		node.getValue().apply(this);
	}


	@Override
	public void caseAReadS(AReadS node) {

	
	}



	@Override
	public void caseASupOperatorlogique(ASupOperatorlogique node) {
		System.out.print(" > ");
	
	}

	

	@Override
	public void caseAVariableref(AVariableref node) {
		System.out.print(node.getIdentifier().getText());

	
	}


	@Override
	public void caseAWhileS(AWhileS node) {
		print("\nwhile (");
		node.getCond().apply(this);
		System.out.print(") : ");
		node.getBody().apply(this);
	}


	@Override
	public void caseADiviserOperatorarith(ADiviserOperatorarith node) {
		System.out.print(" / ");
	}

	@Override
	public void caseAModuloOperatorarith(AModuloOperatorarith node) {
		System.out.print(" % ");
	}



}
