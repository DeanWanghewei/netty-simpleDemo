package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import netty.initializer.MyHeartbeatClientInitializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyHearbeatClient {
    public static void main(String[] args) {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        try {

            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyHeartbeatClientInitializer());
            Channel channel = bootstrap.connect("127.0.0.1", 8899).sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            for (; ; ) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }

    }
}
