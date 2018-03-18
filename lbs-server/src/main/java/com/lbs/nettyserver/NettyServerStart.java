package com.lbs.nettyserver;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lbs.nettyserver.handler.ChannelInitialHandler;

/**
 * 
 *
 *
 */
public class NettyServerStart
{
  private static int port;
  public static ApplicationContext factory;
  private static final Logger logger = LoggerFactory.getLogger(NettyServerStart.class);
//  private static final Log logger = LogFactory.getLog(NettyServerStart.class);
  public static void main(String[] args)
    throws Exception
  {
//	  DOMConfigurator.configureAndWatch("config/log4j.xml");
//    initLogback();
    if (args.length > 0)
      port = Integer.parseInt(args[0]);
    else {
      port = 8189;
    }
    run();
  }

  private static void run()throws Exception
  {
    logger.info("server is running……");
    System.out.println("server is running……");
    factory = new ClassPathXmlApplicationContext("config/application-context.xml");
    ChannelInitialHandler initializer = (ChannelInitialHandler)factory.getBean(ChannelInitialHandler.class);

    int filePort = port + 1;
    int msgPort = port + 2;
    initializer.setBizPort(port);
    initializer.setFilePort(filePort);
    initializer.setMsgPort(msgPort);
    NettyServer server = new NettyServer(port, filePort, msgPort);
    server.setInitializer(initializer);
    server.run();
  }
  private static void initLogback(){
    LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
    JoranConfigurator configurator = new JoranConfigurator();
    configurator.setContext(lc);
    lc.reset();
    try {
      configurator.doConfigure("D:/LBS/projects/servercode/src/main/resources/config/logback/logback.xml");
    } catch (JoranException e) {
      e.printStackTrace();
    }
  }
}