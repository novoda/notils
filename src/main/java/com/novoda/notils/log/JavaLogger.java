package com.novoda.notils.log;

class JavaLogger implements Logger {

    @Override
    public void v(String msg) {
        System.out.println(msg);
    }

    @Override
    public void v(String msg, Throwable t) {
        System.out.println(msg + t);
    }

    @Override
    public void i(String msg) {
        System.out.println(msg);
    }

    @Override
    public void i(String msg, Throwable t) {
        System.out.println(msg + t);

    }

    @Override
    public void d(String msg) {
        System.out.println(msg);

    }

    @Override
    public void d(String msg, Throwable t) {
        System.out.println(msg + t);

    }

    @Override
    public void w(String msg) {
        System.out.println(msg);

    }

    @Override
    public void w(String msg, Throwable t) {

        System.out.println(msg + t);
    }

    @Override
    public void e(String msg) {
        System.out.println(msg);

    }

    @Override
    public void e(String msg, Throwable t) {

        System.out.println(msg + t);
    }

    @Override
    public void wtf(String msg) {

        System.out.println(msg);
    }

    @Override
    public void wtf(String msg, Throwable t) {
        System.out.println(msg + t);

    }
}
