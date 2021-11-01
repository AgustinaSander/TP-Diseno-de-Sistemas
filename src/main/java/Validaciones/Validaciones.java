
package Validaciones;

import java.util.regex.*;

public class Validaciones {
    
    public static boolean verificarTelefono(String tel){
        Pattern pat = Pattern.compile("^\\+\\d{2}(9)?\\d{10}$");
        Matcher mat = pat.matcher(tel);
        
        if(mat.matches()){
            return true;
        }
        return false;
    }
    
    public static  boolean esNumero(String num){
        try{
            Integer.parseInt(num);
            return true;
        }
        catch(NumberFormatException excepcion){
            return false;
        }
    }
    
    public static boolean verificarCUIT(String cuit){
        //Eliminamos todos los caracteres que no son números
        cuit = cuit.replaceAll("[^\\d]", "");
        
        //Controlamos si son 11 números los que quedaron, si no es el caso, ya devuelve falso
        if (cuit.length() != 11){
            if(cuit.length() == 0){
                return true;
            }
            return false;
        }
        
        //Convertimos la cadena que quedó en una matriz de caracteres
        String[] cuitArray = cuit.split("");
        //Inicializamos una matriz por la cual se multiplicarán cada uno de los dígitos
        Integer[] serie = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        //Creamos una variable auxiliar donde guardaremos los resultados del calculo del número validador
        Integer aux = 0;
        //Recorremos las matrices de forma simultanea, sumando los productos de la serie por el número en la misma posición
        for (int i=0; i<10; i++){
            aux += Integer.valueOf(cuitArray[i]) * serie[i];
        }
        //Hacemos como se especifica: 11 menos el resto de la división de la suma de productos anterior por 11
        aux = 11 - (aux % 11);
        //Si el resultado anterior es 11 el código es 0
        if (aux == 11){
            aux = 0;
        //o si el resultado anterior es 10 el código es 9
        } else if (aux == 10){
            aux = 9;
        }
        //Devuelve verdadero si son iguales, falso si no lo son
        if(Integer.parseInt(cuitArray[10]) == aux){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean verificarEmail(String email){
        if(email.length() == 0){
                return true;
        }
        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@([_A-Za-z0-9-\\\\+]+)+(.(\\w){2,3}){1,4}$");
        Matcher mat = pat.matcher(email);
        
        if(mat.matches()){
            return true;
        }
        return false;
    }
}
