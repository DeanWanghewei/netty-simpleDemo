package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.initializer.MyHeartbeatServerInitializer;

public class NettyHeartbeatServer {
    public static void main(String[] args) {
        NioEventLoopGroup baseGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();


        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(baseGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new MyHeartbeatServerInitializer());

            bootstrap.bind(8899).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            baseGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
