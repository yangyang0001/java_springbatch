package com.deepblue.springbatch.test;

import java.io.*;
import java.util.Base64;
import java.util.Map;

public class Base64Decoder {

    public static void main(String[] args) throws Exception {
        // 从数据库复制粘贴这里
        String base64 = "rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAAEdAARYmF0Y2gudGFza2xldFR5cGV0AD1vcmcuc3ByaW5nZnJhbWV3b3JrLmJhdGNoLmNvcmUuc3RlcC5pdGVtLkNodW5rT3JpZW50ZWRUYXNrbGV0dAANYmF0Y2gudmVyc2lvbnQABTUuMi4ydAAOYmF0Y2guc3RlcFR5cGV0ADdvcmcuc3ByaW5nZnJhbWV3b3JrLmJhdGNoLmNvcmUuc3RlcC50YXNrbGV0LlRhc2tsZXRTdGVwdAALY3VycmVudExpbmVzcgAOamF2YS5sYW5nLkxvbmc7i+SQzI8j3wIAAUoABXZhbHVleHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAAAAAAx4";

        byte[] data = Base64.getDecoder().decode(base64);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            Object obj = ois.readObject();
            if (obj instanceof Map<?, ?>) {
                System.out.println("ExecutionContext 内容:");
                ((Map<?, ?>) obj).forEach((k, v) -> System.out.println(k + " = " + v));
            } else {
                System.out.println("不是 Map 类型: " + obj.getClass());
            }
        }
    }
}
