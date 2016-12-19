/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qu.common;


import org.jasypt.util.password.StrongPasswordEncryptor;


public class PasswordSecurity {
    
    	private  StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
	
	public  String encryptPassword(String password){

		return passwordEncryptor.encryptPassword(password);
	}
	
	public  boolean isPasswordLegit(String inputPassword, String encryptedPassword){
		if (passwordEncryptor.checkPassword(inputPassword, encryptedPassword)){
			  return true;
			} else {
			  return false;
			}
	}
}
