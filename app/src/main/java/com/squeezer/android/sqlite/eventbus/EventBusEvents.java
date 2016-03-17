package com.squeezer.android.sqlite.eventbus;

/**
 * Created by adnen on 3/17/16.
 */
public class EventBusEvents {

    public static class ItemEvent {
        String mMessage;

        public ItemEvent(String message) {
            mMessage = message;
        }

        public String getMessage(){
            return mMessage;
        }
    }

}
