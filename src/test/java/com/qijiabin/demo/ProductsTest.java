package com.qijiabin.demo;

import org.junit.Test;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProductsTest {

	@Test
	public void test() {
		Product product = new Product(1, "jackson");
		byte[] data = serializeProduct(product);
		System.out.println("bytes: " + data);
		
		Product p = deserializeProduct(data);
		System.out.println(p);
	}
	
	/**
	 * 序列化
	 * @param product
	 * @return
	 */
	public byte[] serializeProduct(Product product) {
		byte[] bytes = null;
        Schema<Product> schema = RuntimeSchema.getSchema(Product.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        try {
        	bytes = ProtostuffIOUtil.toByteArray(product, schema, buffer);
        } finally {
            buffer.clear();
        }
        return bytes;
    }
	
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public Product deserializeProduct(byte[] bytes) {
		Product product = new Product();
        Schema<Product> schema = RuntimeSchema.getSchema(Product.class);
        ProtostuffIOUtil.mergeFrom(bytes, product, schema);
        return product;
    }
	
}

