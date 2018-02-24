package com.rmswebservice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rmsutil.domain.Response;
import com.rmsutil.domain.UserAuthentication;
import com.rmsutil.util.JsonUtil;
import com.rmswebservice.dao.UserAuthenticationMasterDAO;

@Service
public class UserServiceImpl implements UserService{
	@Value("${sms.mainurl}")
	private String mainurl;
	@Value("${sms.user}")
	private String user;
	@Value("${sms.apikey}")
	private String apikey;
	@Value("${sms.type}")
	private String type;
	@Value("${sms.senderId}")
	private String senderId;
	
	private static Logger logger=LogManager.getLogger(UserServiceImpl.class);
	@Autowired
private UserAuthenticationMasterDAO userAuthenticationMasterDAO;
	
	public String login(String jsonUserAuth) {
		UserAuthentication userAuthentication=JsonUtil.convertJsonToJava(jsonUserAuth,UserAuthentication.class);
		if(userAuthentication!=null){
			logger.info("Entered into login ::"+jsonUserAuth);
			userAuthentication=userAuthenticationMasterDAO.login(userAuthentication);
	jsonUserAuth=JsonUtil.convertJavaToJson(userAuthentication);
	logger.info("Response of login ::"+jsonUserAuth);
		}
		return jsonUserAuth;
	}

	
	public String changePassword(String userName, 
	String oldPassword, String newPassword) {
	logger.info("Entered into changePassword");
	
		Response response=new Response();
		response.setMessage("Password Change is failure!Please Try Again.");
	int count=userAuthenticationMasterDAO.changePassword(userName,oldPassword,newPassword);
	if(count>0){
		response.setStatus((byte)1);
		response.setMessage("Your Password is Changed Successfully.");
	logger.info("calling sendSms()");
		
	}
		return JsonUtil.convertJavaToJson(response);
	}

	public String sendSms(String phoneNumber,String msg) {
		String status="";

		
	
		logger.info(msg);

		URLConnection myURLConnection=null;
		URL myURL=null;
		BufferedReader reader=null;


		String encoded_message=URLEncoder.encode(msg);

		StringBuilder sbPostData= new StringBuilder(mainurl);
		sbPostData.append("user="+user); 
		sbPostData.append("&apikey="+apikey);
		sbPostData.append("&message="+encoded_message);
		sbPostData.append("&mobile="+phoneNumber);
sbPostData.append("&senderid="+senderId);
		sbPostData.append("&type="+type);
		
		//logger.info("user : " + username + " apikey : " + apikey + " senderid : " + senderid + " type : " + type);
		mainurl= sbPostData.toString();
		try
		{
			myURL = new URL(mainurl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			String response;
			while ((response = reader.readLine()) != null) 
				logger.info("sms response :"+response);
			reader.close();
		} 
		catch (IOException ie) 
		{ 
			logger.info("Exception occured while sending the Sms to User :"+ie.getMessage());
		}

		return status;
	}
	


}

	
/*	
	
	public String sendSms(){
	String rsp="";
    
    try {
        // Construct The Post Data
        String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode("spring", "UTF-8");
        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("2016java", "UTF-8");
        data += "&" + URLEncoder.encode("msisdn", "UTF-8") + "=" + URLEncoder.encode("917330196029", "UTF-8");
        data += "&" + URLEncoder.encode("msg", "UTF-8") + "=" + URLEncoder.encode("Hello", "UTF-8");
        data += "&" + URLEncoder.encode("sid", "UTF-8") + "=" + URLEncoder.encode("MYTEXT", "UTF-8");
        data += "&" + URLEncoder.encode("fl", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
        
        //Push the HTTP Request
        URL url = new URL("http://smslane.com/vendorsms/pushsms.aspx");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
    
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
       
        //Read The Response
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            // Process line...
            rsp= rsp+line;
        }
        wr.close();
        rd.close();
        
        System.out.println(rsp);
        
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return  rsp;
}*/