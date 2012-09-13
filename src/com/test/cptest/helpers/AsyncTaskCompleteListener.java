package com.test.cptest.helpers;

interface AsyncTaskCompleteListener<T> {
    public void onTaskComplete(T result);
    public T doTask();
 }
