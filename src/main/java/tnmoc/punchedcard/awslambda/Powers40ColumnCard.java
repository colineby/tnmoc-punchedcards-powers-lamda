package tnmoc.punchedcard.awslambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tnmoc.punchedcards.Card;
import tnmoc.punchedcards.powers.Powers40ColumnCardFactory;

public class Powers40ColumnCard implements RequestHandler<Object, String> {
	private Powers40ColumnCardFactory factory = new Powers40ColumnCardFactory();
	private ArrayList<String> quotations = new ArrayList<String>();
	private ArrayList<String> blacklist = new ArrayList<String>();
	
	public  Powers40ColumnCard() {
		try{

			  URL u = this.getClass().getClassLoader().getResource("blacklist.dict");
			  BufferedReader br = new BufferedReader(new FileReader(URLDecoder.decode(u.getFile())));
			  while(br.ready()){
				  blacklist.add(br.readLine());
			  }
			  br.close();
			  u = this.getClass().getClassLoader().getResource("quotations.dict");
			  br = new BufferedReader(new FileReader(URLDecoder.decode(u.getFile())));
			  while(br.ready()){
				  quotations.add(br.readLine());
			  }
			  br.close();
		  }catch(Exception ex){
			  System.err.print(ex);
		  }
	}
	
	
    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);
        String s = "";
    	try{
    		s= new JSONObject(input.toString()).getString("card_content");
    	}catch(Exception e){}
    	if(s.equals("")){
    		s=pickQuotation();
    	}
    	
    	
    	if(isNaughty(s)) s = "Naughty";
        
        
        Card c = factory.getCard(s);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("card", c.getColumnData());
        jsonObject.put("interpretation", c.getInterpretation());
        jsonObject.put("rows", c.getCardFormat().getRows());
        jsonObject.put("columnFormat", c.getCardFormat().getColumnFormat() );
    	return jsonObject.toString();
       

    }
    
    public boolean isNaughty(String s ){
   	 
	   for(String w : blacklist){
	   		 String testString =  s.toLowerCase().trim();
	   		 if(testString.contains(w)) return true;
	   }
	   return false; 
    }
     
    private String pickQuotation(){
   	  int index = (int)Math.round(Math.random()*(quotations.size()-1));
   	  return quotations.get(index);
    }

}
