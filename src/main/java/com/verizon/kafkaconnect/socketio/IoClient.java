package com.verizon.kafkaconnect.socketio;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;

public class IoClient {

	private boolean isExecuted;

	public synchronized void executeOnce(String url, final String topic,
			final String bootServer, final String authToken)
			throws URISyntaxException {

		if (isExecuted) {
			return;
		} else {

			// Manager manager = new Manager(new URI("http://socket.com"));
			// Socket socket = manager.socket("/my-namespace");
			// socket.connect();
			System.out.println("start..");
			final Socket socket = IO.socket(url);

			System.out.println("url.." + url + topic);
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

				@Override
				public void call(Object... args) {

					if (null != authToken) {
						Transport transport = (Transport) args[0];

						transport.on(Transport.EVENT_REQUEST_HEADERS,
								new Emitter.Listener() {
									@Override
									public void call(Object... args) {
										@SuppressWarnings("unchecked")
										Map<String, List<String>> headers = (Map<String, List<String>>) args[0];
										// modify request headers
										headers.put("access_token",
												Arrays.asList(authToken));
									}
								});
					}

					socket.emit("message", "{\"name\":\"test\"}");
				}

			}).on("event", new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					if (null != args && args.length > 0) {
						JSONObject obj = new JSONObject(args[0].toString());
						if (null != obj) {
							SocketProducer pr = new SocketProducer();
							pr.produceMessage(obj.toString(), topic, bootServer);
						}
					}

				}

			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

				@Override
				public void call(Object... args) {
				}

			}).on("loadEvent", new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					if (null != args && args.length > 0) {
						JSONObject obj = new JSONObject(args[0].toString());
						if (null != obj) {
							SocketProducer pr = new SocketProducer();
							pr.produceMessage(obj.toString(), topic, bootServer);
						}
					}

				}

			}).on("linkEvent", new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					if (null != args && args.length > 0) {
						JSONObject obj = new JSONObject(args[0].toString());
						if (null != obj) {
							SocketProducer pr = new SocketProducer();
							pr.produceMessage(obj.toString(), topic, bootServer);
						}
					}

				}

			}).on("lspEvent", new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					if (null != args && args.length > 0) {
						JSONObject obj = new JSONObject(args[0].toString());
						if (null != obj) {
							SocketProducer pr = new SocketProducer();
							pr.produceMessage(obj.toString(), topic, bootServer);
						}
					}

				}

			}).on("p2mpEvent", new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					if (null != args && args.length > 0) {
						JSONObject obj = new JSONObject(args[0].toString());
						if (null != obj) {
							SocketProducer pr = new SocketProducer();
							pr.produceMessage(obj.toString(), topic, bootServer);
						}
					}

				}

			}).on("faciltyEvent", new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					if (null != args && args.length > 0) {
						JSONObject obj = new JSONObject(args[0].toString());
						if (null != obj) {
							SocketProducer pr = new SocketProducer();
							pr.produceMessage(obj.toString(), topic, bootServer);
						}
					}

				}

			}).on("haEvent", new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					if (null != args && args.length > 0) {
						JSONObject obj = new JSONObject(args[0].toString());
						if (null != obj) {
							SocketProducer pr = new SocketProducer();
							pr.produceMessage(obj.toString(), topic, bootServer);
						}
					}

				}

			});

			if (!socket.connected()) {
				socket.connect();
				System.out.println("start again..");
			}

			isExecuted = true;
		}
	}

}
