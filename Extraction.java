import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;

import junit.framework.*;

import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
//import junit.framework.*;

import com.gargoylesoftware.*;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;

class Extraction
{
	WebClient webClient;
	JavaScriptJobManager manager;
	HtmlPage page2;
	HtmlSelect vocab_list;
	HtmlSelect word_group;
	BufferedWriter bw;
	BufferedWriter temp;
	String onlevel="";
	String onwordlist="";
	HtmlAnchor anchor;
	int first,second,third;
public static void main(String args[])throws Exception
{
	Extraction start=new Extraction();
	java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF); 
	System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
	start.contd();
	start.login();
	
	}

public void file()
{
	try
	{
	File file=new File("saif/Data.txt");
	FileWriter fw=new FileWriter(file);
	bw=new BufferedWriter(fw);
	}
	catch(Exception e)
	{
		System.out.println("xxxxxxxxxxxxxxxxxxxxERRORXXXXXXXXXXXXXXXXXXXXXXXXX");
		e.printStackTrace();
	}
}
public void temp_open(String level)
{
	/*try
	{
	File file=new File("saif/"+level+".txt");
	FileWriter fw=new FileWriter(file);
	temp=new BufferedWriter(fw);
	}catch(Exception e)
	{
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxERRORxxxxxxxxxxxxxxxxxxxxxxxx");
		e.printStackTrace();
	}*/
	}


public void login()throws Exception
{
	 webClient = new WebClient(BrowserVersion.FIREFOX_45);
     webClient.getOptions().setJavaScriptEnabled(true);
     webClient.getOptions().setCssEnabled(false);
     webClient.getOptions().setRedirectEnabled(true);
     webClient.setAjaxController(new NicelyResynchronizingAjaxController());
     webClient.getCookieManager().setCookiesEnabled(true);

     HtmlPage page=webClient.getPage("https://www.jamboreeeducation.com/login");
 
     List<HtmlForm> forms=page.getForms();//getting form
     HtmlForm login=forms.get(0);
     List<?> element=page.getByXPath("//input[@name='email']");
     HtmlInput email=(HtmlInput)element.get(0);
     email.setValueAttribute("moniljain66@gmail.com");
     element=page.getByXPath("//input[@name='password']");
     HtmlInput password=(HtmlInput)element.get(0);
     password.setValueAttribute("9819663545");
     element=page.getByXPath("//button[@type='submit']");
     System.out.println("--------------------------LOGIN---------------------");
     HtmlButton login_button=(HtmlButton)element.get(0);
     page=login_button.click();
     System.out.println("--------------------------LOGed in---------------------");
     
     manager = page.getEnclosingWindow().getJobManager();
     while (manager.getJobCount() > 1){}
     selection();

}
public void selection()throws Exception
{
    page2=webClient.getPage("https://www.jamboreeeducation.com/vocab/learn");//loading a page
    System.out.println("--------------------------Learn Page---------------------");

    manager = page2.getEnclosingWindow().getJobManager();
	 webClient.waitForBackgroundJavaScript(1000*15);//waiting js to load

    while (manager.getJobCount() > 1){}//waiting for js
    webClient.setAjaxController(new  NicelyResynchronizingAjaxController());//resynchronizing ajax
    
	 vocab_list = (HtmlSelect) page2.getByXPath("//select[@id='vocab-list']").get(0);//storing vocab-list select element
     List<HtmlOption> vocab_list_options=vocab_list.getOptions();//storing all the options of vocab_list
	 int vocab_list_size=vocab_list.getOptionSize();
     for(int i=first;i<vocab_list_size;i++)//reading each vocablist
     {
		 
		 HtmlOption vocab_list_option=vocab_list_options.get(i);//extracting option from vocab_list list 
    	 vocab_list.setSelectedAttribute(vocab_list_option, true);//selecting the option
         System.out.println("--------------------------"+vocab_list_option.getTextContent()+"---------------------");

		 webClient.waitForBackgroundJavaScript(1000*8);//waiting js to load
    	 //manager = page2.getEnclosingWindow().getJobManager();
    	 //while (manager.getJobCount() > 1) {}
    	 
		 word_group=(HtmlSelect)page2.getByXPath("//select[@id='vocab-groups']").get(0);//storing vocab-group select element
		 List<HtmlOption> word_group_options=word_group.getOptions();//storing all options of word_group in list
		 int word_group_size=word_group_options.size();
		 for(int x=second;x<word_group_size;x++)//reading each word_group
    	 {
    		
			 	HtmlOption vocab_group_option=word_group_options.get(x);//extracting option from word_group
			 	/*checkin*/
			 	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx \n"+vocab_group_option.getTextContent()+"\n xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			 	 /* */
    		    word_group.setSelectedAttribute(vocab_group_option, true);//selecting that word group
    		    webClient.waitForBackgroundJavaScript(1000*5);//wait js to load
    		  
    		   
    		    
    		    List<HtmlAnchor> anchors=(List<HtmlAnchor>) page2.getByXPath("//a[@class='btn btn-green']");//storing all the anchor tag
    		    int anchor_size=anchors.size();
    		    for(int y=third;y<anchor_size;y++)//reading each anchor
    		    {
    		        anchor=anchors.get(y);//extracting anchor from anchor_list
    		    	anchor.click();//clicking on that anchor
        		    webClient.waitForBackgroundJavaScript(1000*5);//waiting js to load
    		    	manager = page2.getEnclosingWindow().getJobManager();
    		    	while (manager.getJobCount() > 1) {}
    		   
        		    /*---------------extract data---------------------------------*/
          		    String level=vocab_list_options.get(i).getTextContent();
        		    String group=word_group_options.get(x).getTextContent();
        		    String word=anchor.getTextContent();
        		    temp_open(level);
        		    extraction(level,word_group_size,group,anchor_size,word);
        		    System.out.println("i="+i+" x="+x+" y="+y+" x-size="+word_group_size+" i-size="+vocab_list_size+" y-size="+anchor_size);
        		    /*------------------------------------------------------------*/
        		    
        		    HtmlButton cancel_button=(HtmlButton) page2.getByXPath("//button[@data-bb-handler='cancel']").get(0);// storing cancel button of a dialog box
        		    cancel_button.click();//clicking that cancel button
        		    
        		    
        		    
        		    webClient.waitForBackgroundJavaScript(1000*5);//waiting js to load
        	
    		     }
    		    third=0;
     }
		 temp.close();
		 second=0;
		 }
     
	}
