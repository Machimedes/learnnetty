package io.netty.example.echo;

import io.netty.buffer.*;

public class BufferTest {
    public static void main(String[] args) {
        UnpooledByteBufAllocator unpooledAlloc = UnpooledByteBufAllocator.DEFAULT;
        PooledByteBufAllocator pooledAlloc = PooledByteBufAllocator.DEFAULT;


        ByteBuf buffer = unpooledAlloc.buffer();
        System.out.println(buffer + "\t" + buffer.refCnt());
        buffer.writeByte((byte) 0);
        buffer.writeByte((byte) 1);
        buffer.writeByte((byte) 2);
        buffer.writeByte((byte) 3);

        ByteBuf slice1 = buffer.retainedSlice(0, 2);
        ByteBuf slice2 = buffer.slice(2, 2);

        slice1.release();
        System.out.println(buffer.refCnt());
        System.out.println(slice1.refCnt());
        buffer.release();

        System.out.println(slice2.refCnt());





        System.out.println("\n\n\n");


        buffer.retain();
        System.out.println(buffer + "\t" + buffer.refCnt());
        CompositeByteBuf buf = unpooledAlloc.compositeBuffer();
        buf.addComponent(unpooledAlloc.buffer(1));
        buf.addComponent(buffer);
        System.out.println(buf + "\t" + buf.refCnt());

        buf.release();
        System.out.println(buffer.refCnt() + "\t" + buf.refCnt());
        buffer.release();
    }
}
