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
	
	//��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
    private static int onlineCount = 0;

    //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
    private static CopyOnWriteArraySet<EchoAnnotation> webSocketSet = new CopyOnWriteArraySet<EchoAnnotation>();
    
    // ��ĳ���ͻ��˵�session���ӣ�ͨ��������������
    private Session session;
    
    // 

    // ���ݿ��������Ĵ���
    private static DBOption database = new DBOption();
	@OnMessage
	public void onMessage(String message, Session session) 
	    throws IOException, InterruptedException, SQLException {
	  
	    if("next".equalsIgnoreCase(message)){
	    	System.out.println("admin�û�������nextָ�����");
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
		System.out.println("�����������ӣ���ǰ����Ϊ��" + getOlineCount());
	}
	
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);
		subOlineCOune();
		System.out.println("�����������٣���ǰ����Ϊ��"+getOlineCount());
	}
	
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("��������");
		error.printStackTrace();
	}
	
	/*
	 * ���º������������еĿͻ���������Ϣ
	 */
	public void sendMessage(String message) throws IOException{
		this.session.getBasicRemote().sendText(message);
	}

	/*
	 * 
	 * ����������������ͳ�Ƶ�ǰ���ӷ��������û�����
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