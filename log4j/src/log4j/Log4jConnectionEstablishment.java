package log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4jConnectionEstablishment {
  
	private static Logger logger=Logger.getLogger(Log4jConnectionEstablishment.class);//to access both static and non static methods 
	static
	{
		try {
			//create layout out 
			/*SimpleLayout layout=new SimpleLayout();*/
			/*HTMLLayout layout = new HTMLLayout();*/
			PatternLayout layout = new PatternLayout();
			layout.setConversionPattern("%d{yyyy:MM:dd HH:mm:ss} %p %10.15c %-10t %10M %r %L %m %n");
	    //create appender object having layout object
	   /* ConsoleAppender appender = new ConsoleAppender(layout);*/
	 
		/* FileAppender appender = new FileAppender(layout ,"D:/logs/log4j/fileAppender.log");*/
		/* RollingFileAppender appender = new RollingFileAppender(layout,"D:/logs/log4j/RollingFileAppenderHtmllayout.html");
		 appender.setMaxFileSize("3kb");
		 appender.setMaxBackupIndex(3);
		 appender.setAppend(true);*/
	  DailyRollingFileAppender appender= new   DailyRollingFileAppender(layout,"D:/logs/log4j/dailyRollingAppender7.log", "'.'yyyy-MM-dd-HH-mm");
	    //add appender object to logout object
	    logger.addAppender(appender);
	    //logger level to retrive log messages
	   /* logger.setLevel(Level.DEBUG); *///default logger lower messages
		/*logger.setLevel(Level.INFO);*///info and higher messages
	    //INFO->info is use to important confirmation flow log messages ex:connection established ,generating otp ...
		/* logger.setLevel(Level.ERROR);*///only error fatal
	    logger.setLevel(Level.ALL);
	    logger.info("log4j::Log4jConnectionEstalishment==>log4j set up ready");
	}
		catch(Exception se)
		{
			se.printStackTrace();
			//FATAL ->fatal log messages to write unknown exception related catch blocks
			logger.fatal("log4j::log4jConnectionEstablishment==>unknown exception "+se.getMessage());
		}
	}
	public static void main(String[] args) {

		//DEBUG =>debug is use for normal confirmation code flow
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "siva", "velpula");
			//INFO =>info is use for confirmation flow log messages
			logger.info("log4j ::log4jConnectionEstablishment:: Connection established");
			if (con != null) {
				stmt = con.createStatement();
		    logger.debug("log4j::log4jConnectionEStablishment::Statement object is created");
     		}
			if (stmt != null) {
				rs = stmt.executeQuery("select * from emp");
			 logger.debug("log4j::log4jConnectionEstablihment:: result objec is created");
			}
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getInt(4)
							+ "," + rs.getDate(5));
						
				}
			    //WARN  =warn is use for code that shouldnot executed but executed means deprecated apis
				logger.warn("log4j::log4jConnectionEstablishment::deprecated apis is executeds");
			}
		} 
		catch(SQLException se )
		{
			se.printStackTrace();
			//error: error log message is use for known exception
			logger.error("log4j::log4jConnectionEstablishment::known problem"+se.getMessage()+" sql error code:"+se.getErrorCode());
		}catch(Exception cs)
		{
			cs.printStackTrace();
			logger.fatal("log4j::log4jConnectionEstblishment::unknown problem:"+cs.getMessage());
		}
		
		finally {
            logger.debug("log4j::log4jConnectionEstablishment::finally block to close the objects");
			try {
				if (con != null) {
					con.close();
					logger.debug("log4j::log4jConnectionEstablishment::connection object is closed");
				}
			} catch (SQLException se) {
				se.printStackTrace();
				logger.error("log4j::log4jConnectionEstablishment::Known problem "+se.getMessage()+" error code:"+se.getErrorCode());
			}
			try
			{
				if(stmt!=null)
				{
					stmt.close();
					logger.debug("log4j::log4jConnectionEstablishment::statement object is closed");
				}
			}catch(SQLException se)
			{
				se.printStackTrace();
				logger.error("log4j::log4jConnectionEstablishment::known problem "+se.getMessage()+ "error code:"+se.getErrorCode());
			}
			try
			{
				if(rs!=null)
				{
					rs.close();
					logger.debug("log4j::log4jConnectionEstablishment:: resultset obejct is closed ");
				}
			}catch(SQLException se)
			{
				se.printStackTrace();
				logger.error("log4j::log4jConnectionEstablishment:: known problem:"+se.getMessage()+" error code:"+se.getErrorCode());
			}
		}

	}
}
