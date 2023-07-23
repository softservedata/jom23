package com.softserve.academy;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({
        "com.softserve.academy.controller",
        "com.softserve.academy.repository",
        "com.softserve.academy.service"
})
public class ToDoListApplicationTests {  }
