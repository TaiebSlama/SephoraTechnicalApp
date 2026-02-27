package com.sephora.technical_test.application.base.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * Created by taieb.slama on 15/08/2024 .
 * Copyright (c) 2023. All rights reserved.
 */
abstract class BaseChannelViewModel<STATE, EVENT> : ViewModel(), DefaultLifecycleObserver {

    protected abstract var stateChannel: Channel<STATE>

    private var stateObserverJob: Job? = null

    fun cancelAllCoroutines() {
        viewModelScope.coroutineContext.cancelChildren()
    }

    fun observeState(block: suspend CoroutineScope.(STATE) -> Unit) {
        stateObserverJob?.cancelChildren()
        stateObserverJob?.cancel()
        stateObserverJob = viewModelScope.launch(Dispatchers.Default) {
            for (state in stateChannel) {
                block(state)
            }
        }
    }

    fun launchIOCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        val job = viewModelScope.launch(Dispatchers.IO, block = block)
        return job
    }

    fun launchDefaultCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        val job = viewModelScope.launch(Dispatchers.Default, block = block)
        return job
    }

    fun launchMainCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        val job = viewModelScope.launch(Dispatchers.Main, block = block)
        return job
    }

    abstract fun initStates()

    abstract fun handleEvents(viewEvent: EVENT)

}