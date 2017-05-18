package com.sentriz.health.cucumber.stepdefs;

import com.sentriz.health.Application;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
