package com.example.kolomachinetest.api;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallback<T> implements Callback<T> {

    private final RestCallback<T> mCallback;

    public ApiCallback(RestCallback<T> callback) {
        mCallback = callback;
    }


    public interface RestCallback<T> {
        void onSuccess(T result);

        void onFailure(String message);
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            mCallback.onSuccess(response.body());
        } else {
            mCallback.onFailure(response.message());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        mCallback.onFailure(t.getMessage());
    }
}
