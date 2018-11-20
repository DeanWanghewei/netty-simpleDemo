package netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Author deanWang
 * <p>
 * 定义的MyHeartbeatHandler没有去继承 SimpleChannelInboundHandler，而是继承
 * SimpleChannelInboundHandler的父类ChannelInboundHandlerAdapter
 */
public class MyHeartbeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

        String idleType = "";
        switch (idleStateEvent.state()) {
            case READER_IDLE:
                idleType = "reader idle";
                break;

            case WRITER_IDLE:
                idleType = "writer idle";
                break;
            case ALL_IDLE:
                idleType = "all idle what fuck";
                break;
            default:
                idleType = "get some ERROR";
        }

        System.out.println(ctx.channel().remoteAddress() + " 超时类型：" + idleType);

        ctx.channel().close();

    }
}
