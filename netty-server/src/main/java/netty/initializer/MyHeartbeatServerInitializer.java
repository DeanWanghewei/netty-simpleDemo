package netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import netty.handler.MyHeartbeatHandler;

import java.util.concurrent.TimeUnit;

public class MyHeartbeatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        /**
         * 处理空闲状态的处理器
         * readeridleTime 服务器读取的时间阈值，客户端相隔 readerIdleTime 未向服务器发送心跳就会触发idleStateEvent事件
         *
         * writerIdleTime 服务端写事件的事件阈值，当服务端 writerIdleTime 未向客户端写心跳就会触发idleStateEvent 事件
         *
         * allIdleTime   服务端和客户端在 allIdleTime相互发送心跳时，就会触发 idleStateEvent 事件
         *
         */
        pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.MINUTES));


        pipeline.addLast(new MyHeartbeatHandler());
    }
}
