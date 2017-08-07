package com.augment.demo.githubexplorer.model;

import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * @author Othman
 * The GitHubService : This class make the calls to the GitHub GraphQL API
 * I use OkHttpClient and ApolloClient to do the Job
 */

public class GitHubService {

    private static final String BASE_URL = "https://api.github.com/graphql";
    private static final String GITHUB_TOKEN = "eab7dd7c7994a7f0c46ef43cfd5417db7bec0b58";
    public static  String USER_NAME = "othmanelkourd";
    public static String connection_error = "Oops! something went wrong, please check your network connection";
    public static int REPO_NUMBER = 10;


    public static ApolloClient createApolloClient() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder builder = original.newBuilder().method(original.method(), original.body());
                    builder.addHeader("Authorization", "bearer "+GITHUB_TOKEN);
                    return chain.proceed(builder.build());
                })
                .build();

        return ApolloClient.builder().
                serverUrl(BASE_URL).okHttpClient(httpClient).
                build();
    }
}
