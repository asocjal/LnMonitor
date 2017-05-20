package org.dziemian.ln.main;

import static jsweet.dom.Globals.alert;

import jsweet.dom.HTMLCanvasElement;

import static jsweet.dom.Globals.*;
import jsweet.dom.HTMLElement;

public class HelloWorld {
	public static void main(String[] args) {
		alert("V1");
		HTMLElement e = document.getElementById("target");
		e.innerHTML = "Hello world!";
		
		HTMLCanvasElement canvas = (HTMLCanvasElement)document.getElementById("myCanvas");
		canvas.width = 800;
		canvas.height = 600;
	}
}
