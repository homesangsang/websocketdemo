package websocket.echo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.catalina.ha.tcp.SendMessageData;

import websocket.dao.bo.StudentBO;
import websocket.dao.impl.Student;
import websocket.dao.util.ConvertToJSONString;
import websocket.db.DBOption;

@ServerEndpoint("/database")
public class EchoAnnotation {
	
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<EchoAnnotation> webSocketSet = new CopyOnWriteArraySet<EchoAnnotation>();
    
    // 与某个客户端的session连接，通过他来发送数据
    private Session session;
    
    // 

    // 数据库操作对象的创建
    private static DBOption database = new DBOption();
	@OnMessage
	public void onMessage(String message, Session session) 
	    throws IOException, InterruptedException, SQLException {
	  
	    if("next".equalsIgnoreCase(message)){
	    	System.out.println("admin用户发送了next指令！！！");
	    	Student student1 = database.next();
	    	database.setIsSelect(student1);
	    	Student student2 = database.next();
	    	for(EchoAnnotation item: webSocketSet){
	    		try {
	    			ConvertToJSONString<Student> convert = new ConvertToJSONString<Student>();
	    			String students = convert.convertToJSONString(student1, student2);
	    			item.sendMessage(students);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    			continue;
	    		}
	    	}
	    }
	    
    
    
	}
  
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		webSocketSet.add(this);
		addOlineCount();
		System.out.println("在线人数增加，当前人数为：" + getOlineCount());
	}
	
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);
		subOlineCOune();
		System.out.println("在线人数减少，当前人数为："+getOlineCount());
	}
	
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("发生错误");
		error.printStackTrace();
	}
	
	/*
	 * 以下函数用来向所有的客户端推送消息
	 */
	public void sendMessage(String message) throws IOException{
		this.session.getBasicRemote().sendText(message);
	}

	/*
	 * 
	 * 以下三个方法用来统计当前连接服务器的用户数量
	 */
	public static synchronized int  getOlineCount(){
		return EchoAnnotation.onlineCount++;
	}
	public static synchronized void addOlineCount(){
		EchoAnnotation.onlineCount++;
	}
	public static synchronized void subOlineCOune(){
		EchoAnnotation.onlineCount--;
	}
}