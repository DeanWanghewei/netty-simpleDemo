package netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress());
        System.out.println("client output: " + s);

        channelHandlerContext.writeAndFlush("from client: " + LocalDateTime.now());

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("来自客户端的消息！");
    }
}
