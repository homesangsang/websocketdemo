package websocket.dao.util;

public class ConvertToJSONString<Object> {
	public String convertToJSONString(Object s1,Object s2){
		
		return "{ \"student1\" : "+s1.toString()+", \"student2\" :"+s2.toString()+" }";
	}
	
}
