package com.riseuplabs.SimpleUserScoreApp.utils;

public class Mappings {
    public static final String REST = "/rest";

    public static final String CREATE_USER = "/createUser";
    public static final String UPDATE_USER = "/updateUser";
    public static final String CREATE_USER_INCLUDING_PROGRESS = "/createUserIncludingProgress";

    public static final String CREATE_USER_PROGRESS = "/createUserProgress";
    public static final String UPDATE_USER_PROGRESS = "/updateUserProgress";
    public static final String UPDATE_USER_INCLUDING_PROGRESS = "/updateUserIncludingProgress";

    public static final String GET_USER = "/list/users/{id}";
    public static final String GET_USER_PROGRESS = "/list/users/{id}/getUserProgress";
    public static final String GET_USER_LIST = "/list/users";
    public static final String GET_USER_PROGRESS_LIST = "/list/userProgresses";
    public static final String GET_LEADERS = "/list/leaders";
}
