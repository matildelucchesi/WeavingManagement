/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matilde
 */
public class DBUtility {
    public static String stringBuilder(List<Integer> list){
        StringBuilder stringBuilder = new StringBuilder("[");
    
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i));
                if (i < list.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
    
            stringBuilder.append("]");
            
            return stringBuilder.toString();
    }
    
    
    public static List<Integer> convertStringToInteger(String string){
        List<Integer> integerList = new ArrayList<>();
        if(!string.equals("[]")){
            string = string.substring(1, string.length() - 1);
            String[] value = string.split(", ");
            for(String str : value){
                integerList.add(Integer.parseInt(str));
            }
        }
        return integerList;        
    }
}
