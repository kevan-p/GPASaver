package com.company1.gpasaver.networking;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Interface for all Retrofit API calls.
 * Retrofit is insanely simple to use.
 * https://www.androidauthority.com/retrofit-android-tutorial-906928/
 */
public interface ApiService {

    // Get the first user in the list: api.randomuser.me
    @GET Observable<UserResponse> fetchUsers(@Url String url);
}
