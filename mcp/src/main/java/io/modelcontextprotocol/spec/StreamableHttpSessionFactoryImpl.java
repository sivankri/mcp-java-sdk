package io.modelcontextprotocol.spec;

import io.modelcontextprotocol.server.McpServerFeatures;

import java.time.Duration;
import java.util.Map;

public class StreamableHttpSessionFactoryImpl implements McpServerSession.StreamableHttpSessionFactory {

	private final Duration requestTimeout;

	private final Map<String, McpServerSession.RequestHandler<?>> requestHandlers;

	private final Map<String, McpServerSession.NotificationHandler> notificationHandlers;

	private final McpServerSession.InitRequestHandler initRequestHandler;

	private final McpServerSession.InitNotificationHandler initNotificationHandler;

	private final McpServerFeatures.Async asyncMcpServerFeatures;

	public StreamableHttpSessionFactoryImpl(final Duration requestTimeout,
			final Map<String, McpServerSession.RequestHandler<?>> requestHandlers,
			final Map<String, McpServerSession.NotificationHandler> notificationHandlers,
			final McpServerSession.InitRequestHandler initRequestHandler,
			final McpServerSession.InitNotificationHandler initNotificationHandler,
			final McpServerFeatures.Async asyncMcpServerFeatures) {
		this.requestTimeout = requestTimeout;
		this.requestHandlers = requestHandlers;
		this.notificationHandlers = notificationHandlers;
		this.initRequestHandler = initRequestHandler;
		this.initNotificationHandler = initNotificationHandler;
		this.asyncMcpServerFeatures = asyncMcpServerFeatures;
	}

	@Override
	public McpServerSession create(String transportId) {
		return new McpServerSession(transportId, requestTimeout, initRequestHandler, initNotificationHandler,
				requestHandlers, notificationHandlers);
	}

	public Duration getRequestTimeout() {
		return requestTimeout;
	}

	public Map<String, McpServerSession.RequestHandler<?>> getRequestHandlers() {
		return requestHandlers;
	}

	public McpServerSession.InitRequestHandler getInitHandler() {
		return initRequestHandler;
	}

	public McpServerSession.InitNotificationHandler getInitNotificationHandler() {
		return initNotificationHandler;
	}

	public Map<String, McpServerSession.NotificationHandler> getNotificationHandlers() {
		return notificationHandlers;
	}

	public McpServerFeatures.Async getAsyncMcpServerFeatures() {
		return asyncMcpServerFeatures;
	}

}
