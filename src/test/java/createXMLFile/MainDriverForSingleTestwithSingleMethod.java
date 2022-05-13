package createXMLFile;

import org.testng.Reporter;

//import com.mcmena.basetest.createXML_SingleTestwithSingleMethod;

public class MainDriverForSingleTestwithSingleMethod {

	public static void main(String[] args) throws Exception {
		//public static void testDone() throws Exception{
		Reporter.log("====================Execution started =====================================" ,true);
		//createXML_SingleTestwithSingleMethod.createXml();
		Reporter.log("Created the xml",true);
		Thread.sleep(1000);
		//createXML_SingleTestwithSingleMethod.autoRunXml();
		Reporter.log("Executed the xml",true);
		Reporter.log("==============================Execution ended ==============================" , true);
	}
}
	 