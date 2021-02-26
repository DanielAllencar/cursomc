package com.alencar.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	
	
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(","); //split é o metodo que quebra um string atraves do valor de parametro tipo ex 1, 2, 3 -> neste casa ele separa os numeros cada um em uma posição do vetor
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		
		//esta linda em lambda faz o mesmo que o que esta no metodo acima
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
	}

}
