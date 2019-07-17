package com.agritask.ekaterinatemnogrudova.agritask.utils;

import io.reactivex.Scheduler;

public interface IScheduler {
    Scheduler io();
    Scheduler ui();
    Scheduler computation();
}