package com.fjla.PT.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fjla.PT.config.ApplicationConfig;

public class ValidadorEmail {
	
	public static boolean validteEmail(String Email) {
        // expresion regular
        //String patronCorreo = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; //patron harcodeado
		String patronCorreo = ApplicationConfig.REGEXSTR();  // patron obtenido del archivo application.properties
        Pattern pattern = Pattern.compile(patronCorreo);
        // Crear un objeto Matcher
        Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }
}
