package com.estgames.study.chapter03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AroundPattern {

	public static String processFile1() throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
			return br.readLine();
		}
	} // �ѹ��� �� ���� �аų� �ڵλ��Ǵ� �ܾ ��ȯ�Ϸ���? -- ������ �Ķ����ȭ!
	
	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
			return p.process(br);
		}
	}
	
	public static void main(String[] args) throws IOException{
		String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
		// BufferedReader -> String -- IOException�� ���� �� �ִ� �ñ״���
		
		String oneLine = processFile((BufferedReader br) -> br.readLine());
		String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
	}
	
}

@FunctionalInterface
interface BufferedReaderProcessor {
	String process(BufferedReader b) throws IOException;
}