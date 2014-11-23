package com.example;

import org.androidannotations.annotations.Bean;

/**
 * Created by lcs on 14-11-23.
 */
public class Test {
	@Bean
	MyClass myClass;
	public static void main( String[] args ){

		System.out.println("asdf " + new Test().myClass);
	}
}
