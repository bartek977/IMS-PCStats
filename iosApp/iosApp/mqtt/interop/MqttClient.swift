import Foundation
import MQTTNIO
import NIO
import NIOTransportServices

@objc public class SwiftMqttClient: NSObject {
    private var client: MQTTClient?
    private var connectionFuture: EventLoopFuture<MQTTConnackV5>?
    private var group: EventLoopGroup?
    private var lastMessage: String?
    private var lastTimestamp: Date?
    private var connected: Bool = false

    @objc public func connect(host: String, port: UInt16, username: String, password: String, useTLS: Bool = false, connectTimeoutSeconds: TimeAmount.Value = 10, completion: @escaping (Bool) -> Void) {
        print("[SwiftMqttClient] connect called host=\(host) port=\(port) useTLS=\(useTLS)")
        let group = NIOTSEventLoopGroup()
        self.group = group
        print("[SwiftMqttClient] EventLoopGroup created")

        let configuration = MQTTClient.Configuration(
            version: .v5_0,
            userName: username,
            password: password,
            useSSL: useTLS
        )
        print("[SwiftMqttClient] Configuration built")

        print("[SwiftMqttClient] Creating MQTTClient instance")
        let client = MQTTClient(
            host: host,
            port: Int(port),
            identifier: "iosClient-" + UUID().uuidString,
            eventLoopGroupProvider: .shared(group),
            configuration: configuration
        )
        self.client = client

        client.addCloseListener(named: "SwiftMqttClient.close") { [weak self] result in
            switch result {
            case .success:
                print("[SwiftMqttClient] Connection closed successfully")
            case .failure(let error):
                print("[SwiftMqttClient] Connection closed with error: \(error)")
            }
            self?.connected = false
        }

        print("[SwiftMqttClient] Starting connect...")
        let future = client.v5.connect()
        self.connectionFuture = future
        future.whenComplete { [weak self] result in
            guard let self = self else { return }
            switch result {
            case .success(let connack):
                print("[SwiftMqttClient] Connected. CONNACK: \(connack)")
                self.connected = true
                DispatchQueue.main.async { completion(true) }
            case .failure(let error):
                print("[SwiftMqttClient] MQTT connect failed: \(error)")
                self.connected = false
                DispatchQueue.main.async { completion(false) }
            }
        }
    }

    @objc public func subscribe(topic: String, completion: @escaping (String, String) -> Void) {
        guard let client = client else { print("[SwiftMqttClient] Client not connected!"); return }
        client.addPublishListener(named: "SwiftMqttClient.publish") { result in
            switch result {
            case .success(let pub):
                completion(pub.topicName, String(buffer: pub.payload))
                print("[SwiftMqttClient] Received publish topic=\(pub.topicName) size=\(pub.payload.readableBytes)")
            case .failure(let error):
                print("[SwiftMqttClient] Publish listener error: \(error)")
            }
        }
        let infos = [MQTTSubscribeInfo(topicFilter: topic, qos: .exactlyOnce)]
        client.subscribe(to: infos).whenComplete { result in
            switch result {
            case .success:
                print("[SwiftMqttClient] Subscribed to \(topic)")
            case .failure:
                print("[SwiftMqttClient] Failed to subscribe to \(topic)")
            }
        }
    }

    @objc public func disconnect() {
        connected = false
        print("[SwiftMqttClient] disconnect called")
        if let client = client {
            do { try client.syncShutdownGracefully(); print("[SwiftMqttClient] Client shutdown") } catch { print("[SwiftMqttClient] client shutdown error: \(error)") }
        }
        if let group = group {
            do { try group.syncShutdownGracefully(); print("[SwiftMqttClient] EventLoopGroup shutdown") } catch { print("[SwiftMqttClient] group shutdown error: \(error)") }
        }
        client = nil
        group = nil
    }

    @objc public func isConnected() -> Bool {
        return connected
    }
}