package com.taskm.task_manager.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    String type;

    String title;

    int status;

    String detail;

    String instance;




}
