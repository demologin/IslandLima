package com.javarush.island.marzhiievskyi;

import com.javarush.island.marzhiievskyi.services.ThreadsWorker;

public class Runner {


    public static void main(String[] args){
        ThreadsWorker threadsWorker = new ThreadsWorker();
        threadsWorker.startExecutor();


    }
}


