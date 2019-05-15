package com.yqw.retrofitdemo.bean;

/**
 * 测试post的数据
 */
public class TestPostBean {
    String content;
    String type;

    public TestPostBean(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
