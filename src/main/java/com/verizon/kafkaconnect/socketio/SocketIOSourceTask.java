package com.verizon.kafkaconnect.socketio;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocketIOSourceTask extends SourceTask {
	static final Logger log = LoggerFactory.getLogger(SocketIOSourceTask.class);

	public String topic;
	public long pollInterval;
	public String authToken;
	public String bootstrapServers;
	public String url1;
	public String userName;
	public String password;
	public String compareString;
	public boolean flag = false;

	@Override
	public String version() {
		return VersionUtil.getVersion();
	}

	SocketIOConnectorConfig config;

	// SocketClient restclient=new SocketClient();

	@Override
	public void start(Map<String, String> map) {

		this.config = new SocketIOConnectorConfig(map);
		topic = this.config.topic;
		pollInterval = this.config.pollInterval;
		authToken = this.config.authToken;
		bootstrapServers = this.config.bootstrapServers;

		url1 = this.config.url1;
		userName = this.config.userName;
		password = this.config.password;

	}

	@Override
	public List<SourceRecord> poll() throws InterruptedException {

		try {

			String token=null;
			ArrayList<SourceRecord> records = null;

			if (this.flag) {
				return null;
			} else {

				log.info("kafkaconnectrest pollInterval", pollInterval);
				synchronized (this) {
					this.wait(pollInterval);
				}

				if (url1 != null && url1.length() > 9) {

					
					if (authToken != null && authToken.length() > 9 && null!=userName && null!=password){
						RestClient client=new RestClient();
						 token=client.getAuthToken(authToken, userName, password);
					}
					
					
					IoClient cli = new IoClient();
					cli.executeOnce(url1, topic, bootstrapServers, token);
				}
				this.flag = true;
				System.out.println("else flag" + flag);
			}

			return records;

		} catch (Exception e) {

			log.info("kafkaconnectrest exception", e);
		}
		return null;
	}

	@Override
	public void stop() {
		// TODO: Do whatever is required to stop your task.
	}
}