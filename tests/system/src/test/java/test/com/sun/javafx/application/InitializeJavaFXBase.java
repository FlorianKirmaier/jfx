/*
 * Copyright (c) 2021, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package test.com.sun.javafx.application;

import javafx.application.Application;
import javafx.stage.Stage;
import junit.framework.Assert;
import test.util.Util;

public class InitializeJavaFXBase {


    public static class TestApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            System.out.println("start called!");
        }
    }

    public void doTestInitializeThenLaunchInFX() throws Exception {
        Util.runAndWait(() ->{
            try {
                System.out.println("Calling launch!");
                Application.launch(TestApp.class);
                System.out.println("Finished launch!");
                Assert.fail("Error: No Exception was thrown - expected IllegalStateException");
            } catch (IllegalStateException e) {
                // This Exception is what we expect!
                System.out.println("Works!");
            }
        });
    }

    public void doTestInitializeThenSecondLaunch() throws Exception {
        try {
            System.out.println("Calling launch!");
            Application.launch(TestApp.class);
            System.out.println("Finished launch!");
            Assert.fail("Error: No Exception was thrown - expected IllegalStateException");
            throw new Exception();
        } catch (IllegalStateException e) {
            // This Exception is what we expect!
            System.out.println("Works!");
        }
    }
}
