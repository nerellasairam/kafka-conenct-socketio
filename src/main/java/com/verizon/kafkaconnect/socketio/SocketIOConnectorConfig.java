package com.verizon.kafkaconnect.socketio;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Width;

import java.util.Map;


public class SocketIOConnectorConfig extends AbstractConfig {

	  public static final String TOPIC_CONFIG = "topic";
	  private static final String TOPIC_DOC = " topic";
	  
	  public static final String POLL_INTERVAL_CONFIG = "poll.interval.milliseconds";
	  private static final String POLL_INTERVAL_DOC =  "The amount of time in ms to wait between polling. ";
	  static final long POLL_INTERVAL_DEFAULT = 60000;
	  
	  public static final String USER_NAME = "user.name";
	  private static final String USER_NAME_DOC =  "authentication server user name. ";
	  static final String USER_NAME_DEFAULT = "optional";
	  
	  public static final String PASSWORD = "password";
	  private static final String PASSWORD_DOC =  "authentication server password. ";
	  static final String PASSWORD_DEFAULT = "optional";
	  
	  public static final String AUTH_TOKEN_URL_CONFIG = "authentication.token.url";
	  private static final String AUTH_TOKEN_URL_DOC = "If needs authentication token, provide the url";
	  static final String AUTH_ADDRESS_DEFAULT = "optional";

	  public static final String BOOTSTRAP_SERVERS_CONFIG = "bootstrap.servers";
	  private static final String BOOTSTRAP_SERVERS_DOC = "bootstrap.servers";
	  
	  public static final String GROUP_1 = "authentication";
	  
	  public static final String REST_URL1_CONFIG = "server.url";
	  private static final String REST_URL1_DOC = "server.url";
	  
	
	  
	  public final String topic;
	  public final long pollInterval;
	  public final String authToken;
	  public final String bootstrapServers;
	  public final String userName;
	  public final String password;
	  
	  
	  
	  public final String url1;

	 

	  
	  public SocketIOConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
	    super(config, parsedConfig);
	    
	    this.topic = this.getString(TOPIC_CONFIG);
	    this.pollInterval = this.getLong(POLL_INTERVAL_CONFIG);
	    this.authToken = this.getString(AUTH_TOKEN_URL_CONFIG);
	    this.bootstrapServers = this.getString(BOOTSTRAP_SERVERS_CONFIG);
	    this.userName = this.getString(USER_NAME);
	    this.password = this.getString(PASSWORD);
	   
	    
	    this.url1 = this.getString(REST_URL1_CONFIG);
	   
	  
	  }

	  public SocketIOConnectorConfig(Map<String, String> parsedConfig) {
	    this(conf(), parsedConfig);
	  }

	  public static ConfigDef conf() {
	    return new ConfigDef()
	        
	        .define(TOPIC_CONFIG, Type.STRING, Importance.HIGH, TOPIC_DOC)
	        .define(POLL_INTERVAL_CONFIG, Type.LONG, POLL_INTERVAL_DEFAULT, ConfigDef.Range.between(10, Integer.MAX_VALUE), Importance.MEDIUM, POLL_INTERVAL_DOC)	     
	        .define(BOOTSTRAP_SERVERS_CONFIG, Type.STRING, Importance.HIGH, BOOTSTRAP_SERVERS_DOC)
	        .define(REST_URL1_CONFIG, Type.STRING, Importance.HIGH, REST_URL1_DOC)
	        .define(AUTH_TOKEN_URL_CONFIG, Type.STRING, AUTH_ADDRESS_DEFAULT, Importance.HIGH, AUTH_TOKEN_URL_DOC, GROUP_1, 1, Width.LONG, AUTH_TOKEN_URL_CONFIG)
	        .define(USER_NAME, Type.STRING, USER_NAME_DEFAULT, Importance.HIGH, USER_NAME_DOC, GROUP_1, 2, Width.MEDIUM, USER_NAME)
	        .define(PASSWORD, Type.STRING, PASSWORD_DEFAULT, Importance.HIGH, PASSWORD_DOC, GROUP_1, 3, Width.MEDIUM, PASSWORD)	 ;       
	        
	       
	  }

	  
}