public void extraction(String level,int vocab_list_size,String group,int word_group_size,String word)throws Exception
{
	BufferedReader bw1=new BufferedReader(new FileReader(new File("saif/Data.txt")));
	String s;
	String main="";
	while( (s=bw1.readLine())!=null)
	{
		main=main+s+"\n";
	}
	bw1.close();
	file();
	//System.out.println(main);
	bw.write(main);
	String meaning="";
	String example="";
	if(!(onlevel.equals(level)))
	{
		onlevel=level;
		System.out.println("<"+level+">"+"<"+word_group_size+">"+"\n");

		bw.write("<"+level+">"+"<"+word_group_size+">"+"\n");
		//temp.write("<"+level+">"+"<"+word_group_size+">"+"\n");
	}
	if(!(onwordlist.equals(group)))
	{
		onwordlist=group;
		bw.write("<"+group+">"+"<"+word_group_size+">"+"\n");
		//temp.write("<"+group+">"+"<"+word_group_size+">"+"\n");
		System.out.println("<"+group+">"+"<"+word_group_size+">"+"\n");

	}
	List<HtmlParagraph> paras=(List<HtmlParagraph>) page2.getByXPath("//p");
	int size=paras.size();
	   /*for(int i=0;i<size;i++)
	   {
		   String para=paras.get(0).getTextContent();
		   if(para.length()>7)
		   {
			   if(para.substring(0,8).equals("Meaning:"))
			   {
				   meaning=para;
				   
			   }
			  
			   if(para.substring(0,8).equals("Example:"))
			   {
				   example=para;
			   }
		   }
	   }*/
	   bw.write("<"+word+">"+"<"+paras.get(2).getTextContent()+">"+"<"+paras.get(3).getTextContent()+">"+"\n");
	   //temp.write("<"+word+">"+"<"+paras.get(2).getTextContent()+">"+"<"+paras.get(3).getTextContent()+">"+"\n");
	   System.out.println("<"+word+">"+"<"+paras.get(2).getTextContent()+">"+"<"+paras.get(3).getTextContent()+">"+"\n");
	 
bw.close();	
}

public void contd()
{
	first=0;
	second=7;
	third=4;
	onlevel="List 1";
	onwordlist="HAVING TWISTS/TURNS";
}

}

